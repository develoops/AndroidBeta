package model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by Alvaro on 2/17/15.
 */
@ParseClassName("UIToolbar")
public class ToolbarUI extends ParseObject {

    public Boolean isActive() {
        return getBoolean("active");
    }
    public List<TabUI> getUITabs() {
        return getList("tabs");
    }
    public static ParseQuery<ToolbarUI> getQuery() {
        return ParseQuery.getQuery(ToolbarUI.class);
    }
}
