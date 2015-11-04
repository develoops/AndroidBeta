package adapters;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import com.parse.ParseFile;
import com.parse.ParseImageView;


import java.util.ArrayList;
import java.util.List;

import mc.fmca.R;
import model.Facade;

/**
 * Created by Alvaro on 2/21/15.
 */
public class GridImageAdapter extends BaseAdapter {

    private static final String LOG_TAG = "LOG";
    Context context;
    LayoutInflater inflater;

    private List<Facade> stands= null;
    private ArrayList<Facade> arraylist;

    public GridImageAdapter(Context context,
                            List<Facade> standsapp) {
        this.context = context;
        this.stands= standsapp;
        inflater = LayoutInflater.from(context);
        this.arraylist = new ArrayList<>();
        this.arraylist.addAll(stands);

    }

    public class ViewHolder {

        ParseImageView image;
    }
    @Override
    public int getCount() {
        return stands.size();
    }

    @Override
    public Object getItem(int position) {
        return stands.get(position);
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
            view = inflater.inflate(R.layout.cell_gridimage, null);
            // Locate the TextViews in listview_item.xml

            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            int width = displayMetrics.widthPixels;
            int height = displayMetrics.heightPixels;

            Log.i("WIDTH",String.valueOf(width));
            Log.i("HEIGHT",String.valueOf(height));

            // Locate the ImageView in listview_item.xml
            holder.image = (ParseImageView) view.findViewById(R.id.image);
            holder.image.getLayoutParams().height = (height-100) - dpToPx(75);
            holder.image.getLayoutParams().width = (width);


            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }






        if(stands.get(position).getCompany().getLogo().getParseFileV1()!=null){
            final ParseFile photoFile = stands.get(position).getCompany().getLogo().getParseFileV1();
            holder.image.setParseFile(photoFile);
            holder.image.loadInBackground();
        }

        else{
            Log.i("LOG","LOG");
        }







        return view;
    }



    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }


}