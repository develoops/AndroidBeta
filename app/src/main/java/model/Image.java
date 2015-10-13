package model;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by Alvaro on 2/16/15.
 */
@ParseClassName("Image")
public class Image extends ParseObject {

    public String getImageName() {
        return getString("imageName");
    }
    public String getImageVersion() {
        return getString("imageVersion");
    }
    public Boolean isActive() {
        return getBoolean("active");
    }
    public Integer getSize() {
        return getInt("size");
    }
    public String getType() {
        return getString("type");
    }

    public ParseFile getParseFile() {
        return getParseFile("parseFile");
    }

    public static ParseQuery<Image> getQuery() {
        return ParseQuery.getQuery(Image.class);
    }
}
