package fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import adapters.EventsFragmentAdapter;
import fragments.sliding_tab.SlidingTabLayout;
import mc.urolchi.MainActivity;
import mc.urolchi.R;
import mc.urolchi.myApp;
import model.Event;
import model.MeetingApp;
import model.Wall;
import utils.MUtil;
import views.CustomViewPager;

/**
 * Created by Alvaro on 3/3/15.
 */
public class MeetingAppViewPagerFragment extends Fragment {

    private CustomViewPager pager;
    EventsFragmentAdapter pageAdapter;
    public static MeetingApp meetingApp;
    private SlidingTabLayout mSlidingTabLayout;
    ListView listview;
    public static ArrayList<Event> events = new ArrayList<>();
    myApp app;
    public static List<Wall> news ;
    public static Wall wall;
    public static Long time;

    public static MeetingAppViewPagerFragment newInstance(MeetingApp app) {
        // Instantiate a new fragment
        MeetingAppViewPagerFragment fragment = new MeetingAppViewPagerFragment();
        meetingApp = app;
        fragment.setRetainInstance(true);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        this.app = (myApp) getActivity().getApplicationContext();
        app.setNow(false);
        app.setNowClickable(true);
        View v = inflater.inflate(R.layout.activity_text, container, false);
        Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.directorio);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPagerFragment loadDataFragment = new ViewPagerFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.container, loadDataFragment);
                ft.commitAllowingStateLoss();
            }
        });
        toolbar.setBackgroundColor(getResources().getColor(R.color.companySecundario));
        toolbar.inflateMenu(R.menu.menuprogram);
        View target = v.findViewById(R.id.news);

        final BadgeView badge = new BadgeView(getActivity(), target);
        if(meetingApp!=null){
            news= meetingApp.getWalls();
            if(news!=null){
                if (news.size() - app.getintNews() > 0) {
                    app.ingresoNews();
                }
                if (app.getNews()) {
                    badge.setText("0");
                    badge.increment(meetingApp.getWalls().size() - app.getintNews());
                    badge.show();
                } else {
                    badge.hide();
                }
            }

        }



        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                //Calendar cal = Calendar.getInstance();
                if (menuItem.getItemId() == R.id.news) {
                    if(news!=null){
                        app.setNews(news.size());
                        app.saleNews();
                    }

                    if(meetingApp.getWalls()!=null){
                        wall = meetingApp.getWalls().get(0);

                        Log.i("BOOLeee2", String.valueOf(app.getNews()));
                        NewsFragment newsfragment = NewsFragment.newInstance(wall.getNews());
                        final FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        getActivity().getFragmentManager().executePendingTransactions();
                        fragmentTransaction.replace(R.id.container, newsfragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                    else {

                    }

                    return true;
                }

                if (menuItem.getItemId() == R.id.search) {
                    SearchViewFragment newsfragment = SearchViewFragment.newInstance(meetingApp);
                    final FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    getActivity().getFragmentManager().executePendingTransactions();
                    fragmentTransaction.replace(R.id.container, newsfragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }

                if (menuItem.getItemId() == R.id.now) {
                    if (app.getNowClickable()) {
                        app.setNowClickable(false);
                        menuItem.setChecked(!menuItem.isChecked());
                        menuItem.setIcon(menuItem.isChecked() ? R.drawable.program : R.drawable.emm);
                        app.setNow(menuItem.isChecked());
                        reloadView();
                    }
                }

                return false;
            }
        });

        pager = (CustomViewPager) v.findViewById(R.id.pager);
        //pager.getAdapter().notifyDataSetChanged();
        mSlidingTabLayout = (SlidingTabLayout) v.findViewById(R.id.tabs);
        // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View



        // insert all tabs from pagerAdapter data

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(meetingApp!=null){
            List<model.View> Titles = meetingApp.getViews();
            int Numboftabs = Titles.size();
            Collections.sort(Titles, new Comparator<model.View>() {
                @Override
                public int compare(model.View lhs, model.View rhs) {
                    return lhs.getsortingAux() - rhs.getsortingAux();

                }
            });

            pager.setOffscreenPageLimit(4);
            pager.setMaxChildProgramId(MUtil.divideEventByGroupSize(meetingApp.getEvents()) - 1);
            pageAdapter = new EventsFragmentAdapter(getChildFragmentManager(), Titles, Numboftabs, meetingApp, pager, app);
            pager.setAdapter(pageAdapter);
            mSlidingTabLayout.setViewPager(pager);
        }

        else {
            Intent intent = new Intent(getActivity(),
                    MainActivity.class);

            startActivity(intent);
        }

    }
    public void reloadView(){
        if(meetingApp!=null){
            List<model.View> Titles = meetingApp.getViews();
            int Numboftabs = Titles.size();
            Collections.sort(Titles, new Comparator<model.View>() {
                @Override
                public int compare(model.View lhs, model.View rhs) {
                    return lhs.getsortingAux() - rhs.getsortingAux();
                }
            });
            pager.setOffscreenPageLimit(4);
            pager.setMaxChildProgramId(MUtil.divideEventByGroupSize(meetingApp.getEvents()) - 1);
            pageAdapter = new EventsFragmentAdapter(getChildFragmentManager(), Titles, Numboftabs, meetingApp, pager, app);
            pager.setAdapter(pageAdapter);
            mSlidingTabLayout.setViewPager(pager);
        }

        else {
            Intent intent = new Intent(getActivity(),
                    MainActivity.class);

            startActivity(intent);
        }
        app.setNowClickable(true);
    }
}