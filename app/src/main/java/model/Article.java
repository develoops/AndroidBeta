package model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by Alvaro on 10/20/15.
 */
@ParseClassName("Article")
public class Article extends ParseObject {


    public String getText() {
        return getString("text");
    }
    public String getType() {
        return getString("type");
    }

    public static ParseQuery<Article> getQuery() {
        return ParseQuery.getQuery(Article.class);
    }
}
