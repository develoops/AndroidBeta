package adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import fragments.ChildPagerOneDayMeetingsFragment;
import fragments.ProgramFragment;
import model.Event;
import model.MeetingApp;
import utils.MUtil;

/**
 * Created by Hetpin on 3/15/15.
 */
public class ChildPagerMeetingsAdapter extends FragmentStatePagerAdapter {
    private String tag = "ChildPagerMeetingsAdapter-THANH";
    private Map<String, List<Event>> map;
    private List<String> keys = new ArrayList<>();
    private ViewPager viewPager;
    private MeetingApp mApp;

    public ChildPagerMeetingsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (MUtil.logThanh) {
            Log.e(tag, "getItem at position " + position + " " + keys.get(position));
            Log.e(tag, keys.toString() + "");
        }
        return ProgramFragment.newInstance(viewPager, map, keys.get(position), mApp);
    }

    public ChildPagerMeetingsAdapter(FragmentManager fm, MeetingApp meetingApp, ViewPager viewPager, Map<String, List<Event>> map, List<String> keys) {
        super(fm);
        this.viewPager = viewPager;
        this.mApp = meetingApp;
        this.map = map;
        this.keys = keys;
    }

    @Override
    public int getCount() {
        return map.size();
    }
}
