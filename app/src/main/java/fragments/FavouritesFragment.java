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
import android.widget.RelativeLayout;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import adapters.HetpinProgramListViewAdapter;

import mc.mextesol.R;
import mc.mextesol.myApp;
import model.Event;
import model.MeetingApp;
import model.Person;
import model.Rating;
import utils.Sectionizer;
import utils.SimpleSectionAdapter;

/**
 * Created by Alvaro on 2/25/15.
 */
public class FavouritesFragment extends Fragment {

    public static List <Event> events2;
    HetpinProgramListViewAdapter adapter;
    ListView listview;
    public static MeetingApp meetingApp;
    public myApp myapp;
    RelativeLayout relativeLayout;
    public static FavouritesFragment newInstance(MeetingApp mApp) {

        // Instantiate a new fragment

        FavouritesFragment fragment = new FavouritesFragment();
        meetingApp = mApp;

        return fragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View RootView = inflater.inflate(R.layout.favorite_list_layout, container , false);


        this.myapp = (myApp) getActivity().getApplicationContext();

       // relativeLayout = (RelativeLayout) RootView.findViewById(R.id.fav_bar);
       // relativeLayout.setBackgroundColor(getResources().getColor(R.color.eventoTerciario));
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
/*
        Date date = meetingAppList.get(position).getStartDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);

        month = cal.getDisplayName(Calendar.MONTH,Calendar.LONG, Locale.ENGLISH);

        int day = cal.get(Calendar.DAY_OF_MONTH);
        Log.i("STARTDATE",String.valueOf(day));
        holder.date.setText(month +" "+day+", "+ year);
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

                            events2 = events;
                            Log.i("EVENTS2",String.valueOf(events2));
                            Collections.sort(events2, new Comparator<Event>() {
                                @Override
                                public int compare(Event lhs, Event rhs) {

                                    int date1Diff = lhs.getStartDate().compareTo(rhs.getStartDate());
                                    if(date1Diff==0){
                                        return (int)lhs.getEndDate().getTime()-(int)rhs.getEndDate().getTime();
                                    }
                                    else {
                                        return (int)lhs.getStartDate().getTime() - (int)rhs.getStartDate().getTime();
                                    }

                                }
                            });
                            if(events2!=null){
                                if(getActivity()!=null){
                                    adapter = new HetpinProgramListViewAdapter(getActivity(),events2,meetingApp,false,true);

                                    Sectionizer<Event> alphabetSectionizer = new Sectionizer<Event>() {


                                        @Override
                                        public String getSectionTitleForItem(Event instance) {
                                            /*
                                            Calendar cal = Calendar.getInstance();
                                            cal.setTime(instance.getStartDate());
                                            String month;
                                            String date;
                                            int year = cal.get(Calendar.YEAR);
                                            month = cal.getDisplayName(Calendar.MONTH,Calendar.LONG, Locale.ENGLISH);
                                            int day = cal.get(Calendar.DAY_OF_MONTH);
                                            date = month +" "+day+", "+ year;
                                            */
                                            return instance.getTitle2();
                                        }
                                    };

                                    SimpleSectionAdapter<Event> sectionAdapter = new SimpleSectionAdapter<Event>(getActivity(),
                                            adapter, R.layout.fav_bar, R.id.dayFavorite, alphabetSectionizer);

                                    // 6. Set the adapter to your ListView
                                    listview.setAdapter(sectionAdapter);
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
