package tw.digitalculture.data.model;

/**
 *
 * @author Jonathan
 */
public class Record_Query {

    public String img_url;
    public String content;

    public Record_Query(String url, String text) {
        this.img_url = url;
        this.content = text;
    }
}
