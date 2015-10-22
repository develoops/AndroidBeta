package model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by Alvaro on 10/20/15.
 */
@ParseClassName("Question2Article")
public class Question2Article extends ParseObject {

    public Article getItem() {
        return (Article)get("item");
    }
    public Question getQuestion() {
        return (Question)get("question");
    }

    public Number getSequencePosition() {
        return getNumber("sequencePosition");
    }
    public String getType() {
        return getString("type");
    }
    public static ParseQuery<Question2Article> getQuery() {
        return ParseQuery.getQuery(Question2Article.class);
    }
}
