package model;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by Alvaro on 3/10/15.
 */
@ParseClassName("MobiFile")
public class MobiFile extends ParseObject {

    public Boolean isActive() {
        return getBoolean("active");
    }
    public ParseFile getParseFileV1() {
        return (ParseFile)get("parseFileV1");
    }
    public ParseFile getParseFileV2() {
        return (ParseFile)get("parseFileV2");
    }
    public ParseFile getParseFileV3() {
        return (ParseFile)get("parseFileV3");
    }
    public Integer getSize() {
        return getInt("size");
    }
    public String getsubtype() {
        return getString("subtype");
    }
    public ParseFile getThumbnail() {
        return (ParseFile)get("thumbnail");
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
    public String getType() {
        return getString("type");
    }
    public String geturlFile() {
        return getString("urlSource");
    }




    public static ParseQuery<MobiFile> getQuery() {
        return ParseQuery.getQuery(MobiFile.class);
    }
}
