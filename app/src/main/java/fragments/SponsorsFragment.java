package fragments;


import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
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

import adapters.GridImageAdapter;

import mc.cvdl.R;
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
        gridview = (GridView) RootView.findViewById(R.id.gridView);// crear el
        // gridview a partir del elemento del xml gridview
        final Button button = (Button) RootView.findViewById(R.id.comercialmap);



        ParseQuery<MobiFile> query = ParseQuery.getQuery(MobiFile.class);
        query.whereEqualTo("subtype","mapa_comercial");
        query.getFirstInBackground(new GetCallback<MobiFile>() {
            @Override
            public void done(MobiFile mobiFile, ParseException e) {

                if(mobiFile!=null){
                    button.setVisibility(View.VISIBLE);
                    Log.i("MOBIFILE", String.valueOf(mobiFile.getObjectId()));
                    if(Locale.getDefault().getLanguage().equals("en")){
                        button.setText("Commercial Map");
                    }
                    else {
                        button.setText("Mapa Comercial");
                    }
                }

            }
        });



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialogo = new Dialog(getActivity());
                dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                dialogo.setContentView(R.layout.map_box_layout);

                ParseQuery<MobiFile> query = ParseQuery.getQuery(MobiFile.class);
                query.whereEqualTo("subtype","mapa_comercial");
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

        return RootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        /*
        View v = mTabHost.getTabWidget().getChildAt(0);
        v.setBackgroundResource(R.drawable.programa);
*/
        if(mApp!=null){
            if(mApp.getCompaniesFacade()!=null){

                List<Facade> facades = mApp.getCompaniesFacade();

                ArrayList<Facade> facade1 = new ArrayList<>();
                for(Facade facade:facades){
                    if(!facade.getRole().equals("Organizadores")){
                        facade1.add(facade);
                        Log.i("FACADE",facade.getObjectId());
                    }
                }
                Log.i("MAPP",String.valueOf(facade1));

                gridview.setAdapter(new GridImageAdapter(getActivity(),facade1));
                gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    public void onItemClick(AdapterView<?> parent, View v,
                                            int position, long id) {





                    }
                });
            }
        }


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