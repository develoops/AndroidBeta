package model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by Alvaro on 2/17/15.
 */

@ParseClassName("TabBarUI")
public class TabBarUI extends ParseObject {

    public Boolean isActive() {
        return getBoolean("active");
    }
    public List<TabUI> getTabs() {
        return getList("tabs");
    }

    public static ParseQuery<TabBarUI> getQuery() {
        return ParseQuery.getQuery(TabBarUI.class);
    }
}
