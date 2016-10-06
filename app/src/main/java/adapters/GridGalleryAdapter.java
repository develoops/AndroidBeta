package adapters;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import com.nostra13.universalimageloader.core.ImageLoader;
import com.parse.ParseFile;
import com.parse.ParseImageView;


import java.util.ArrayList;
import java.util.List;

import mc.neuro.R;
import model.MobiFile;

/**
 * Created by Alvaro on 2/21/15.
 */
public class GridGalleryAdapter extends BaseAdapter {

    private static final String LOG_TAG = "LOG";
    Context context;
    LayoutInflater inflater;

    private List<MobiFile> stands= null;
    private ArrayList<MobiFile> arraylist;

    public GridGalleryAdapter(Context context,
                            List<MobiFile> standsapp) {
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
            view = inflater.inflate(R.layout.cell_gridgallery, null);
            // Locate the TextViews in listview_item.xml

            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            int width = displayMetrics.widthPixels;
            int height = displayMetrics.heightPixels;

            Log.i("WIDTH",String.valueOf(width));
            Log.i("HEIGHT",String.valueOf(height));

            // Locate the ImageView in listview_item.xml
            holder.image = (ParseImageView) view.findViewById(R.id.image);
            holder.image.getLayoutParams().height = (height - dpToPx(55)) / 3;
            holder.image.getLayoutParams().width = width / 3;


            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }






        if(stands.get(position).getParseFileV1()!=null){
            final ParseFile photoFile = stands.get(position).getParseFileV1();
            if (photoFile != null) {
                //Get singleton instance of ImageLoader
                ImageLoader imageLoader = ImageLoader.getInstance();
                //Load the image from the url into the ImageView.

                imageLoader.displayImage(photoFile.getUrl(), holder.image);


            }
            else{
                Log.i("LOG","LOG");
            }
            Log.i("LOG","LOG");

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
