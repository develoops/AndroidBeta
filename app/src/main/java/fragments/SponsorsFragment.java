package fragments;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import mc.fmca.R;
import model.Facade;
import model.MeetingApp;
import model.MobiFile;
import utils.TouchImageView;

/**
 * Created by Alvaro on 2/25/15.
 */
public class SponsorsFragment extends Fragment {


    public static MeetingApp mApp;
    public static MobiFile map;
    public static GridView gridview;
    public static Button button,acomodation;


    public static SponsorsFragment newInstance(MeetingApp meetingApp) {

        // Instantiate a new fragment
        mApp= meetingApp; //Alfonso
        SponsorsFragment fragment = new SponsorsFragment();

        fragment.setRetainInstance(true);
        return fragment;

    }

    @Override
    public void onAttach(Activity activity) {


        super.onAttach(activity);




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View RootView = inflater.inflate(R.layout.sponsors_layout, container , false);
        button = (Button) RootView.findViewById(R.id.comercialmap);
        if (Locale.getDefault().getLanguage().equals("en")) {
            button.setText("Commercial Map");
        }
        else {
            button.setText("Mapa Comercial");
        }

        acomodation = (Button) RootView.findViewById(R.id.acomodation);
        acomodation.setText(R.string.accomodation);
        acomodation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = mApp.getStatus();
                if(url!=null){
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }

            }
        });

        if(mApp!=null) {
            if (mApp.getCompaniesFacade() != null) {

                List<Facade> facades = mApp.getCompaniesFacade();

                ArrayList<Facade> facade1 = new ArrayList<>();
                for (Facade facade : facades) {
                    if (!facade.getRole().equals("Organizadores")) {
                        facade1.add(facade);
                    }
                }
                Log.i("MAPP", String.valueOf(facade1));

                if(facade1!=null && !facade1.isEmpty()){
                    map = facade1.get(0).getCompany().getLogo();
                    TouchImageView mapadialog = (TouchImageView) RootView.findViewById(R.id.image_dialog);
                    mapadialog.setMaxZoom(2f);
                    mapadialog.setMinZoom(1f);
                    if (map!= null) {
                        ImageLoader imageLoader = ImageLoader.getInstance();
                        //Load the image from the url into the ImageView.
                        imageLoader.displayImage(map.getParseFileV1().getUrl(), mapadialog);
                    }
                }




            }


        }

/*
         gridview = (GridView) RootView.findViewById(R.id.gridView);
         //button = (Button) RootView.findViewById(R.id.comercialmap);

         acomodation = (Button) RootView.findViewById(R.id.acomodation);
         acomodation.setText(R.string.accomodation);
         acomodation.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String url = mApp.getStatus();
                 if(url!=null){
                     Intent i = new Intent(Intent.ACTION_VIEW);
                     i.setData(Uri.parse(url));
                     startActivity(i);
                 }

             }
         });
        /*
        if (Locale.getDefault().getLanguage().equals("en")) {
            button.setText("Commercial Map");
        }
        else {
            button.setText("Mapa Comercial");
        }

        if(mApp!=null){
            if(mApp.getCompaniesFacade()!=null){

                List<Facade> facades = mApp.getCompaniesFacade();

                ArrayList<Facade> facade1 = new ArrayList<>();
                for(Facade facade:facades){
                    if(!facade.getRole().equals("Organizadores")){
                        facade1.add(facade);
                    }
                }
                Log.i("MAPP", String.valueOf(facade1));
                gridview.setAdapter(new GridImageAdapter(getActivity(),facade1));

                gridview.setStretchMode( GridView.STRETCH_COLUMN_WIDTH );
                gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    public void onItemClick(AdapterView<?> parent, View v,
                                            int position, long id) {



                        ParseObject object = (ParseObject)(gridview.getItemAtPosition(position));


                        Facade stand = ParseObject.createWithoutData(Facade.class, object.getObjectId());

                        String url = stand.getCompany().getWeb();
                        if(url!=null){
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(url));
                            startActivity(i);

                        }

                        /*
                        if(stand.getCompany().getDetails()!=null){
                            Fragment fragment = CompanyFragment.newInstance(stand,mApp,true);
                            final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                            ft.replace(R.id.container,fragment);
                            ft.addToBackStack(null);
                            ft.commit();
                        }

                        else if(stand.getCompany().getWeb()!=null && stand.getCompany().getDetails()==null){

                        }
                        else {
                            Log.i("NADA","NADA");
                        }

                    }


                });
            }
        }


*/

        return RootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        /*
        View v = mTabHost.getTabWidget().getChildAt(0);
        v.setBackgroundResource(R.drawable.programa);
*/









        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialogo = new Dialog(getActivity());
                dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                dialogo.setContentView(R.layout.map_box_layout);

                ParseQuery<MobiFile> query = ParseQuery.getQuery(MobiFile.class);
                query.whereEqualTo("title","PlanoStands");
                query.getFirstInBackground(new GetCallback<MobiFile>() {
                    @Override
                    public void done(MobiFile mobiFile, ParseException e) {
                        map=mobiFile;
                        final Button done = (Button) dialogo.findViewById(R.id.btn_done_image_dialog);
                        TouchImageView mapadialog = (TouchImageView) dialogo.findViewById(R.id.image_dialog);
                        mapadialog.setMaxZoom(3f);
                        mapadialog.setMinZoom(1f);
                        if (map!= null) {
                            ImageLoader imageLoader = ImageLoader.getInstance();
                            //Load the image from the url into the ImageView.
                            imageLoader.displayImage(map.getParseFileV1().getUrl(), mapadialog);
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

        });



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