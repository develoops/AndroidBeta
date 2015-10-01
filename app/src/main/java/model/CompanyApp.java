package model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by Alvaro on 2/17/15.
 */
@ParseClassName("CompanyApp")

public class CompanyApp extends ParseObject {

    public Boolean isActive () {return getBoolean("active");}
    public List<String> getChannels() {
        return getList("channels");
    }
    public List <Facade> getCompaniesFacade() {
        return getList("companies");
    }
    public MobiFile getcompanySplash(){
        return (MobiFile)get("companySplash");
    }
    public Company getHostedCompany(){
        return (Company)get("hostedCompany");
    }
    public String getLanguage() {
        return getString("language1");
    }
    public String getLanguage2() {
        return getString("language2");
    }
    public String getLanguage3() {
        return getString("language3");
    }
    public MobiFile getLogo(){
        return (MobiFile)get("logo");
    }
    public List <MeetingApp> getMeetingApps() {
        return getList("meetingApps");
    }
    public ColorPalette getPalette() {
        return (ColorPalette)get("palette");
    }
    public String getTitle() {
        return getString("title");
    }
    public String getTitle2() {
        return getString("titleLg2");
    }
    public String getTitle3() {
        return getString("titleLg3");
    }
    public List <View> getViews() {
        return getList("views");
    }


    public Image getCompanySplash() {
        return (Image)get("companySplash");
    }
    public List<Actor> getStaffCompany() {
        return getList("staffCompany");
    }
    public TabBarUI getTabBarUI() {
        return (TabBarUI)get("tabBarCompany");
    }
    public List <MobiFile> getFiles() {
        return getList("companyFiles");
    }
    public Stand getmCongress (){
        return (Stand)get("mCongress");
    }
    public Stand getAboutCompany() {
        return (Stand)get("aboutCompany");
    }


    public static ParseQuery<CompanyApp> getQuery() {
        return ParseQuery.getQuery(CompanyApp.class);
    }
}
