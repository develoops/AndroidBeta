package fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import adapters.GridDocumentsAdapter;
import mc.mextesol.R;
import model.Event;
import model.Facade;
import model.MeetingApp;
import model.MobiFile;
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
    ArrayList<String> statements= new ArrayList<>();
    GridDocumentsAdapter adapter;
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


        super.onAttach(activity);




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View RootView = inflater.inflate(R.layout.survey_layout, container , false);

        // gridview a partir del elemento del xml gridview
        //final Button button = (Button) RootView.findViewById(R.id.comercialmap);

        //button.setText("Mapa Comercial");
        statement = (TextView) RootView.findViewById(R.id.statement);


        ParseQuery<Questionnarie2Question> query = ParseQuery.getQuery(Questionnarie2Question.class);
        query.findInBackground(new FindCallback<Questionnarie2Question>() {
            @Override
            public void done(List<Questionnarie2Question> questionnarie2Questions, ParseException e) {
                ArrayList<Question> questions= new ArrayList<>();

                for(Questionnarie2Question q2q:questionnarie2Questions){
                    questions.add(q2q.getQuestion());
                }


                    ParseQuery<Question2Article> query2 = ParseQuery.getQuery(Question2Article.class);
                    query2.include("item");
                    query2.include("question");
                    query2.whereEqualTo("type","statement");
                    query2.findInBackground(new FindCallback<Question2Article>() {
                        @Override
                        public void done(List<Question2Article> question2Articles, ParseException e) {
                            for (Question2Article q2a : question2Articles) {


                            }

                            Collections.sort(question2Articles, new Comparator<Question2Article>() {
                                @Override
                                public int compare(Question2Article lhs, Question2Article rhs) {

                                   return lhs.getQuestion().toString().compareTo(rhs.getQuestion().toString());

                                }
                            });

                            statement.setText(question2Articles.get(0).getQuestion().getName());
                        }
                    });

                ParseQuery<Question2Article> query3 = ParseQuery.getQuery(Question2Article.class);
                query3.include("item");
                query3.include("question");

                query3.findInBackground(new FindCallback<Question2Article>() {
                    @Override
                    public void done(List<Question2Article> question2Articles, ParseException e) {

                        RadioGroup radioGroup = (RadioGroup) RootView.findViewById(R.id.answers);
                        for (int i = 0; i < radioGroup.getChildCount(); i++) {
                            ((RadioButton) radioGroup.getChildAt(i)).setText(question2Articles.get(i).getItem().getText());
                        }

                        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(RadioGroup group, int checkedId) {
                                if (checkedId == R.id.a0){
                                    Toast toast =
                                            Toast.makeText(getActivity(),
                                                    "Boton 1", Toast.LENGTH_SHORT);
                                    toast.show();
                                }else if (checkedId == R.id.a1){
                                    Toast toast =
                                            Toast.makeText(getActivity(),
                                                    "Boton 2", Toast.LENGTH_SHORT);
                                    toast.show();
                                }else if (checkedId == R.id.a2){
                                    Toast toast =
                                            Toast.makeText(getActivity(),
                                                    "Boton 3", Toast.LENGTH_SHORT);
                                    toast.show();
                                }
                            }
                        });

                    }
                });







            }
        });





        return RootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        /*
        View v = mTabHost.getTabWidget().getChildAt(0);
        v.setBackgroundResource(R.drawable.programa);
*/
/*

        ParseQuery<MobiFile> query = ParseQuery.getQuery(MobiFile.class);
        query.whereEqualTo("type","doc");
        query.findInBackground(new FindCallback<MobiFile>() {
            @Override
            public void done(List<MobiFile> mobiFiles, ParseException e) {
                adapter = new GridDocumentsAdapter(getActivity(),R.layout.cell_event,mobiFiles);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ParseObject object = (ParseObject)(listView.getItemAtPosition(position));
                        MobiFile mobiFile= ParseObject.createWithoutData(MobiFile.class, object.getObjectId());
                        Fragment fragment = DocumentDetailFragment.newInstance(mobiFile);
                        final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.container,fragment);
                        ft.addToBackStack(null);
                        ft.commit();
                    }
                });

            }
        });
        /*
        query.whereEqualTo("subtype","gallery");
        query.findInBackground(new FindCallback<MobiFile>() {
            @Override
            public void done(List<MobiFile> mobiFiles, ParseException e) {
                    mFiles = (ArrayList<MobiFile>) mobiFiles;

                gridview.setAdapter(new GridGalleryAdapter(getActivity(),mFiles));
                gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                        MobiFile mobiFile = (MobiFile)(gridview.getItemAtPosition(position));

                        final Dialog dialogo = new Dialog(getActivity());
                        dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                        dialogo.setContentView(R.layout.map_box_layout);
                        final Button done = (Button) dialogo.findViewById(R.id.btn_done_image_dialog);
                        TouchImageView mapadialog = (TouchImageView) dialogo.findViewById(R.id.image_dialog);
                        mapadialog.setMaxZoom(3f);
                        mapadialog.setMinZoom(1f);
                        if (mobiFile!= null) {
                            ImageLoader imageLoader = ImageLoader.getInstance();
                            //Load the image from the url into the ImageView.
                            imageLoader.displayImage(mobiFile.getParseFileV1().getUrl(), mapadialog);
                        }


                        dialogo.getWindow().getAttributes().width = RelativeLayout.LayoutParams.MATCH_PARENT;
                        done.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogo.dismiss();

                            }
                        });

                        dialogo.show();

                    }

                });
            }
        });*/







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
}
