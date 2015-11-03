package adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.nostra13.universalimageloader.core.ImageLoader;
import com.parse.ParseFile;
import com.parse.ParseImageView;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import mc.ached.R;
import mc.ached.myApp;
import model.MeetingApp;

/**
 * Created by Alvaro on 2/21/15.
 */

//ADAPTADOR DE CELDAS MEETING
public class MeetingAppsListViewAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    myApp myapp;
    String month;

    private List<MeetingApp> meetingAppList = null;
    private ArrayList<MeetingApp> arraylist;

    public MeetingAppsListViewAdapter(Context context,
                           List<MeetingApp> meetingAppList) {


        this.context = context;
        this.meetingAppList = meetingAppList;
        inflater = LayoutInflater.from(context);
        this.arraylist = new ArrayList<>();
        this.arraylist.addAll(meetingAppList);
        myapp = (myApp) context.getApplicationContext();

    }

    public class ViewHolder {
        TextView name;
        TextView date;
        TextView place;
        ParseImageView icon;
        ProgressBar bar;
        RelativeLayout relativeLayout;
    }
    @Override
    public int getCount() {
        return meetingAppList.size();
    }

    @Override
    public MeetingApp getItem(int position) {
        return meetingAppList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }




    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.cell_meetingapp, null);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) view.findViewById(R.id.name);
            holder.place = (TextView) view.findViewById(R.id.place);
            holder.date = (TextView) view.findViewById(R.id.date);
            holder.bar = (ProgressBar)view.findViewById(R.id.progressBarIcon);
            // Locate the ImageView in listview_item.xml
            holder.icon = (ParseImageView) view.findViewById(R.id.icon);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.name.setText(meetingAppList.get(position).getName());
        /*
        if(Locale.getDefault().getLanguage().equals("en")){
            if(meetingAppList.get(position).getName2()!=null && !meetingAppList.get(position).getName2().isEmpty()){
                holder.name.setText(meetingAppList.get(position).getName2());
            }


        }

        else if(Locale.getDefault().getLanguage().equals("pt")){
            if(meetingAppList.get(position).getName3()!=null && !meetingAppList.get(position).getName3().isEmpty()){
                holder.name.setText(meetingAppList.get(position).getName3());
            }

        }
        else{

        }
*/
        //THIS LINE

                if(meetingAppList.get(position).getPlaceParse().getName()!=null ||
                        !meetingAppList.get(position).getPlaceParse().getName().isEmpty()){
                    holder.place.setText(meetingAppList.get(position).getPlaceParse().getName());
                }
                else{
                    Log.i("LOG","LOG");
                }







        Date date = meetingAppList.get(position).getStartDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);

        if(Locale.getDefault().getLanguage().equals("en")){

            month = cal.getDisplayName(Calendar.MONTH,Calendar.LONG, Locale.ENGLISH);
        }
        else if(Locale.getDefault().getLanguage().equals("pt")){
            Locale portuguese = new Locale("pt", "PT");
            month = cal.getDisplayName(Calendar.MONTH,Calendar.LONG, portuguese);
        }
        else {
            Locale spanish = new Locale("es", "ES");
            month = cal.getDisplayName(Calendar.MONTH,Calendar.LONG, spanish);
        }

        int day = cal.get(Calendar.DAY_OF_MONTH);
        Log.i("STARTDATE",String.valueOf(day));
        holder.date.setText(month +" "+day+", "+ year);

        holder.bar.setVisibility(View.VISIBLE);
        if(meetingAppList.get(position).getIcon()!=null){
            final ParseFile photoFile = meetingAppList.get(position).getIcon().getParseFileV1();
            if (photoFile!= null) {
                //Get singleton instance of ImageLoader
                ImageLoader imageLoader = ImageLoader.getInstance();
                //Load the image from the url into the ImageView.
                imageLoader.displayImage(photoFile.getUrl(), holder.icon);
            }
            else {

            }
        }





        holder.bar.setVisibility(View.GONE);


        return view;
    }






}
