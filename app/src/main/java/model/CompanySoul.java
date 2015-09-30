package model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by Alvaro on 3/23/15.
 */

@ParseClassName("CompanySoul")
public class CompanySoul extends ParseObject {
    public List<Company> getCompanies() {
        return getList("companies");
    }
    public Place getCountry() {
        return (Place)get("country");
    }
    public String getName() {
        return getString("name");
    }
    public String getType() {
        return getString("type");
    }

    public static ParseQuery<CompanySoul> getQuery() {
        return ParseQuery.getQuery(CompanySoul.class);
    }
}
