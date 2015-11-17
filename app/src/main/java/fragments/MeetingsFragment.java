package fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.annotation.Nullable;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;

import java.util.List;


import adapters.MeetingAppsListViewAdapter;
import mc.sms.MainActivity;
import mc.sms.R;
import mc.sms.myApp;

import model.MeetingApp;


public class MeetingsFragment extends Fragment {

    SwipeDetector swipeDetector;

    Bundle savedState;
    ListView listview;
    public myApp myapp;
    MeetingAppsListViewAdapter adapter;
    public ParseImageView hdr;
    public static List<MeetingApp> meetingAppList;



    public static MeetingsFragment newInstance(List<MeetingApp> meetingApps) {

        // Instantiate a new fragment

        MeetingsFragment fragment = new MeetingsFragment();
        Log.i("meetings", String.valueOf(meetingApps));
        meetingAppList = meetingApps;


        fragment.setRetainInstance(true);
        return fragment;

    }


    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Restore State Here


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            Log.i("CHAO", "rer");

        } else {
            Log.i("HOLA", "rer");
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View RootView = inflater.inflate(R.layout.meeting_listview, container, false);
        listview = (ListView) RootView.findViewById(R.id.commonListView);
        hdr = (ParseImageView) RootView.findViewById(R.id.headerMeeting);
        this.myapp = (myApp) getActivity().getApplicationContext();
        listview.setOnTouchListener(swipeDetector);
        return RootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (meetingAppList != null) {
            adapter = new MeetingAppsListViewAdapter(getActivity(), meetingAppList);
            listview.setAdapter(adapter);

        }

        else {
            Intent intent = new Intent(getActivity(),
                    MainActivity.class);

            startActivity(intent);
        }

    }

    @Override
    public void onResume() {
        super.onResume();

        if(meetingAppList.get(0).getIcon()!=null){
            ParseFile header  = meetingAppList.get(0).getIcon().getParseFileV1();
            if (header != null) {
                //Get singleton instance of ImageLoader
                ImageLoader imageLoader = ImageLoader.getInstance();
                //Load the image from the url into the ImageView.
                imageLoader.displayImage(header.getUrl(), hdr);
            }
            else{
                Log.i("NO HAY HEADER1","LOG");
                Log.i("LOG","LOG");
            }

            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();

            int height = displayMetrics.heightPixels;

            hdr.getLayoutParams().height = (height / 4) - dpToPx(55);
        }

        else{
            hdr.setVisibility(View.GONE);
            //hdr.setImageDrawable(null);
            Log.i("NO HAY HEADER0","LOG");
        }

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ParseObject object = (ParseObject) (listview.getItemAtPosition(position));
                MeetingApp meetingApp = ParseObject.createWithoutData(MeetingApp.class, object.getObjectId());
                Fragment fragment = SplashEventFragment.newInstance(meetingApp);
                final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.container, fragment);
                ft.addToBackStack(null);
                ft.commit();
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

    @Override
    public void onPause() {
        super.onPause();


    }

    @Override
    public void onStop() {
        super.onStop();

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("true", true);

    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }


}

