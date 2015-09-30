package model;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by Alvaro on 2/16/15.
 */
@ParseClassName("Stand")
public class Stand extends ParseObject {

    public Boolean isActive() {
        return getBoolean("active");
    }
    public ParseFile getCompanyHeader() {
        return getParseFile("companyHeader");
    }
    public ParseFile getCompanyLogo() {
        return getParseFile("companyLogo");
    }
    public Company getCompany() {
        return (Company) get("company");
    }
    public String getDescription() {
        return getString("descriptionStand");
    }

    public static ParseQuery<Stand> getQuery() {
        return ParseQuery.getQuery(Stand.class);
    }


}
