package fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import adapters.HetpinProgramListViewAdapter;

import mc.waspalm.R;
import mc.waspalm.myApp;
import model.Event;
import model.MeetingApp;
import model.Rating;

/**
 * Created by Alvaro on 2/25/15.
 */
public class FavouritesFragment extends Fragment {

    public static List <Event> events2;
    HetpinProgramListViewAdapter adapter;
    ListView listview;
    public static MeetingApp meetingApp;
    public myApp myapp;
    public static FavouritesFragment newInstance(MeetingApp mApp) {

        // Instantiate a new fragment

        FavouritesFragment fragment = new FavouritesFragment();
        meetingApp = mApp;

        return fragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View RootView = inflater.inflate(R.layout.common_list_layout, container , false);


        this.myapp = (myApp) getActivity().getApplicationContext();


        /*


*/

        //events = (List<Event>) ParseUser.getCurrentUser().get("favourites");



        listview = (ListView)RootView.findViewById(R.id.commonListView);


        return RootView;
    }
    @Override
    public void onResume() {
        super.onResume();
        /*
        View v = mTabHost.getTabWidget().getChildAt(0);
        v.setBackgroundResource(R.drawable.programa);
*/


        ParseQuery<Rating> query = ParseQuery.getQuery(Rating.class);
        query.whereEqualTo("user",ParseUser.getCurrentUser());
        query.whereEqualTo("type","fav");
        query.findInBackground(new FindCallback<Rating>() {
            @Override
            public void done(List<Rating> ratings, ParseException e) {
                Log.i("HELLO","HELLASDASD");
                ArrayList<String> ids = new ArrayList<>();

                if(ratings!=null){
                    for(Rating rating:ratings){
                        Log.i("IDD",String.valueOf(rating.getIdEvent()));
                        ids.add(rating.getIdEvent());

                    }

                    Log.i("IDDSS",String.valueOf(ids));
                    ParseQuery<Event> query2 = ParseQuery.getQuery(Event.class);
                    query2.whereContainedIn("objectId", ids);

                    query2.findInBackground(new FindCallback<Event>() {
                        @Override
                        public void done(List<Event> events, ParseException e) {
                            Log.i("PASEEEAFSADF","SDAFNVV");
                            Log.i("EVENTS",String.valueOf(events));
                            events2 = events;
                            Log.i("EVENTS2",String.valueOf(events2));
                            if(events2!=null){
                                if(getActivity()!=null){
                                    adapter = new HetpinProgramListViewAdapter(getActivity(),events2,meetingApp,false,true);
                                    listview.setAdapter(adapter);
                                }

                            }
                            else {
                                Log.i("JOLA","JOA");
                            }

                            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    ParseObject object = (ParseObject)(listview.getItemAtPosition(position));
                                    Event event = ParseObject.createWithoutData(Event.class, object.getObjectId());
                                    Fragment fragment = EventDetailFragment.newInstance(event, meetingApp);
                                    final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                                    ft.replace(R.id.container, fragment);
                                    ft.addToBackStack(null);
                                    ft.commit();
                                }
                            });

                        }
                    });
                }


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
