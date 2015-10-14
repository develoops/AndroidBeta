package fragments;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.parse.ParseObject;

import java.util.ArrayList;

import adapters.HetpinProgramListViewAdapter;
import adapters.SpeakerDetailAdapter;
import mc.cau.R;
import model.Actor;
import model.Event;
import model.MeetingApp;

/**
 * Created by Alvaro on 3/1/15.
 */
public class SpeakerDetailFragment extends Fragment {

    public static Actor actorEvent;
    public static Boolean bool;
    SpeakerDetailAdapter adapter;
    public static MeetingApp meetingApp;

    public static SpeakerDetailFragment newInstance(Actor actor, MeetingApp mApp, boolean b) {

        // Instantiate a new fragment
        bool = b;
        meetingApp=mApp; //Para Alfonso
        SpeakerDetailFragment fragment = new SpeakerDetailFragment();
        actorEvent = actor;
        fragment.setRetainInstance(true);
        return fragment;

    }

    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);
        //this._id = getArguments().getInt(INDEX);

        //((ActionBarActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.left);
        setHasOptionsMenu(true);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View RootView = inflater.inflate(R.layout.eventdetail_layout, container , false);
        //TextView speaker_name = (TextView)RootView.findViewById(R.id.speaker_name);
        //TextView institution = (TextView)RootView.findViewById(R.id.institution);
        //TextView country = (TextView)RootView.findViewById(R.id.country);
        ArrayList<Actor> speakers = new ArrayList<>();
        speakers.add(0,actorEvent);
        adapter = new SpeakerDetailAdapter(getActivity(),speakers,false);
        ListView speakercell = (ListView) RootView.findViewById(R.id.commonListView);
        ListView speakerlist = (ListView) RootView.findViewById(R.id.speakers_list_view);

        speakercell.setAdapter(adapter);

        TextView speaker_bio = (TextView)RootView.findViewById(R.id.content);
        RelativeLayout footer = (RelativeLayout)RootView.findViewById(R.id.footer);
        //RoundedImageView image = (RoundedImageView) RootView.findViewById(R.id.image_speaker);
        final ListView eventsofSpeaker = (ListView)RootView.findViewById(R.id.events_list_view);
        Log.i("ASD",String.valueOf(actorEvent));
        //speaker_name.setText(actorEvent.getPerson().getSalutation() + " " + actorEvent.getPerson().getFirstName() + " " + actorEvent.getPerson().getLastName());
        //institution.setText(actorEvent.getCompany().getName());
        footer.setVisibility(View.GONE);
        if(actorEvent.getPerson()!=null){
            if(actorEvent.getPerson().getBio()==null || actorEvent.getPerson().getBio().isEmpty()){
                speaker_bio.setVisibility(View.GONE);
                speakerlist.setVisibility(View.GONE);

            }
            else {
                speaker_bio.setText(actorEvent.getPerson().getBio());
                speaker_bio.setTextColor(getResources().getColor(R.color.negro));
                Log.i("SPEAJrrrER","SP");
            }
        }



        //country.setText(actorEvent.getCompany().getCountry());
        //ParseFile photoFile = actorEvent.getPerson().getProfilePicture();
        //image.setParseFile(photoFile);
        //image.loadInBackground();
        if(actorEvent.getEvents()!=null){
            if(actorEvent.getEvents().size()>0){
                HetpinProgramListViewAdapter adapter = new HetpinProgramListViewAdapter(getActivity(),actorEvent.getEvents(),meetingApp,false,true);
                eventsofSpeaker.setAdapter(adapter);
                eventsofSpeaker.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ParseObject object = (ParseObject)(eventsofSpeaker.getItemAtPosition(position));
                        Event event = ParseObject.createWithoutData(Event.class, object.getObjectId());
                        Fragment fragment = EventDetailFragment.newInstance(event, meetingApp);
                        final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.container, fragment);
                        ft.addToBackStack(null);
                        ft.commit();
                    }
                });
            }
        }


        Toolbar toolbar = (Toolbar) RootView.findViewById(R.id.event_detail_toolbar);
        if(actorEvent.getPerson()!=null){
            if(actorEvent.getPerson().getSalutation()!=null || !actorEvent.getPerson().getSalutation().isEmpty() ){
                toolbar.setTitle(actorEvent.getPerson().getSalutation()+ " " +actorEvent.getPerson().getFirstName()+ " " +
                        actorEvent.getPerson().getLastName());
            }
            else {
                toolbar.setTitle(actorEvent.getPerson().getFirstName()+ " " +
                        actorEvent.getPerson().getLastName());
            }

        }

        toolbar.setNavigationIcon(R.drawable.left);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();

            }
        });
        toolbar.setBackgroundColor(getResources().getColor(R.color.companySecundario));

        return RootView;
    }

    @Override
    public void onStart() {
        super.onStart();



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

                Log.i("HOLA",String.valueOf(keyCode));
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){
                    // handle back button's click listener

                    getActivity().onBackPressed();


                    return true;



                }
                return false;
            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
        if (!getActivity().isFinishing()){
            Log.i("DAFUCk","DADA");

        }
    }


    @Override
    public void onStop() {
        super.onStop();


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {

            case android.R.id.home:

                ((ActionBarActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.directorio);
                getActivity().onBackPressed();



                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
