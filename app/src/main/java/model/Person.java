package model;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by alvaro on 04-12-14.
 */
@ParseClassName("Person")
public class Person extends ParseObject {

    public Boolean isActive() {
        return getBoolean("active");
    }
    public List<Actor> getActors() {
        return getList("actors");
    }
    public String getBio() {
        return getString("bio");
    }
    public String getBio2() {
        return getString("bioLg2");
    }
    public String getBio3() {
        return getString("bioLg3");
    }
    public List <Product> getCatalog() {
        return getList("catalog");
    }
    public Company getCompany(){
        return (Company)get("company");
    }
    public String getFacebook() {
        return getString("facebook");
    }
    public String getFirstName() {
        return getString("firstName");
    }
    public List<MobiFile> getGallery() {
        return getList("gallery");
    }
    public MobiFile getheaderImage(){
        return (MobiFile)get("headerImage");
    }
    public List <MeetingApp> getMeetingApps() {
        return getList("hostingMeetingApps");
    }
    public String getLastName() {
        return getString("lastName");
    }
    public List <MobiFile> getFiles() {
        return getList("library");
    }
    public String getLinkedIn() {
        return getString("linkedin");
    }
    public String getMail() {
        return getString("mail");
    }
    public String getPhone() {
        return getString("phone");
    }
    public Place getPlace() {
        return (Place)get("place");
    }
    public MobiFile getImage(){
        return (MobiFile)get("profileImage");
    }
    public String getSalutation() {
        return getString("salutation");
    }
    public String getSalutation2() {
        return getString("salutationLg2");
    }
    public String getSalutation3() {
        return getString("salutationLg3");
    }
    public Integer getSortingAux() {
        return getInt("sortingAux");
    }
    public PersonSoul getSoul(){return (PersonSoul)get("soul");}
    public String getTwitter() {
        return getString("twitter");
    }

    public List<Event> getEvents() {
        return getList("events");
    }

  /*
    public List<File> getFiles() {
        return getList("archivos");
    }

    public Integer getOrder() {
        return getInt("orden");
    }

    public List<Actor> getCharacters() {
        return getList("personajes");
    }
    public Company getInstitution() {
        return (Company)get("institucion");
    }

    public Place getPlace() {
        return (Place)get("lugar");
    }

*/
    public static ParseQuery<Person> getQuery() {
        return ParseQuery.getQuery(Person.class);
    }

}