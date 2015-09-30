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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import adapters.GridImageAdapter;
import mc.soched2015.R;
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
    ArrayList<MobiFile> mobiFiles = new ArrayList<>();

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
        final View RootView = inflater.inflate(R.layout.commercial_map, container , false);



        ParseQuery<MobiFile> query = ParseQuery.getQuery(MobiFile.class);
        query.whereEqualTo("title","Aztrazeneca");
        query.getFirstInBackground(new GetCallback<MobiFile>() {
            @Override
            public void done(MobiFile mobiFile, ParseException e) {
                map=mobiFile;
                ImageView touchImageView = (ImageView) RootView.findViewById(R.id.mapa);


                //touchImageView.setImageMatrix();
                if (map!= null) {
                    ImageLoader imageLoader = ImageLoader.getInstance();
                    //Load the image from the url into the ImageView.
                    imageLoader.displayImage(map.getParseFileV1().getUrl(), touchImageView);
                }

                touchImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = "http://www.astrazeneca.com";
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
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

