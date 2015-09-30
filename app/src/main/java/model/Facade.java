package model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by Alvaro on 3/23/15.
 */
@ParseClassName("Facade")
public class Facade extends ParseObject {

    public Boolean isActive() {
        return getBoolean("active");
    }
    public Company getCompany(){
        return (Company)get("company");
    }
    public List<CompanyApp> getCompanyApps() {
        return getList("companyApps");
    }
    public List <Event> getEvents() {
        return getList("events");
    }
    public List <MeetingApp> getMeetingApps() {
        return getList("meetingApps");
    }
    public String getRole() {
        return getString("role");
    }
    public String getRole2() {
        return getString("roleLg2");
    }
    public String getRole3() {
        return getString("roleLg3");
    }
    public static ParseQuery<Facade> getQuery() {
        return ParseQuery.getQuery(Facade.class);
    }
}
