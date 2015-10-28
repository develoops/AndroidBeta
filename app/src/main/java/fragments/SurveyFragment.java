package fragments;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import adapters.GridDocumentsAdapter;
import mc.mextesol.R;
import mc.mextesol.myApp;
import model.Answer;
import model.Event;
import model.Facade;
import model.MeetingApp;
import model.MobiFile;
import model.Person;
import model.Question;
import model.Question2Article;
import model.Questionnarie2Question;

/**
 * Created by Alvaro on 2/25/15.
 */
public class SurveyFragment extends Fragment {

    public static MeetingApp mApp;
    public static MobiFile map;
    public static ListView listView;
    public static TextView statement;
    ArrayList<MobiFile> mFiles = new ArrayList<>();

    ArrayList<Question> questionsA = new ArrayList<>();
    public static List <Question2Article> question2ArticleList;
    public static ArrayList<Question2Article> statements = new ArrayList<>();
    GridDocumentsAdapter adapter;
    public int currentindex = 0;
    public myApp myapp;
    public static RadioGroup radioGroup;
    public static RadioButton r0,r1,r2,r3,r4;
    //RadioGroup radioGroup;

    public static SurveyFragment newInstance() {

        // Instantiate a new fragment
      //Alfonso
        SurveyFragment fragment = new SurveyFragment();

        fragment.setRetainInstance(true);
        return fragment;

    }

    @Override
    public void onAttach(Activity activity) {

        this.myapp = (myApp) getActivity().getApplicationContext();
        super.onAttach(activity);





    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retain this fragment across configuration changes.

        if(!myapp.getBooleanEncuesta()){
            if(!myapp.checkConnection()){
                new AlertDialog.Builder(getActivity())
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Warning")
                        .setMessage("You need to connect to internet to answer")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getActivity().finish();
                            }

                        })
                        .show();
            }
            else {
                getQuestion2Article();
            }

        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View RootView = inflater.inflate(R.layout.survey_layout, container , false);

        // gridview a partir del elemento del xml gridview
        //final Button button = (Button) RootView.findViewById(R.id.comercialmap);

        //button.setText("Mapa Comercial");
        statement = (TextView) RootView.findViewById(R.id.statement);
        if(myapp.getBooleanEncuesta()){
            statement.setText("You already answer this survey. Thank you");
        }


        radioGroup = (RadioGroup) RootView.findViewById(R.id.answers);


        r0 = (RadioButton)RootView.findViewById(R.id.a0);
        r1 = (RadioButton)RootView.findViewById(R.id.a1);
        r2 = (RadioButton)RootView.findViewById(R.id.a2);
        r3 = (RadioButton)RootView.findViewById(R.id.a3);
        r4 = (RadioButton)RootView.findViewById(R.id.a4);





        return RootView;
    }

    @Override
    public void onResume() {
        super.onResume();





        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    // handle back button's click listener


                    return true;
                }
                return false;
            }
        });

    }

    private void getQuestion2Article(){

        ParseQuery<Question2Article> queryQuestion2Article = ParseQuery.getQuery(Question2Article.class);
        queryQuestion2Article.include("item");
        queryQuestion2Article.include("question");
        queryQuestion2Article.fromLocalDatastore();
        // queryQuestion2Article.orderByAscending("question.name");
        queryQuestion2Article.findInBackground(new FindCallback<Question2Article>() {
            @Override
            public void done(List<Question2Article> question2Articles, ParseException e) {



                question2ArticleList = question2Articles;

                Collections.sort(question2ArticleList, new Comparator<Question2Article>() {
                    @Override
                    public int compare(Question2Article lhs, Question2Article rhs) {
                        return lhs.getQuestion().getName().toString().compareTo(rhs.getQuestion().getName().toString());
                    }
                });
            }

        });



        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                if(statements.size()==20){

                }
                else {
                    for(Question2Article question2Article:question2ArticleList){
                        if(question2Article.getType().equals("statement")){
                            Log.i("STATEMT",question2Article.getQuestion().getName().toString());
                            statements.add(question2Article);
                        }
                    }

                }


            }
        }, 2000);


        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                getStatement();
            }
        }, 2000);



    }

    private void getStatement(){
        int check;
        check = currentindex+1;
        Log.i("CHECK",String.valueOf(check));
        Log.i("STAT",String.valueOf(statements.size()));
        if(check>statements.size()){
            statement.setText("THANK YOU");
            r0.setVisibility(View.GONE);
            r1.setVisibility(View.GONE);
            r2.setVisibility(View.GONE);
            r3.setVisibility(View.GONE);
            r4.setVisibility(View.GONE);
            myapp.setBooleanEncuesta();


        }
        else {
            statement.setText(statements.get(currentindex).getItem().getText());
            getOptions();
        }

    }





    private void getOptions(){
        ParseQuery<Question2Article> query = ParseQuery.getQuery(Question2Article.class);
        query.whereEqualTo("question",statements.get(currentindex).getQuestion());
        query.whereEqualTo("type","option");
        query.fromLocalDatastore();
        query.include("item");
        query.findInBackground(new FindCallback<Question2Article>() {
            @Override
            public void done(List<Question2Article> question2Articles, ParseException e) {



                Collections.sort(question2Articles,new Comparator<Question2Article>() {
                    @Override
                    public int compare(Question2Article lhs, Question2Article rhs) {
                        return lhs.getSequencePosition().intValue()-rhs.getSequencePosition().intValue();
                    }
                });

                if(question2Articles.size()==1){
                    r0.setVisibility(View.VISIBLE);
                    r1.setVisibility(View.GONE);
                    r2.setVisibility(View.GONE);
                    r3.setVisibility(View.GONE);
                    r4.setVisibility(View.GONE);

                }
                else if(question2Articles.size()==2){
                    r0.setVisibility(View.VISIBLE);
                    r1.setVisibility(View.VISIBLE);
                    r2.setVisibility(View.GONE);
                    r3.setVisibility(View.GONE);
                    r4.setVisibility(View.GONE);

                }

                else if(question2Articles.size()==3){
                    r0.setVisibility(View.VISIBLE);
                    r1.setVisibility(View.VISIBLE);
                    r2.setVisibility(View.VISIBLE);
                    r3.setVisibility(View.GONE);
                    r4.setVisibility(View.GONE);
                }

                else if(question2Articles.size()==4){
                    r0.setVisibility(View.VISIBLE);
                    r1.setVisibility(View.VISIBLE);
                    r2.setVisibility(View.VISIBLE);
                    r3.setVisibility(View.VISIBLE);
                    r4.setVisibility(View.GONE);
                }

                else {
                    r0.setVisibility(View.VISIBLE);
                    r1.setVisibility(View.VISIBLE);
                    r2.setVisibility(View.VISIBLE);
                    r3.setVisibility(View.VISIBLE);
                    r4.setVisibility(View.VISIBLE);
                }



                for (int i = 0; i < question2Articles.size(); i++) {
                    ((RadioButton) radioGroup.getChildAt(i)).setText(question2Articles.get(i).getItem().getText());
                }

                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if (checkedId == R.id.a0){
                            Log.i("CURRENT INDEX",String.valueOf(currentindex));
                            Log.i("STAT",String.valueOf(statements.size()));
                            setAnswer(r0.getText().toString());

                            currentindex++;
                            getStatement();
                            r0.setChecked(false);

                        }else if (checkedId == R.id.a1){
                            Log.i("CURRENT INDEX",String.valueOf(currentindex));
                            Log.i("STAT",String.valueOf(statements.size()));
                            setAnswer(r1.getText().toString());
                            currentindex++;
                            getStatement();
                            r1.setChecked(false);

                        }else if (checkedId == R.id.a2){
                            Log.i("CURRENT INDEX",String.valueOf(currentindex));
                            Log.i("STAT",String.valueOf(statements.size()));
                            setAnswer(r2.getText().toString());
                            currentindex++;
                            getStatement();
                            r2.setChecked(false);

                        }
                        else if (checkedId == R.id.a3){
                            Log.i("CURRENT INDEX",String.valueOf(currentindex));
                            Log.i("STAT",String.valueOf(statements.size()));
                            setAnswer(r3.getText().toString());
                            currentindex++;
                            getStatement();
                            r3.setChecked(false);
                        }
                        else {
                            Log.i("CURRENT INDEX",String.valueOf(currentindex));
                            Log.i("STAT",String.valueOf(statements.size()));
                            setAnswer(r4.getText().toString());
                            currentindex++;
                            getStatement();
                            r4.setChecked(false);
                        }
                    }
                });

            }
        });
    }

    private void setAnswer(String text){
        final Answer answer = new Answer();
        answer.setUser(ParseUser.getCurrentUser());
        answer.setPregunta(statements.get(currentindex).getItem().getText());
        answer.setRespuesta(text);
        new Thread(new Runnable() {
            public void run() {
                answer.saveEventually();
            }

        }).start();

    }
}
