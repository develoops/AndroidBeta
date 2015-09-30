package model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by Alvaro on 2/17/15.
 */
@ParseClassName("ColorPalette")
public class ColorPalette extends ParseObject {

    public String getColor1() {
        return getString("color1");
    }
    public String getColor2() {
        return getString("color2");
    }
    public String getColor3() {
        return getString("color3");
    }
    public String getColor4() {
        return getString("color4");
    }
    public String getColor5() {
        return getString("color5");
    }
    public String paletteName() {
        return getString("paletteName");
    }

    public static ParseQuery<ColorPalette> getQuery() {
        return ParseQuery.getQuery(ColorPalette.class);
    }
}
