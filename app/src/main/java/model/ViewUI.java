package model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by Alvaro on 2/17/15.
 */
@ParseClassName("ViewUI")
public class ViewUI extends ParseObject {

    public String getnameView() {
        return getString("nameView");
    }
    public Boolean isActive() {
        return getBoolean("active");
    }

    public static ParseQuery<ViewUI> getQuery() {
        return ParseQuery.getQuery(ViewUI.class);
    }
}
