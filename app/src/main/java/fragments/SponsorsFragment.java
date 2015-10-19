package fragments;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import adapters.GridImageAdapter;
import mc.urolchi.R;
import model.Facade;
import model.MeetingApp;
import model.MobiFile;

/**
 * Created by Alvaro on 2/25/15.
 */
public class SponsorsFragment extends Fragment {


    public static MeetingApp mApp;
    public static MobiFile map;
    public static GridView gridview;


    public static SponsorsFragment newInstance(MeetingApp meetingApp) {

        // Instantiate a new fragment
        mApp= meetingApp; //Alfonso
        SponsorsFragment fragment = new SponsorsFragment();

        fragment.setRetainInstance(true);
        return fragment;

    }

    @Override
    public void onAttach(Activity activity) {


        super.onAttach(activity);




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View RootView = inflater.inflate(R.layout.sponsors_layout, container , false);

        gridview = (GridView) RootView.findViewById(R.id.gridView);





        return RootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        /*
        View v = mTabHost.getTabWidget().getChildAt(0);
        v.setBackgroundResource(R.drawable.programa);
*/

        if(mApp!=null){
            if(mApp.getCompaniesFacade()!=null){

                List<Facade> facades = mApp.getCompaniesFacade();

                ArrayList<Facade> facade1 = new ArrayList<>();
                for(Facade facade:facades){
                    if(!facade.getRole().equals("Organizadores")){
                        facade1.add(facade);
                    }
                }
                Log.i("MAPP", String.valueOf(facade1));
                gridview.setAdapter(new GridImageAdapter(getActivity(),facade1));

                gridview.setStretchMode( GridView.STRETCH_COLUMN_WIDTH );
                gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    public void onItemClick(AdapterView<?> parent, View v,
                                            int position, long id) {



                        ParseObject object = (ParseObject)(gridview.getItemAtPosition(position));


                            Facade stand = ParseObject.createWithoutData(Facade.class, object.getObjectId());

                            if(stand.getCompany().getDetails()!=null){
                                Fragment fragment = CompanyFragment.newInstance(stand,mApp,true);
                                final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                                ft.replace(R.id.container,fragment);
                                ft.addToBackStack(null);
                                ft.commit();
                            }

                            else if(stand.getCompany().getWeb()!=null && stand.getCompany().getDetails()==null){
                                String url = stand.getCompany().getWeb();
                                Intent i = new Intent(Intent.ACTION_VIEW);
                                i.setData(Uri.parse(url));
                                startActivity(i);
                            }
                            else {
                                Log.i("NADA","NADA");
                            }

                        }


                });
            }
        }


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

