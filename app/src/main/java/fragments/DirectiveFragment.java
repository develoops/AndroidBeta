package fragments;

import android.app.Activity;
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

import java.util.List;

import adapters.DirectiveListViewAdapter;

import mc.mextesol.R;
import model.Actor;
import model.MeetingApp;

/**
 * Created by Alvaro on 2/19/15.
 */
public class DirectiveFragment extends Fragment {

    ListView listview;
    SwipeDetector swipeDetector;
    DirectiveListViewAdapter adapter;
    public static List<Actor> actors;
    public MeetingApp mApp;

    public static DirectiveFragment newInstance(List<Actor> staff) {

        // Instantiate a new fragment

        DirectiveFragment fragment = new DirectiveFragment();

        actors = staff;

        fragment.setRetainInstance(true);
        return fragment;

    }


    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);




    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View RootView = inflater.inflate(R.layout.common_list_layout, container , false);
        listview = (ListView)RootView.findViewById(R.id.commonListView);

        listview.setOnTouchListener(swipeDetector);


        //startLoading();

        return RootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        if(actors!=null){
            adapter = new DirectiveListViewAdapter(getActivity(),actors, true);
        }
        else {

        }

        // Binds the Adapter to the ListView
        listview.setAdapter(adapter);
        Log.i("HOLA", "HOLA");

    }

    @Override
    public void onResume() {
        super.onResume();
        /*
        View v = mTabHost.getTabWidget().getChildAt(0);
        v.setBackgroundResource(R.drawable.programa);
*/
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Actor actor = (Actor)(listview.getItemAtPosition(position));
                Fragment fragment = DirectiveDetailFragment.newInstance(actor, true);
                final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.container,fragment);
                ft.addToBackStack(null);
                ft.commit();
                /*
                MeetingApp meetingApp = ParseObject.createWithoutData(MeetingApp.class, object.getObjectId());
                Fragment fragment = SplashEventFragment.newInstance(meetingApp);
                final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.container,fragment);
                ft.addToBackStack(null);
                ft.commit();*/

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