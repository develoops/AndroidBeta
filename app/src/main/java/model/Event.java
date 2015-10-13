package model;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Date;
import java.util.List;

/**
 * Created by alvaro on 04-12-14.
 */
@ParseClassName("Event")
public class Event extends ParseObject {


    public Boolean isActive() {
        return getBoolean("active");
    }
    public List <Actor> getActors() {
        return getList("actors");
    }
    public List <Event> getAnidateEvents() {
        return getList("anidateEvents");
    }
    public List <Product> getCatalog() {
        return getList("catalog");
    }
    public List <Facade> getCompanies() {
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
    public List<MobiFile> getGallery() {
        return getList("gallery");
    }
    public MobiFile getheaderImage(){
        return (MobiFile)get("headerImage");
    }
    public MobiFile getheaderVideo(){
        return (MobiFile)get("headerVideo");
    }
    public List <MeetingApp> getMeetingApps() {
        return getList("hostingMeetingApps");
    }
    public MobiFile getIcon(){
        return (MobiFile)get("icon");
    }
    public List<MobiFile> getEventFiles() {
        return getList("library");
    }
    public ColorPalette getColorPalette() {
        return (ColorPalette)get("palette");
    }
    public Place getPlace() {
        return (Place)get("place");
    }
    public Integer getSortingAux() {
        return getInt("sortingAux");
    }
    public Boolean isSponsored() {
        return getBoolean("sponsored");
    }
    public Date getStartDate() {
        return getDate("startDate");
    }
    public String getTags() {
        return getString("tags");
    }
    public String getTags2() {
        return getString("tagsLg2");
    }
    public String getTags3() {
        return getString("tagsLg3");
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
    public String getType() {
        return getString("type");
    }


    /*
    public Image getMap() {
        return (Image)get("map");
    }
    public ParseFile getEventPictureHeader() {
        return (ParseFile)get("headerImage");
    }
    public ParseFile getEventProfilePicture() {
        return (ParseFile)get("profileImage");
    }
    public ColorPalette getPalette() {
        return (ColorPalette)get("palette");
    }
    public String getTitleEvent() {
        return getString("title");
    }
    public ToolbarUI getToolbar() {
        return (ToolbarUI)get("toolBar");
    }
    public String getTopic() {
        return getString("topic");
    }
    public Integer getOrder() {
        return getInt("order");
    }
    public String getDescription() {
        return getString("descriptionEvent");
    }

*/
    public static ParseQuery<Event> getQuery() {
        return ParseQuery.getQuery(Event.class);
    }

}