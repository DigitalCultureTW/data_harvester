package tw.digitalculture.data.model;

/**
 *
 * @author Jonathan
 */
public class Record_Display {

    public String query_str;
    public String img_path;
    public String content;
    public boolean used;

    public Record_Display(String str, String img, String txt) {
        query_str = str;
        img_path = img;
        content = txt;
        used = false;
    }
}
