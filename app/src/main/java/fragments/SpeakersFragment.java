package fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.parse.ParseObject;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import adapters.SpeakersListViewAdapter;

import mc.soched.R;
import model.Actor;
import model.MeetingApp;
import model.Person;

/**
 * Created by Alvaro on 2/19/15.
 */
public class SpeakersFragment extends Fragment implements SearchView.OnQueryTextListener {

    ListView listview;
    private SearchView mSearchView;
    SpeakersListViewAdapter adapter;
    public static List<Person> persons;
    public static MeetingApp mApp;
    SwipeDetector swipeDetector;
    public static Actor actorEvent;
    public static SpeakersFragment newInstance(MeetingApp meetingApp) {

        // Instantiate a new fragment

        SpeakersFragment fragment = new SpeakersFragment();

        persons = meetingApp.getPersons();
        mApp = meetingApp;

        fragment.setRetainInstance(true);
        return fragment;

    }


    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);




    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e(getClass().getName(), "onCreateView");
        final View RootView = inflater.inflate(R.layout.speakers_layout, container , false);
        listview = (ListView)RootView.findViewById(R.id.commonListView);
        mSearchView=(SearchView) RootView.findViewById(R.id.search);
        return RootView;

    }

    @Override
    public void onStart() {
        super.onStart();


        if(persons!=null){
            List<Person> persons1 = persons;
            for(Person person:persons1){
                Log.i("PERSONAA",String.valueOf(person.getObjectId()));
            }

            Collections.sort(persons1,new Comparator<Person>() {
                @Override
                public int compare(Person lhs, Person rhs) {
                    return lhs.getSortingAux() - rhs.getSortingAux();
                }
            });
            adapter = new SpeakersListViewAdapter(getActivity(),persons1,mApp,true);
            // Binds the Adapter to the ListView
            listview.setAdapter(adapter);
            listview.setTextFilterEnabled(true);
            setupSearchView();
            listview.setOnTouchListener(swipeDetector);

            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ParseObject object = (ParseObject)(listview.getItemAtPosition(position));
                    Person person = ParseObject.createWithoutData(Person.class, object.getObjectId());
                    if(person.getActors()!=null){
                        for(Actor actor:person.getActors()){

                                actorEvent = actor;


                        }

                        Fragment fragment = SpeakerDetailFragment.newInstance(actorEvent, mApp, true);
                        final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.container,fragment);
                        ft.addToBackStack(null);
                        ft.commit();
                    }

                    else {
                        Log.i("NO","NO");
                    }

                }
            });
        }
        else {
            Log.i("NO","NO");
        }







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

    private void setupSearchView()
    {
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setQueryHint("Search here");
    }

    @Override
    public boolean onQueryTextChange(String newText)
    {

        if (TextUtils.isEmpty(newText)) {
            listview.clearTextFilter();
        } else {
            listview.setFilterText(newText.toString());
        }
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query)
    {
        return false;
    }


}
