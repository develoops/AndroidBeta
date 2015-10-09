package fragments;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseImageView;
import com.parse.ParseQuery;

import java.util.List;

import mc.soched.R;
import mc.soched.myApp;
import model.Actor;
import model.Company;
import model.CompanyApp;
import model.Facade;
import model.MeetingApp;
import model.MobiFile;
import model.Stand;
import model.TabUI;
import utils.MUtil;

/**
 * Created by Alvaro on 2/25/15.
 */
public class LoadDataFragment extends Fragment {

	public static List<MeetingApp> meetingApps;
	public static List<TabUI> tabUIs;
	public static List<Actor> staff;
	public static List<MobiFile> files;
	public static Stand stand, mCongress;
	public myApp myapp;
	public static CompanyApp company;
	public static ParseImageView splash;
	public static Integer i = 0;
	public static ProgressBar bar;
	public static Boolean flag = false;
	public static View RootView;
	public static Company com, mobiCongress;
	public static Facade facade;

	public static LoadDataFragment newInstance() {
		// Instantiate a new fragment
		LoadDataFragment fragment = new LoadDataFragment();
		fragment.setRetainInstance(true);
		return fragment;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.myapp = (myApp) getActivity().getApplicationContext();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Retain this fragment across configuration changes.
		setRetainInstance(true);
		if (myapp.isFirstTime()) {
			if (!myapp.checkConnection()) {
				//TODO Alert no internet.
				return;
			}
			Log.e(getClass().getName(), "first time load data");
			loadServerDataAndSaveLocal();
			myapp.setFirstTime();
		} else {
			Log.e(getClass().getName(), "> 1 time load data");
			loadDataAndUpdateLocal();
		}
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View RootView = inflater.inflate(R.layout.splash_layout, container, false);
		bar = (ProgressBar) RootView.findViewById(R.id.progressBar);
		ImageView splash_first = (ImageView) RootView.findViewById(R.id.splash_first);
		splash_first.setVisibility(View.VISIBLE);
		return RootView;
	}

	//From second time:
	// if connection available, load server data, update local
	// if not, load local data, finish.
	private void loadDataAndUpdateLocal() {


		Log.e(getClass().getName(), "call loadServerDataAndSaveLocal");
		ParseQuery<CompanyApp> query = ParseQuery.getQuery(CompanyApp.class);
		MUtil.isUpdateLocal = true;
		if (!myapp.checkConnection()) {
			//If no internet connection, query local.
			//MUtil.isUpdateLocal = false;//Load from local, so dont need to update.
			//query.fromLocalDatastore();
            new AlertDialog.Builder(getActivity())
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Warning")
                    .setMessage("You need to connect to internet")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            getActivity().finish();
                        }

                    })
                    .show();
		}
		query.include("companies");
		query.include("companies.company");
		query.include("companies.company.actors");
		query.include("companies.company.actors.person");
        query.include("companies.company.actors.person.profileImage");
		query.include("companies.company.headerImage");
		query.include("companies.company.logo");
		query.include("companies.company.location.country");
		//query.include("companies.company.place");
		query.include("meetingApps");
		query.include("meetingApps.companies");
		query.include("meetingApps.companies.company");
		query.include("meetingApps.companies.company.location");
        query.include("meetingApps.companies.company.headerImage");
        query.include("meetingApps.companies.company.logo");
		query.include("meetingApps.companies.headerImage");
		query.include("meetingApps.companies.logo");
		query.include("meetingApps.events");
		query.include("meetingApps.events.actors.person");
		query.include("meetingApps.events.actors.person.profileImage");
		query.include("meetingApps.events.icon");
		query.include("meetingApps.events.palette");
		query.include("meetingApps.events.place");
		query.include("meetingApps.library");
		query.include("meetingApps.icon");
        query.include("meetingApps.palette");
		query.include("meetingApps.place");
		query.include("meetingApps.persons");
		query.include("meetingApps.persons.actors");
		query.include("meetingApps.persons.actors.companies");
        query.include("meetingApps.persons.actors.person");
        query.include("meetingApps.persons.company");
        query.include("meetingApps.persons.company.location");
        query.include("meetingApps.persons.company.location.country");
        query.include("meetingApps.persons.profileImage");
		//query.include("meetingApps.persons.actors.companies.company");
		query.include("meetingApps.splashMeeting");
		query.include("meetingApps.views");
		query.include("meetingApps.walls");
		query.include("meetingApps.walls.news");
		query.include("companySplash");
		query.include("logo");
		query.include("palette");
		query.include("views");
		query.whereEqualTo("active", true);
		query.findInBackground(
				new FindCallback<CompanyApp>() {
					@Override
					public void done(final List<CompanyApp> companyApps, ParseException e) {
						if (e != null && companyApps != null && companyApps.size() > 0)
							return;
						company = companyApps.get(0);
						List<Facade> facades = company.getCompaniesFacade();

						for (Facade facade1 : facades) {
							if (facade1.getRole().equals("Organizacion") || facade1.getRole().equals("Organizadores")) {
								com = facade1.getCompany();
                                if(com.getheaderImage()!=null){
                                    com.getheaderImage().getParseFileV1().getDataInBackground();
                                }


								if (com.getFiles() != null) {
									files = facade.getCompany().getFiles();
								}
								if (com.getActors() != null) {
									staff = com.getActors();
								}
							}

                            /*
							if (facade1.getRole().equals("mCongress")) {
								mobiCongress = facade1.getCompany();
								mobiCongress.getheaderImage().getParseFileV1().getDataInBackground();
								mobiCongress.getLogo().getParseFileV1().getDataInBackground();
								//com.getLogo().getParseFileV1().getDataInBackground();
								if (mobiCongress.getFiles() != null) {
									files = mobiCongress.getFiles();
								}
							}*/
						}
						meetingApps = company.getMeetingApps();
						for (MeetingApp meetingApp : meetingApps) {
                            if(meetingApp.getIcon()!=null){
                                meetingApp.getIcon().getParseFileV1().getDataInBackground();
                            }
							if(meetingApp.getSplashMeeting()!=null){
                                meetingApp.getSplashMeeting().getParseFileV1().getDataInBackground();
                            }

                            if(meetingApp.getCompaniesFacade()!=null){
                                for (Facade facade1 : meetingApp.getCompaniesFacade()) {
                                    if(facade1.getCompany().getLogo()!=null){
                                        if(facade1.getCompany().getLogo().getParseFileV1()!=null){
                                            facade1.getCompany().getLogo().getParseFileV1().getDataInBackground();
                                        }
                                    }

                                    if(facade1.getCompany().getheaderImage()!=null){
                                        if(facade1.getCompany().getheaderImage().getParseFileV1()!=null){
                                            facade1.getCompany().getheaderImage().getParseFileV1().getDataInBackground();
                                        }
                                    }



                                }
                            }

						}


						//TODO update local database.
						// Remove the previously local db.
						newFragment();
					}
				}

		);
	}

	//First time: Load server data and save to local.

	private void loadServerDataAndSaveLocal() {
		Log.e(getClass().getName(), "call loadServerDataAndSaveLocal");
        if (!myapp.checkConnection()) {
            //If no internet connection, query local.
            //MUtil.isUpdateLocal = false;//Load from local, so dont need to update.
            //query.fromLocalDatastore();
            new AlertDialog.Builder(getActivity())
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Warning")
                    .setMessage("You need to connect to internet")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            getActivity().finish();
                        }

                    })
                    .show();
        }
		ParseQuery<CompanyApp> query = ParseQuery.getQuery(CompanyApp.class);
		query.include("companies");
		query.include("companies.company");
		query.include("companies.company.actors");
		query.include("companies.company.actors.person");
        query.include("companies.company.actors.person.profileImage");
		query.include("companies.company.headerImage");
		query.include("companies.company.logo");
		query.include("companies.company.location.country");
		//query.include("companies.company.place");
		query.include("meetingApps");
		query.include("meetingApps.companies");
		query.include("meetingApps.companies.company");
		query.include("meetingApps.companies.company.location");
        query.include("meetingApps.companies.company.headerImage");
        query.include("meetingApps.companies.company.logo");
		query.include("meetingApps.companies.headerImage");
		query.include("meetingApps.companies.logo");
		query.include("meetingApps.events");
		query.include("meetingApps.events.actors.person");
		query.include("meetingApps.events.actors.person.profileImage");
		query.include("meetingApps.events.icon");
		query.include("meetingApps.events.palette");
		query.include("meetingApps.events.place");
		query.include("meetingApps.library");
		query.include("meetingApps.icon");
        query.include("meetingApps.palette");
		query.include("meetingApps.place");
		query.include("meetingApps.persons");
		query.include("meetingApps.persons.actors");
        query.include("meetingApps.persons.actors.person");
		query.include("meetingApps.persons.actors.companies");
        query.include("meetingApps.persons.company");
        query.include("meetingApps.persons.company.location");
        query.include("meetingApps.persons.company.location.country");
        query.include("meetingApps.persons.profileImage");
		//query.include("meetingApps.persons.actors.companies.company");
		query.include("meetingApps.splashMeeting");
		query.include("meetingApps.views");
		query.include("meetingApps.walls");
		query.include("meetingApps.walls.news");
		query.include("companySplash");
		query.include("logo");
		query.include("palette");
		query.include("views");
		query.whereEqualTo("active", true);
		query.findInBackground(new FindCallback<CompanyApp>() {
			@Override
			public void done(List<CompanyApp> companyApps, ParseException e) {
				if (e != null)
					return;
				company = companyApps.get(0);
				List<Facade> facades = company.getCompaniesFacade();

				for (Facade facade1 : facades) {
					if (facade1.getRole().equals("Organizacion") || facade1.getRole().equals("Organizadores")) {
						com = facade1.getCompany();
                        if(com.getheaderImage()!=null){
                            com.getheaderImage().getParseFileV1().getDataInBackground();
                        }

						if (com.getFiles() != null) {
							files = facade.getCompany().getFiles();
						}
						if (com.getActors() != null) {
							staff = com.getActors();
						}
					}

                    /*
					if (facade1.getRole().equals("mCongress")) {
						mobiCongress = facade1.getCompany();
						mobiCongress.getheaderImage().getParseFileV1().getDataInBackground();
						mobiCongress.getLogo().getParseFileV1().getDataInBackground();
						//com.getLogo().getParseFileV1().getDataInBackground();
						if (mobiCongress.getFiles() != null) {
							files = mobiCongress.getFiles();
						}
					}*/
				}
				meetingApps = company.getMeetingApps();
				for (MeetingApp meetingApp : meetingApps) {
                    if(meetingApp.getIcon()!=null){
                        meetingApp.getIcon().getParseFileV1().getDataInBackground();
                    }
                    if(meetingApp.getSplashMeeting()!=null){
                        meetingApp.getSplashMeeting().getParseFileV1().getDataInBackground();
                    }

                    if(meetingApp.getCompaniesFacade()!=null){
                        for (Facade facade1 : meetingApp.getCompaniesFacade()) {
                            facade1.getCompany().getLogo().getParseFileV1().getDataInBackground();
                            facade1.getCompany().getheaderImage().getParseFileV1().getDataInBackground();
                        }
                    }
				}
                newFragment();
				//Save to local


			}

		});
	}

	private void newFragment() {
		MUtil.stopTimer(getClass().getName());
		if (meetingApps != null) {
			Log.i("boolean", String.valueOf(myapp.isFirstTime()));
			myapp.setSecondTime();
			ViewPagerFragment loadDataFragment = new ViewPagerFragment();
			if (getActivity() != null) {

				FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
				ft.replace(R.id.container, loadDataFragment, "ViewPager");
				ft.commitAllowingStateLoss();
				bar.setVisibility(View.INVISIBLE);
			} else {
			}
		} else {
		}
	}

/*
private void firstDataLoad(){



     //Download data from active CompanyApp
     ParseQuery<CompanyApp> query = ParseQuery.getQuery(CompanyApp.class);

     query.include("aboutCompany");
     query.include("aboutCompany.company");
     query.include("aboutCompany.company.place");
     query.include("companySplash");
     query.include("companyFiles");
     query.include("mCongress");
     query.include("mCongress.company");
     query.include("mCongress.company.place");
     query.include("palette");
     query.include("staffCompany");
     query.include("staffCompany.person");
     query.include("staffCompany.company");
     query.include("tabBarCompany.tabs");
     query.include("tabBarCompany.tabs.viewPointer");

     query.include("meetingApps");
     query.include("meetingApps.events");
     query.include("meetingApps.events.actors");
     query.include("meetingApps.events.actors.events");
     query.include("meetingApps.events.actors.person");
     query.include("meetingApps.events.actors.person.place");
     query.include("meetingApps.events.actors.company");
     query.include("meetingApps.events.anidateEvents");
     query.include("meetingApps.events.anidateEvents.actors");
     query.include("meetingApps.events.anidateEvents.actors.person");
     query.include("meetingApps.events.anidateEvents.actors.person.place");
     query.include("meetingApps.events.anidateEvents.library");
     query.include("meetingApps.events.anidateEvents.gallery");
     query.include("meetingApps.events.anidateEvents.place");
     query.include("meetingApps.events.anidateEvents.place.map");
     query.include("meetingApps.events.anidateEvents.palette");

     query.include("meetingApps.events.library");
     query.include("meetingApps.events.gallery");
     query.include("meetingApps.events.place.map");
     query.include("meetingApps.events.toolBar");
     query.include("meetingApps.events.place");
     query.include("meetingApps.events.map");

     query.include("meetingApps.companies");
     query.include("meetingApps.companies.stand");
     query.include("meetingApps.library");
     query.include("meetingApps.news");
     query.include("meetingApps.palette");

     query.include("meetingApps.persons");
     query.include("meetingApps.persons.actors");
     query.include("meetingApps.persons.actors.person");
     //query4.include("meetingApps.persons.actors.person.place");
     query.include("meetingApps.persons.actors.company");

     query.include("meetingApps.place");
     query.include("meetingApps.polls");
     query.include("meetingApps.splashMeeting");

     query.include("meetingApps.stands");
     query.include("meetingApps.stands.company");
     query.include("meetingApps.stands.company.place");

     query.include("meetingApps.subMeetings");
     query.include("meetingApps.tabBarMeeting.tabs");
     query.include("meetingApps.tabBarMeeting.tabs.viewPointer");



     query.whereEqualTo("active",true);
     query.getFirstInBackground(new GetCallback<CompanyApp>() {
         @Override
         public void done(final CompanyApp companyApp, ParseException e) {
             if(e==null){


                 company = companyApp;
                 meetingApps = companyApp.getMeetingApps();
                 if(companyApp.getTabBarUI()!=null){
                     tabUIs = companyApp.getTabBarUI().getTabs();
                 }
                 else {

                 }
                 if(companyApp.getAboutCompany()!=null){
                     stand = companyApp.getAboutCompany();
                 }
                 if(companyApp.getStaffCompany()!=null){
                     staff = companyApp.getStaffCompany();

                 }

                 if(companyApp.getFiles()!=null){
                     files = companyApp.getFiles();
                 }
                 for(MeetingApp meetingApp:meetingApps){

         Log.i("RATE",meetingApp.getNameParse());
         if(meetingApp.getIconParse()!=null){
             meetingApp.getIconParse().getDataInBackground();
         }
         if(meetingApp.getSplash()!=null){
             meetingApp.getSplash().getParseFile().getDataInBackground();
         }


         List <Event> events = meetingApp.getEvents();
         if(events!=null){
             for(Event event:events){
                 if(event.getEventProfilePicture()!=null){
                     event.getEventProfilePicture().getDataInBackground();
                 }
                 else {

                 }


             }
         }

     }

     if(staff != null){
         for(Actor actor:staff){
             if(actor.getPerson().getProfilePicture()!=null){
                 actor.getPerson().getProfilePicture().getDataInBackground();
             }




         }

     }

     if(companyApp.getAboutCompany()!=null){
         companyApp.getAboutCompany().getCompanyHeader().getDataInBackground();
     }

     if(companyApp.getAboutCompany()!=null){
         companyApp.getAboutCompany().getCompanyLogo().getDataInBackground();
     }


     if(companyApp.getCompanySplash().getParseFile()!=null){
         companyApp.getCompanySplash().getParseFile().getDataInBackground();
     }




     companyApp.unpinInBackground("company",new DeleteCallback() {
         @Override
         public void done(ParseException e) {
             companyApp.pinInBackground("company", new SaveCallback() {
                 @Override
                 public void done(ParseException e) {

                 }
             });
         }
     });


     newFragment();


 }
 else {

 }
}
});



     ParseQuery<Person> query2 = ParseQuery.getQuery(Person.class);
     query2.include("actors");
     query2.findInBackground(new FindCallback<Person>() {
         @Override
         public void done(final List<Person> persons, ParseException e) {
             Person.unpinAllInBackground("persons", new DeleteCallback() {
                 @Override
                 public void done(ParseException e) {
                     Person.pinAllInBackground("persons", persons);
                 }
             });
             for(Person person:persons){
                 if(person.getProfilePicture()!=null){
                     person.getProfilePicture().getDataInBackground();
                 }

             }
         }
     });

     ParseQuery<Event> query3 = ParseQuery.getQuery(Event.class);
     query3.include("anidateEvents");
     query3.include("toolBar");
     query3.include("map");
     query3.include("place.map");

     query3.findInBackground(new FindCallback<Event>() {
         @Override
         public void done(final List<Event> events, ParseException e) {
             Event.unpinAllInBackground("events", new DeleteCallback() {
                 @Override
                 public void done(ParseException e) {
                     Event.pinAllInBackground("events",events);
                 }
             });
         }
     });

     ParseQuery<Image> image= ParseQuery.getQuery(Image.class);

     image.findInBackground(new FindCallback<Image>() {
         @Override
         public void done(final List<Image> images, ParseException e) {
             Image.unpinAllInBackground("images", new DeleteCallback() {
                 @Override
                 public void done(ParseException e) {
                     Image.pinAllInBackground("images",images);
                 }
             });
         }
     });


 }

*/
}

