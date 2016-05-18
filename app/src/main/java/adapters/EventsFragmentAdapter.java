package adapters;

import android.database.DataSetObserver;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import fragments.ChildPagerMeetingsFragment;


import fragments.CurrentEventsFragment;
import fragments.FavouritesFragment;
import fragments.GalleryFragment;
import fragments.MoreFragment;
import fragments.SpeakersFragment;
import fragments.SponsorsFragment;

import mc.peoplemarketing.myApp;
import model.Event;
import model.MeetingApp;
import views.CustomViewPager;

/**
 * Created by Alvaro on 3/3/15.
 */
public class EventsFragmentAdapter extends FragmentStatePagerAdapter

{
    private CustomViewPager custom_pager;
    public static MeetingApp mApp;
    public static Long time;
    public static  ArrayList<Event> events = new ArrayList<>();
    public Boolean now = false;
    myApp app;

    List<model.View> tabUIs; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created


    public EventsFragmentAdapter(FragmentManager fm, List<model.View> mTitles, int mNumbOfTabsumb, MeetingApp meetingApp, CustomViewPager pager, myApp app) {
        super(fm);
        mApp=meetingApp;
        this.tabUIs= mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;
        this.custom_pager = pager;
        events.clear();
        this.app = app;
    }

    @Override
    public int getCount()
    {
        return NumbOfTabs;
    }
    @Override
    public Fragment getItem(int position)
    {

        Calendar cal = Calendar.getInstance();
        Log.i("MAPPID",mApp.getObjectId());
        if(tabUIs.get(position).getnameView().equals("navEvents")){
            Log.e("THANHNX", "CHILD PAGER ChildPagerMeetingsFragment 0");
            if(timeZone().equals("-03:00")){
                time = cal.getTimeInMillis() - 10800000;
            }
            else if(timeZone().equals("-04:00")){
                time = cal.getTimeInMillis() - 14400000;
            }
            else if(timeZone().equals("-05:00")){
                time = cal.getTimeInMillis() -18000000;
            }
            else if(timeZone().equals("-06:00")){
                time = cal.getTimeInMillis() -21600000;
            }
            else if(timeZone().equals("-07:00")){
                time = cal.getTimeInMillis() -25200000;
            }

            else if(timeZone().equals("-08:00")){
                time = cal.getTimeInMillis() -28800000;

            }
            else if(timeZone().equals("-02:00")){
                time = cal.getTimeInMillis() -7200000;
            }
            else if(timeZone().equals("-01:00")){
                time = cal.getTimeInMillis() -3600000;
            }
            else if(timeZone().equals("00:00")){
                time = cal.getTimeInMillis();
            }

            Log.i("TIME",String.valueOf(cal.getTimeInMillis()));
            try{
                Date now = new Date();
                //This block of comment just for test a day in 18th April
//		        Calendar c = Calendar.getInstance();
//		        c.set(Calendar.DAY_OF_MONTH, 18);
//		        now = c.getTime();
                List<Event> list = mApp.getEvents();
                for (int i = 0; i <list.size(); i++) {
                    Event event = list.get(i);
                    if(event.getStartDate().getTime()<=time
                            && time<=event.getEndDate().getTime() ) {

                        if(event.getType().equals("Curso")){

                        }
                        else {
                            events.add(event);
                        }

                        Log.e("THANHNXNOW1", "Added " + event.getStartDate().toString());
                    }
                }
            } catch (NullPointerException e){
                Log.e("THANHNX events.add()", e.toString());
            }
            Log.e("Thanhnx", "events size " + events.size());
            Log.i("NOWLOCO", String.valueOf(app.getNow()));
            if (app.getNow())
                return CurrentEventsFragment.newInstance(mApp, events);
            else
                return ChildPagerMeetingsFragment.newInstance(mApp, this.custom_pager);
        }
        else if(tabUIs.get(position).getnameView().equals("navSpeakers")){
            return SpeakersFragment.newInstance(mApp);
        }
        else if(tabUIs.get(position).getnameView().equals("navFavorites")){
            return FavouritesFragment.newInstance(mApp);
        }



        else if(tabUIs.get(position).getnameView().equals("navSponsors")&& tabUIs.get(position).getTitle().equals("Patrocinadores")){
            return SponsorsFragment.newInstance(mApp);
        }

        else if(tabUIs.get(position).getnameView().equals("navGallery")){
            return GalleryFragment.newInstance(mApp);
        }

        else {
            return MoreFragment.newInstance(mApp);
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public String getPageTitle(int position) {
        try {
            for(Event event: mApp.getEvents()){
                if(event.getStartDate().getTime()<=getTime()
                        && getTime()<=event.getEndDate().getTime()){
                    now=true;
                }
            }
        } catch (NullPointerException e){
            Log.e("THANHNX now = true", e.toString());
        }
        if(Locale.getDefault().getLanguage().equals("en")){


            Log.i("SI","SI");
            if(tabUIs.get(position).getTitle()!=null && !tabUIs.get(position).getTitle().isEmpty()){
                return tabUIs.get(position).getTitle();
            }
            else {
                return tabUIs.get(position).getTitle();
            }





        }
        else {


            return tabUIs.get(position).getTitle();


        }

    }





    public static String timeZone()
    {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.getDefault());
        String   timeZone = new SimpleDateFormat("Z").format(calendar.getTime());
        return timeZone.substring(0, 3) + ":"+ timeZone.substring(3, 5);
    }

    public Long getTime(){

        Calendar cal = Calendar.getInstance();
        if(timeZone().equals("-03:00")){
            time = cal.getTimeInMillis() - 10800000;
        }
        else if(timeZone().equals("-04:00")){
            time = cal.getTimeInMillis() - 14400000;
        }
        else if(timeZone().equals("-05:00")){
            time = cal.getTimeInMillis() -18000000;
        }
        else if(timeZone().equals("-06:00")){
            time = cal.getTimeInMillis() -21600000;
        }
        else if(timeZone().equals("-07:00")){
            time = cal.getTimeInMillis() -25200000;
        }

        else if(timeZone().equals("-08:00")){
            time = cal.getTimeInMillis() -28800000;

        }
        else if(timeZone().equals("-02:00")){
            time = cal.getTimeInMillis() -7200000;
        }
        else if(timeZone().equals("-01:00")){
            time = cal.getTimeInMillis() -3600000;
        }
        else if(timeZone().equals("00:00")){
            time = cal.getTimeInMillis();
        }

        return time;
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        if (observer != null) {
            super.unregisterDataSetObserver(observer);
        }
        else{
            Log.i("LOG","LOG");
        }

    }


}