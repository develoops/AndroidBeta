package fragments;


import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import adapters.GridGalleryAdapter;
import mc.neuro.R;
import model.MeetingApp;
import model.MobiFile;
import utils.TouchImageView;

/**
 * Created by Alvaro on 2/25/15.
 */
public class GalleryFragment extends Fragment {


    public static MeetingApp mApp;
    public static MobiFile map;
    public static GridView gridview;
    ArrayList<MobiFile> mobiFiles = new ArrayList<>();

    public static GalleryFragment newInstance(MeetingApp meetingApp) {

        // Instantiate a new fragment
        mApp= meetingApp; //Alfonso
        GalleryFragment fragment = new GalleryFragment();

        fragment.setRetainInstance(true);
        return fragment;

    }

    @Override
    public void onAttach(Activity activity) {


        super.onAttach(activity);




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View RootView = inflater.inflate(R.layout.gallery_layout, container , false);
        gridview = (GridView) RootView.findViewById(R.id.gridView);


        ParseQuery<MobiFile> query = ParseQuery.getQuery(MobiFile.class);

        query.whereEqualTo("subtype","gallery");

        query.findInBackground(new FindCallback<MobiFile>() {
            @Override
            public void done(List<MobiFile> list, ParseException e) {
                gridview.setAdapter(new GridGalleryAdapter(getActivity(),list));

                gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        MobiFile mobiFile =  ParseObject.createWithoutData(MobiFile.class, ((ParseObject) GalleryFragment.gridview.getItemAtPosition(position)).getObjectId());
                        map=mobiFile;
                        final Dialog dialogo = new Dialog(GalleryFragment.this.getActivity());
                        dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                        dialogo.setContentView(R.layout.map_box_layout);
                        final Button done = (Button) dialogo.findViewById(R.id.btn_done_image_dialog);
                        TouchImageView mapadialog = (TouchImageView) dialogo.findViewById(R.id.image_dialog);
                        mapadialog.setMaxZoom(3f);
                        mapadialog.setMinZoom(1f);
                        if (map!= null) {
                            ImageLoader imageLoader = ImageLoader.getInstance();
                            //Load the image from the url into the ImageView.
                            imageLoader.displayImage(map.getParseFileV1().getUrl(), mapadialog);
                        }


                        dialogo.getWindow().getAttributes().width = RelativeLayout.LayoutParams.MATCH_PARENT;
                        done.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogo.dismiss();

                            }
                        });
                        dialogo.show();

                    }
                });

            }
        });




        return RootView;
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

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    // handle back button's click listener


                    return true;
                }
                return false;
            }

        });

        }

    }

