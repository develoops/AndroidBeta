package model;

/**
 * Created by Alvaro on 10/20/15.
 */

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Date;

/**
 * Created by Alvaro on 10/20/15.
 */
@ParseClassName("Questionnaire")
public class Questionnaire extends ParseObject {

    public String getName() {
        return getString("name");
    }
    public String getType() {
        return getString("type");
    }
    public Date getDate() {
        return getDate("showDate");
    }
    public static ParseQuery<Questionnaire> getQuery() {
        return ParseQuery.getQuery(Questionnaire.class);
    }
}

