package model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by Alvaro on 10/20/15.
 */
@ParseClassName("Question2Question")
public class Question2Question extends ParseObject {

    public Question getSubQuestion() {
        return (Question)get("subQuestion");
    }
    public Question getSuperQuestion() {
        return (Question)get("superQuestion");
    }
    public Number getSequencePosition() {
        return getNumber("sequencePosition");
    }
    public static ParseQuery<Question2Question> getQuery() {
        return ParseQuery.getQuery(Question2Question.class);
    }
}
