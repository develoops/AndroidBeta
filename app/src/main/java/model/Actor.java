package model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by Alvaro on 2/16/15.
 */
@ParseClassName("Actor")
public class Actor extends ParseObject {

    public Boolean isActive() {
        return getBoolean("active");
    }
    public String getRole() {
        return getString("charge");
    }
    public String getRole2() {
        return getString("chargeLg2");
    }
    public String getRole3() {
        return getString("chargeLg3");
    }
    public List <Company> getCompanies() {
        return getList("companies");
    }
    public List <Event> getEvents() {
        return getList("events");
    }
    public Person getPerson() {
        return (Person)get("person");
    }
    public String getType() {
        return getString("type");
    }




    public static ParseQuery<Actor> getQuery() {
        return ParseQuery.getQuery(Actor.class);
    }
}


