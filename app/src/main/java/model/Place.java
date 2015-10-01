package model;

import com.parse.ParseClassName;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by Alvaro on 2/16/15.
 */

@ParseClassName("Place")
public class Place extends ParseObject {



    public Boolean isActive() {
        return getBoolean("active");
    }
    public String getAddress() {
        return getString("adress");
    }
    public Place getCityPlace(){return (Place)get("city");}
    public Place getCountryPlace(){return (Place)get("country");}
    public ParseGeoPoint getGeoPoint() {
        return getParseGeoPoint("geoPoint");
    }
    public String getGooglemaps() {
        return getString("googleMaps");
    }
    public List<MobiFile> getMaps() {
        return getList("maps");
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
    public Place getState(){return (Place)get("state");}
    public String getTripAdvisor() {
        return getString("tripadvisor");
    }
    public String getType() {
        return getString("type");
    }
    public Integer getX() {
        return getInt("x");
    }
    public Integer getY() {
        return getInt("y");
    }

/*
    public String getCity() {
        return getString("city");
    }
    public String getDescription() {
        return getString("descriptionPlace");
    }
    public Image getMap() {
        return (Image)get("map");
    }
    public String getCountry() {
        return getString("country");
    }
    public String getVenue() {
        return getString("venue");
    }
    public String getRoom() {
        return getString("room");
    }
    public String getHeadQuarter() {
        return getString("name");
    }
    public String getKind() {
        return getString("tipo");
    }
*/
    public static ParseQuery<Place> getQuery() {
        return ParseQuery.getQuery(Place.class);
    }
}
