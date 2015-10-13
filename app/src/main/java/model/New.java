package model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Date;
import java.util.List;

/**
 * Created by Alvaro on 2/16/15.
 */
@ParseClassName("New")
public class New extends ParseObject {

    public Boolean isActive() {
        return getBoolean("active");
    }
    public List<String> getChannels() {
        return getList("channels");
    }
    public String getContent() {
        return getString("content");
    }
    public String getContent2() {
        return getString("contentLg2");
    }
    public String getContent3() {
        return getString("contentLg3");
    }
    public String getDate() {
        return getString("date");
    }
    public Image getImage() {
        return (Image)get("image");
    }
    public Boolean isPush() {
        return getBoolean("isPush");
    }
    public Boolean isTouch() {
        return getBoolean("isTouch");
    }
    public Date getReleaseDate() {
        return getDate("releaseDate");
    }
    public String getSender() {
        return getString("sender");
    }
    public Integer getSortingAux() {
        return getInt("sortingAux");
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
    public List <Wall> getWalls() {
        return getList("walls");
    }

    public Boolean getisHidden() {
        return getBoolean("hidden");
    }
    public Integer getOrder() {
        return getInt("order");
    }




    public static ParseQuery<New> getQuery() {
        return ParseQuery.getQuery(New.class);
    }
}
