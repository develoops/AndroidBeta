package model;

import android.os.Parcel;
import android.os.Parcelable;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Alvaro on 2/16/15.
 */
@ParseClassName("MeetingApp")
public class MeetingApp extends ParseObject {



    public Boolean isActive() {
        return getBoolean("active");
    }
    public List<String> getActorCharges() {
        return getList("actorCharges");
    }
    public List<String> getActorCharges2() {
        return getList("actorChargesLg2");
    }
    public List<String> getActorCharges3() {
        return getList("actorChargesLg3");
    }
    public List<String> getChannels() {
        return getList("channels");
    }
    public List <Facade> getCompaniesFacade() {
        return getList("companies");
    }
    public String getDetails() {
        return getString("details");
    }
    public String getDetails2() {
        return getString("detailsLg2");
    }
    public String getDetails3() {
        return getString("detailsLg3");
    }
    public Date getEndDate() {
        return getDate("endDate");
    }
    public List<String> getEventLoadTags() {
        return getList("eventTagsLoad");
    }
    public List<String> getEventLoadTagsLg2() {
        return getList("eventTagsLoadLg2");
    }
    public List<String> getEventLoadTagsLg3() {
        return getList("eventTagsLoadLg3");
    }
    public List<String> getEventTypeLoad() {
        return getList("eventTypesLoad");
    }
    public List<String> getEventTypeLoadLg2() {
        return getList("eventTypesLoadLg2");
    }
    public List<String> getEventTypeLoadLg3() {
        return getList("eventTypesLoadLg3");
    }
    public String getEventTags() {
        return getString("eventTags");
    }
    public String getEventTags2() {
        return getString("eventTagsLg2");
    }
    public String getEventTags3() {
        return getString("eventTagsLg3");
    }
    public List <Event> getEvents() {
        return getList("events");
    }
    public List<MobiFile> getGallery() {
        return getList("gallery");
    }
    public MobiFile getheaderImage(){
        return (MobiFile)get("headerImage");
    }
    public MobiFile getheaderVideo(){
        return (MobiFile)get("headerVideo");
    }
    public CompanyApp getHostedCompany() {
        return (CompanyApp)get("hostingCompanyApp");
    }
    public MobiFile getIcon(){
        return (MobiFile)get("icon");
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
    public List <MobiFile> getFiles() {
        return getList("library");
    }
    public String getName() {
        return getString("name");
    }
    public String getName2() {
        return getString("nameLg2");
    }
    public String getName3() {
        return getString("nameLg3");
    }
    public ColorPalette getColorPalette() {
        return (ColorPalette)get("palette");
    }
    public List<String> getPersonTags() {
        return getList("personTagsLoad");
    }
    public List <Person> getPersons() {
        return getList("persons");
    }
    public Place getPlaceParse() {
        return (Place)get("place");
    }
    public List <Poll> getPolls() {
        return getList("polls");
    }
    public Integer getSize() {
        return getInt("size");
    }
    public MobiFile getSplashMeeting(){
        return (MobiFile)get("splashMeeting");
    }
    public Date getStartDate() {
        return getDate("startDate");
    }
    public String getStatus() {
        return getString("status");
    }
    public Boolean isSubMeeting() {
        return getBoolean("subMeeting");
    }
    public List <MeetingApp> getSubMeetingApps() {
        return getList("subMeetings");
    }
    public List <Place> getVenues() {
        return getList("venues");
    }
    public List <View> getViews() {
        return getList("views");
    }
    public List <Wall> getWalls() {
        return getList("walls");
    }

    /*
    public List<Company> getCompanies() {
        return getList("companies");
    }
    public ParseFile getIconParse() {
       return getParseFile("icon");
   }
    public List <New> getNews() {
        return getList("news");
    }
    public Integer getOrder() {
        return getInt("order");
    }

    public Image getSplash() {
        return (Image)get("splashMeeting");
    }
    public List <Stand> getStands() {
        return getList("stands");
    }
    public TabBarUI getTabBar() {
        return (TabBarUI)get("tabBarMeeting");
    }




   /*
    public Date getStartDate() {
        return getDate("fechaInicio");
    }

    public Date getEndDate() {
        return getDate("fechaFin");
    }

    public ParseFile getIcon() {
        return getParseFile("icono");
    }

    public String getIdNotifications() {
        return getString("idNotificaciones");
    }

    public List <Company> getInstitutions() {
        return getList("instituciones");
    }

    public Place getPlace() {
        return (Place)get("lugar");
    }

    public Boolean getToShow() {
        return getBoolean("mostrar");
    }

    public List <New> getNews() {
        return getList("novedades");
    }

    public Integer getOrder() {
        return getInt("orden");
    }

    public List <Actor> getCharacters() {
        return getList("personajes");
    }



    public List <Stand> getStands() {
        return getList("stands");
    }

    public List getStringTabs() {
        return getList("stringsTabs");
    }

    public List <MeetingApp> getSubCongress() {
        return getList("subcongresos");
    }

    public String getTabs() {
        return getString("tabs");
    }

    public String getKind() {
        return getString("tipo");
    }

    public String getName() {
        return getString("titulo");
    }

  */
    public static ParseQuery<MeetingApp> getQuery() {
        return ParseQuery.getQuery(MeetingApp.class);
    }



}
