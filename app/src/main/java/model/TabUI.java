package model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by Alvaro on 2/17/15.
 */

@ParseClassName("TabUI")
public class TabUI extends ParseObject {

    public Image getIcon() {
        return (Image)get("icon");
    }
    public String getNameTab() {
        return getString("name");
    }
    public Integer getPositionTab() {
        return getInt("position");
    }
    public ViewUI getViewPointer(){return (ViewUI)get("viewPointer");}

    public static ParseQuery<TabUI> getQuery() {
        return ParseQuery.getQuery(TabUI.class);
    }
}
