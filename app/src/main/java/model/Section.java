package model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by Alvaro on 2/17/15.
 */
@ParseClassName("Section")
public class Section extends ParseObject {

    public Boolean isActive() {
        return getBoolean("active");
    }
    public String getnameSection() {
        return getString("nameSection");
    }




    public static ParseQuery<Section> getQuery() {
        return ParseQuery.getQuery(Section.class);
    }
}
