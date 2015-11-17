package fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import adapters.ChildPagerMeetingsAdapter;
import mc.sms.R;
import model.Event;
import model.MeetingApp;
import utils.MUtil;
import views.CustomViewPager;


/**
 * Created by Hetpin on 3/15/15.
 */
public class ChildPagerMeetingsFragment extends Fragment implements View.OnClickListener {

    private ChildPagerMeetingsAdapter pg_adapter;
    private static CustomViewPager parents_pager;
    public static ViewPager viewPager;
    public static MeetingApp mApp;
    private ImageButton left, right;
    private Map<String,List<Event>> map;
    private List<String> keys;
    public static  ArrayList<Event> anidateEvents = new ArrayList<>();
    public static  ArrayList<Event> events = new ArrayList<>();


    public static ChildPagerMeetingsFragment newInstance(MeetingApp meetingApp, CustomViewPager custom_pager) {
        // Instantiate a new fragment
        ChildPagerMeetingsFragment fragment = new ChildPagerMeetingsFragment();
        mApp = meetingApp;
        parents_pager = custom_pager;
        anidateEvents.clear();
        events.clear();
        return fragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View RootView = inflater.inflate(R.layout.fragment_childpager, container, false);
        setHasOptionsMenu(true);
        left = (ImageButton) RootView.findViewById(R.id.btnBackDay);
        right = (ImageButton) RootView.findViewById(R.id.btnNextDay);
        left.setOnClickListener(this);
        right.setOnClickListener(this);


        viewPager = (ViewPager) RootView.findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(5);
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                parents_pager.f5SwipeStateFromChild(position);
                setNaviVisibility(position);
            }
        });

        return RootView;
    }

    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void onStop() {
        super.onStop();
        events.clear();
        anidateEvents.clear();

    }

    @Override
    public void onResume() {
        super.onResume();
        if(mApp!=null){

            for(Event event:mApp.getEvents()){
                if(event.getAnidateEvents()!=null && !event.getAnidateEvents().isEmpty()){
                    for(Event event1:event.getAnidateEvents()){
                        anidateEvents.add(event1);
                    }
                }
            }

            if(anidateEvents!=null){
                for(Event event: mApp.getEvents()){
                    if (anidateEvents.contains(event)){

                    }
                    else {
                        events.add(event);
                    }
                }
            }
            else {

            }

            keys = new ArrayList<>();
            if(events!=null && !events.isEmpty()){
                map = MUtil.divideEventByGroup(events, keys);
            }
            else {
                map = MUtil.divideEventByGroup(mApp.getEvents(), keys);
            }

            pg_adapter = new ChildPagerMeetingsAdapter(getChildFragmentManager(), mApp, viewPager, map, keys);
            viewPager.setAdapter(pg_adapter);
            setNaviVisibility(0);
        }
        else {
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNextDay:
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                break;

            case R.id.btnBackDay:
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                break;

        }
    }

    public void setNaviVisibility(int selected) {
        if (MUtil.logThanh)
            Log.e("THANH", "setNaviVisibility " + selected + " keysize = " + keys.size());
        if (selected == 0) {
            left.setVisibility(View.INVISIBLE);
            right.setVisibility(View.VISIBLE);
        } else {
            if (selected == (keys.size()-1)) {
                left.setVisibility(View.VISIBLE);
                right.setVisibility(View.INVISIBLE);
            } else {
                left.setVisibility(View.VISIBLE);
                right.setVisibility(View.VISIBLE);
            }
        }
    }

}

