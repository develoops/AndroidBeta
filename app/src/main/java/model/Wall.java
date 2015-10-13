package model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by Alvaro on 3/23/15.
 */
@ParseClassName("Wall")
public class Wall extends ParseObject {

    public List<New> getNews() {
        return getList("news");
    }

    public static ParseQuery<Wall> getQuery() {
        return ParseQuery.getQuery(Wall.class);
    }
}

