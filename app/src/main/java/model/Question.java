package model;

/**
 * Created by Alvaro on 10/20/15.
 */

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;



import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by Alvaro on 10/20/15.
 */
@ParseClassName("Question")
public class Question extends ParseObject {

    public String getName() {
        return getString("name");
    }
    public String getType() {
        return getString("type");
    }
    public static ParseQuery<Question> getQuery() {
        return ParseQuery.getQuery(Question.class);
    }
}

