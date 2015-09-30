package model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

/**
 * Created by Alvaro on 2/16/15.
 */
@ParseClassName("Rating")
public class Rating extends ParseObject {

    public Boolean isActive() {
        return getBoolean("active");
    }
    public String getComment() {
        return getString("comment");
    }

    public Event getEvent() {
        return (Event)get("event");
    }
    public String getNameSection() {
        return getString("nameSection");
    }
    public String getIdEvent() {
        return getString("id_event");
    }
    public Integer getValue() {
        return getInt("value");
    }
    public void setEvent(Event event) {
        put("event", event);
    }

    public void setRating(Integer value) {
        put("value", value);
    }

    public void setComment(String comment) {
        put("comment", comment);
    }

    public void setType(String type) {
        put("type", type);
    }

    public void setUser(ParseUser user) {
        put("user", user);
    }


    public static ParseQuery<Rating> getQuery() {
        return ParseQuery.getQuery(Rating.class);
    }

}
