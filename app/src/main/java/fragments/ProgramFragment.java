package fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.parse.ParseObject;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;


import adapters.HetpinProgramListViewAdapter;
import adapters.PagerViewAdapter;
import mc.soched2015.R;

import model.Event;
import model.MeetingApp;
import model.Person;


public class ProgramFragment extends Fragment {

    private static ViewPager viewPager;
    RelativeLayout barradias;
    private SearchView mSearchView;
    ListView listview;
    TextView day_program;
    HetpinProgramListViewAdapter adapter;
    PagerViewAdapter pg_adapter;
    ImageButton left, right;
    public static MeetingApp meetingApp;
    public static Map<String, List<Event>> staticMap;
    public List<Event> eventList;
    private  String headerDay = "";

    public static ProgramFragment newInstance(ViewPager pager, Map<String, List<Event>> map, String headerText, MeetingApp mApp) {
        // Instantiate a new fragment
        ProgramFragment fragment = new ProgramFragment();
        staticMap = map;
        viewPager = pager;
        meetingApp = mApp;
        Bundle args = new Bundle();

        args.putString("header", headerText);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View RootView = inflater.inflate(R.layout.hetpin_program_layout, container, false);
        setHasOptionsMenu(true);
        headerDay = getArguments().getString("header");
        Log.e(getClass().getName(), "onCreateView + header: " + headerDay);
        barradias = (RelativeLayout) RootView.findViewById(R.id.navi_bar);
        barradias.setBackgroundColor(getResources().getColor(R.color.eventoTerciario));
        pg_adapter = new PagerViewAdapter(getChildFragmentManager());


        listview = (ListView) RootView.findViewById(R.id.commonListView);
//       listview.setTextFilterEnabled(true);

        day_program = (TextView) RootView.findViewById(R.id.dayProgram);


        return RootView;
    }


    @Override
    public void onStart() {
        super.onStart();
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ParseObject object = (ParseObject) (listview.getItemAtPosition(position));
                Event event = ParseObject.createWithoutData(Event.class, object.getObjectId());
                if(event.getType().equals("Break")){

                }
                else {
                    Fragment fragment = EventDetailFragment.newInstance(event, meetingApp);
                    final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.container, fragment);
                    ft.addToBackStack(null);
                    ft.commit();
                }

            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        if(meetingApp!=null){
            eventList = staticMap.get(headerDay);

            List<Event> events= eventList;

            Collections.sort(events, new Comparator<Event>() {
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



            Collections.sort(events, new Comparator<Event>() {
                @Override
                public int compare(Event lhs, Event rhs) {

                    if(lhs.getPlace()==null){
                        return 0;
                    }
                    else if (rhs.getPlace()==null){
                        return 0;
                    }
                    else {
                        return lhs.getPlace().getName().toString().compareTo(rhs.getPlace().getName().toString());

                    }

                }
            });



            if(Locale.getDefault().getLanguage().equals("en")) {
                headerDay = headerDay.replace("September", "Septiembre");
            }
            day_program.setText(headerDay);
            //Llama al adaptador con boolean true para mostrar celda como deberia salir en el Programa
            // (no viene de evento detalle), en el adaptador se configura la celda

            adapter = new HetpinProgramListViewAdapter(getActivity(), events, meetingApp, true, false);
            // lista de celdas PROGRAMA˛≈≈X
            listview.setAdapter(adapter);
            //listview.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
            listview.setCacheColorHint(0);



            //startLoading();
            Log.e(getClass().getName(), "onCreateView end" + "header: " + headerDay);
            for(Person person: meetingApp.getPersons()){
                if(person.getImage()!=null){
                    person.getImage().getParseFileV1().getDataInBackground();
                }

            }
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


}

