package utils;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import model.Event;

/**
 * Created by hetpin on 3/23/15.
 */
public class MUtil {
	public static boolean isUpdateLocal = true;
    public static String localKey = "hetpinlocal";
    private static String tag = "MUtil-THANH";
    private static long startTime;
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
    public static boolean logThanh = true;

    public static Map<String, List<Event>> divideEventByGroup(List<Event> eventList, List<String> keyStrings) {
        Map<String, List<Event>> eventByDate = new LinkedHashMap<String, List<Event>>();
        List<Date> keyDates = new ArrayList<Date>();
        for (Event event : eventList) {
            List eventForDate = eventByDate.get(dateFormat.format(event.getStartDate()));
            if (eventForDate == null) {
                eventForDate = new ArrayList<Event>();
                String date2 = dateFormat.format(event.getStartDate());
                //date2= date2.replace("agosto","August");
                Log.i("REPLACE2",date2);
                eventByDate.put(date2, eventForDate);
                keyDates.add(event.getStartDate());
            }
            eventForDate.add(event);
        }
        Collections.sort(keyDates);
        if (logThanh) {
            Log.e(tag, "size " + eventByDate.size());
            Log.e(tag, "events " + eventByDate.toString() );
        }
        for (int i = 0; i < keyDates.size(); i++) {
            Log.i("STRINGS",String.valueOf(dateFormat.format(keyDates.get(i))));

            keyStrings.add(dateFormat.format(keyDates.get(i)));
            Collections.sort(eventByDate.get(keyStrings.get(i)), new Comparator<Event>() {
                @Override
                public int compare( final Event object1, final Event object2) {
                    return object1.getStartDate().compareTo(object2.getStartDate());
                }
            });
            if (logThanh) {
                Log.e(tag, dateFormat.format(keyDates.get(i)));
            }
        }

        Log.i("EVENTBYDATE",String.valueOf(eventByDate));

        return eventByDate;
    }

    public static int divideEventByGroupSize(List<Event> eventList) {
        Map<String, List<Event>> eventByDate = new LinkedHashMap<String, List<Event>>();
        for (Event city : eventList) {
            List eventForDate = eventByDate.get(dateFormat.format(city.getStartDate()));
            if (eventForDate == null) {
                eventForDate = new ArrayList<Event>();
                String date = dateFormat.format(city.getStartDate());

                eventByDate.put(date, eventForDate);
            }
            eventForDate.add(city);
            Log.i("CITY",String.valueOf(city));
            Log.i("EVENTBYDAY",String.valueOf(eventForDate));
        }
        if (logThanh)
            Log.e("THANH", "divideEventByGroupSize " + eventByDate.size());
        return eventByDate.size();
    }

    public static long startTimer() {
        startTime = System.currentTimeMillis();
        return startTime;
    }

    public static long stopTimer(String message) {
        long delta = System.currentTimeMillis() - startTime;
        startTime = 0;
        if (logThanh)
            Log.e(tag, message + " Execution time: " + delta);
        return delta;
    }
}