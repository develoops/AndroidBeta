package model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

/**
 * Created by Alvaro on 10/27/15.
 */
@ParseClassName("Answer")
public class Answer extends ParseObject {

    public void setUser(ParseUser user) {
        put("user", user);
    }

    public void setPregunta(String pregunta) {
        put("pregunta", pregunta);
    }

    public void setRespuesta(String respuesta) {
        put("respuesta", respuesta);
    }


    public static ParseQuery<Answer> getQuery() {
        return ParseQuery.getQuery(Answer.class);
    }
}

