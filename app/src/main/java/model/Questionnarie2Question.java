package model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by Alvaro on 10/20/15.
 */
@ParseClassName("Questionnarie2Question")
public class Questionnarie2Question extends ParseObject {

    public Question getQuestion() {
        return (Question)get("question");
    }
    public Questionnaire getQuestionnaire() {
        return (Questionnaire)get("questionnaire");
    }
    public static ParseQuery<Questionnarie2Question> getQuery() {
        return ParseQuery.getQuery(Questionnarie2Question.class);
    }
}
