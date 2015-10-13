package model;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by Alvaro on 2/21/15.
 */
@ParseClassName("Video")
public class Video extends ParseObject {


    public String getVideoName() {
        return getString("videoName");
    }

    public ParseFile getParseFile() {
        return getParseFile("parseFile");
    }

    public Integer getSize() {
        return getInt("size");
    }

    public String getIdYouTube() {
        return getString("idYouTube");
    }

    public Integer getOrder() {
        return getInt("order");
    }

    public Boolean isActive() {
        return getBoolean("active");
    }


    public static ParseQuery<Video> getQuery() {
        return ParseQuery.getQuery(Video.class);
    }
}
