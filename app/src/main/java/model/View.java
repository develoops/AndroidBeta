package model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by Alvaro on 3/23/15.
 */
@ParseClassName("View")
public class View extends ParseObject {

    public String getTitle() {
        return getString("title");
    }
    public String getTitleLg2() {
        return getString("titleLg2");
    }
    public String getTitleLg3() {
        return getString("titleLg3");
    }
    public String getnameView() {
        return getString("nameView");
    }
    public Integer getsortingAux() {
        return getInt("sortingAux");
    }

    public static ParseQuery<View> getQuery() {
        return ParseQuery.getQuery(View.class);
    }
}
