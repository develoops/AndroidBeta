package model;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by Alvaro on 2/16/15.
 */
@ParseClassName("Company")
public class Company extends ParseObject {

    public Boolean isActive() {
        return getBoolean("active");
    }
    public List <Actor> getActors() {
        return getList("actors");
    }
    public List <Product> getCatalog() {
        return getList("catalog");
    }
    public String getDetails() {
        return getString("details");
    }
    public String getDetails2() {
        return getString("detailsLg2");
    }
    public String getDetails3() {
        return getString("detailsLg3");
    }
    public String getFacebook() {
        return getString("facebook");
    }
    public List<MobiFile> getGallery() {
        return getList("gallery");
    }
    public MobiFile getheaderImage(){
        return (MobiFile)get("headerImage");
    }
    public MobiFile getheaderVideo(){
        return (MobiFile)get("headerVideo");
    }
    public List <MobiFile> getFiles() {
        return getList("library");
    }
    public String getLinkedIn() {
        return getString("linkedin");
    }
    public Place getLocation(){return (Place)get("location");}
    public MobiFile getLogo(){
        return (MobiFile)get("logo");
    }
    public String getMail() {
        return getString("mail");
    }
    public String getName() {
        return getString("name");
    }
    public String getName2() {
        return getString("nameLg2");
    }
    public String getName3() {
        return getString("nameLg3");
    }
    public String getPhone() {
        return getString("phone_s");
    }
    public Place getPlace(){return (Place)get("place");}
    public CompanySoul getSoul(){return (CompanySoul)get("soul");}
    public String getSubtitle() {
        return getString("subtitle");
    }
    public String getSubtitle2() {
        return getString("subtitleLg2");
    }
    public String getSubtitle3() {
        return getString("subtitleLg3");
    }
    public String getTitle() {
        return getString("title");
    }
    public String getTitle2() {
        return getString("titleLg2");
    }
    public String getTitle3() {
        return getString("titleLg3");
    }
    public String getTwitter() {
        return getString("twitter");
    }
    public String getType() {
        return getString("type");
    }
    public String getWeb() {
        return getString("website");
    }






    /*

    public String getFacebook() {
        return getString("facebook");
    }
    public Image getHeader() {
        return (Image)get("header");
    }
    public String getLinkedIn() {
        return getString("linkedin");
    }
    public Image getLogo() {
        return (Image)get("logo");
    }
    public Place getPlace() {
        return (Place)get("lugar");
    }
    public String getMail() {
        return getString("mail");
    }
    public String getName() {
        return getString("nombre");
    }
    public List<Actor> getCharacters() {
        return getList("personajes");
    }

    public String getPhone() {
        return getString("telefono");
    }
    public String getKind() {
        return getString("tipo");
    }
    public String getTwitter() {
        return getString("twitter");
    }
    public String geturl() {
        return getString("urlWeb");
    }

    */

    public static ParseQuery<Company> getQuery() {
        return ParseQuery.getQuery(Company.class);
    }
}
