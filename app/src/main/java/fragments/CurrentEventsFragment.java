package fragments;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.parse.ParseObject;

import java.util.List;
import java.util.Locale;

import adapters.HetpinProgramListViewAdapter;

import mc.waspalm.R;
import mc.waspalm.myApp;
import model.Event;
import model.MeetingApp;

/**
 * Created by hetpin on 4/16/15.
 */
public class CurrentEventsFragment extends Fragment {
    ListView listview;
    HetpinProgramListViewAdapter adapter;
    public static MeetingApp meetingApp;
    public static List<Event> eventList;
    myApp app;

    public static CurrentEventsFragment newInstance(MeetingApp mApp, List<Event> events) {
        // Instantiate a new fragment
        CurrentEventsFragment fragment = new CurrentEventsFragment();
        eventList = events;
        meetingApp = mApp;
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
    //current_event_toolbar
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View RootView = inflater.inflate(R.layout.hetpin_current_events_fragment, container, false);
        setHasOptionsMenu(true);
        this.app = (myApp) getActivity().getApplicationContext();
        Toolbar toolbar = (Toolbar) RootView.findViewById(R.id.current_event_toolbar);
        if(Locale.getDefault().getLanguage().equals("en")){
            toolbar.setTitle("Current events");
        }
        else {
            toolbar.setTitle("Current events");
        }

        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setBackgroundColor(getResources().getColor(R.color.companySecundario));
        listview = (ListView) RootView.findViewById(R.id.commonListView);
        if (meetingApp != null) {
            adapter = new HetpinProgramListViewAdapter(getActivity(), eventList, meetingApp, true, false);
            listview.setAdapter(adapter);
            listview.setCacheColorHint(0);
        }


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

    @Override
    public void onStart() {
        super.onStart();
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ParseObject object = (ParseObject) (listview.getItemAtPosition(position));
                Event event = ParseObject.createWithoutData(Event.class, object.getObjectId());
                if (event.getType().equals("Break")) {

                } else {
                    app.setFromDetail(false);
                    Fragment fragment = EventDetailFragment.newInstance(event, meetingApp);
                    final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.container, fragment);
                    ft.addToBackStack(null);
                    ft.commit();
                }
            }
        });
    }
}