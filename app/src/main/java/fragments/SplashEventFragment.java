package fragments;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.parse.ParseFile;
import com.parse.ParseImageView;

import mc.soched2015.R;
import model.MeetingApp;

/**
 * Created by Alvaro on 2/25/15.
 */

public class SplashEventFragment extends Fragment {

    public static MeetingApp meetingApp;
    public static View RootView;
    public static ParseImageView splash;

    public static SplashEventFragment newInstance(MeetingApp app) {

        // Instantiate a new fragment

        SplashEventFragment fragment = new SplashEventFragment();
        meetingApp = app;

        fragment.setRetainInstance(true);
        return fragment;

    }

    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        RootView = inflater.inflate(R.layout.splash_layout, container, false);
        /*
        for(Stand stand: meetingApp.getStands()){
            stand.getCompanyLogo().getDataInBackground();
            stand.getCompanyHeader().getDataInBackground();
        }*/

        //startLoading();

        ProgressBar bar = (ProgressBar) RootView.findViewById(R.id.progressBar);
        bar.setVisibility(View.GONE);
        splash = (ParseImageView) RootView.findViewById(R.id.splash);

        if(meetingApp.getSplashMeeting()!=null){
            ParseFile splashFile = meetingApp.getSplashMeeting().getParseFileV1();
            if (splashFile != null) {
                //Get singleton instance of ImageLoader
                ImageLoader imageLoader = ImageLoader.getInstance();
                //Load the image from the url into the ImageView.

                imageLoader.displayImage(splashFile.getUrl(), splash);
            } else {

            }
        }


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                Fragment fragment = MeetingAppViewPagerFragment.newInstance(meetingApp);
                final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.container, fragment);
                ft.addToBackStack(null);
                ft.commit();

            }
        }, 3000);


        return RootView;
    }

    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void onResume() {
        super.onResume();


//        final Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                //Do something after 100ms
//
//                Fragment fragment = MeetingAppViewPagerFragment.newInstance(meetingApp);
//                final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//                ft.replace(R.id.container, fragment);
//                ft.addToBackStack(null);
//                ft.commit();
//            }
//        }, 100);


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

