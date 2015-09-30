package fragments;


import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import adapters.GridDocumentsAdapter;
import adapters.GridGalleryAdapter;
import adapters.GridImageAdapter;
import adapters.GridMediaAdapter;
import mc.soched2015.R;
import model.Actor;
import model.Facade;
import model.MeetingApp;
import model.MobiFile;
import utils.TouchImageView;

/**
 * Created by Alvaro on 2/25/15.
 */
public class MoreFragment extends Fragment {

    public static MeetingApp mApp;
    public static MobiFile map;
    public static ListView listView;
    ArrayList<MobiFile> mFiles = new ArrayList<>();
    GridDocumentsAdapter adapter;

    public static MoreFragment newInstance(MeetingApp meetingApp) {

        // Instantiate a new fragment
        mApp= meetingApp; //Alfonso
        MoreFragment fragment = new MoreFragment();

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
        listView = (ListView) RootView.findViewById(R.id.commonListView);/// crear el
        // gridview a partir del elemento del xml gridview
        //final Button button = (Button) RootView.findViewById(R.id.comercialmap);

        //button.setText("Mapa Comercial");






        return RootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        /*
        View v = mTabHost.getTabWidget().getChildAt(0);
        v.setBackgroundResource(R.drawable.programa);
*/


        ParseQuery<MobiFile> query = ParseQuery.getQuery(MobiFile.class);
        query.whereEqualTo("type","doc");
        query.findInBackground(new FindCallback<MobiFile>() {
              @Override
              public void done(List<MobiFile> mobiFiles, ParseException e) {
                  adapter = new GridDocumentsAdapter(getActivity(),R.layout.cell_event,mobiFiles);
                  listView.setAdapter(adapter);

                  listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                      @Override
                      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                          ParseObject object = (ParseObject)(listView.getItemAtPosition(position));
                          MobiFile mobiFile= ParseObject.createWithoutData(MobiFile.class, object.getObjectId());
                          Fragment fragment = DocumentDetailFragment.newInstance(mobiFile);
                          final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                          ft.replace(R.id.container,fragment);
                          ft.addToBackStack(null);
                          ft.commit();
                      }
                  });

              }
         });
        /*
        query.whereEqualTo("subtype","gallery");
        query.findInBackground(new FindCallback<MobiFile>() {
            @Override
            public void done(List<MobiFile> mobiFiles, ParseException e) {
                    mFiles = (ArrayList<MobiFile>) mobiFiles;

                gridview.setAdapter(new GridGalleryAdapter(getActivity(),mFiles));
                gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                        MobiFile mobiFile = (MobiFile)(gridview.getItemAtPosition(position));

                        final Dialog dialogo = new Dialog(getActivity());
                        dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                        dialogo.setContentView(R.layout.map_box_layout);
                        final Button done = (Button) dialogo.findViewById(R.id.btn_done_image_dialog);
                        TouchImageView mapadialog = (TouchImageView) dialogo.findViewById(R.id.image_dialog);
                        mapadialog.setMaxZoom(3f);
                        mapadialog.setMinZoom(1f);
                        if (mobiFile!= null) {
                            ImageLoader imageLoader = ImageLoader.getInstance();
                            //Load the image from the url into the ImageView.
                            imageLoader.displayImage(mobiFile.getParseFileV1().getUrl(), mapadialog);
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
        });*/







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
