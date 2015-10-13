package model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

/**
 * Created by Alvaro on 3/23/15.
 */
@ParseClassName("PersonSoul")
public class PersonSoul extends ParseObject {

    public String getFirstName() {
        return getString("firstName");
    }
    public String getLastName() {
        return getString("lastName");
    }
    public List<Person> getPersons() {
        return getList("persons");
    }
    public List<ParseUser> getUsers() {
        return getList("users");
    }
    public static ParseQuery<PersonSoul> getQuery() {
        return ParseQuery.getQuery(PersonSoul.class);
    }
}
