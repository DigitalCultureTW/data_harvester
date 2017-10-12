package tw.digitalculture.data.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jonathan
 */
public class Result {

    public String client;
    public String query_str;
    public List<Record_Query> record_set;

    public Result(String client, String query_string) {
        this.client = client;
        this.query_str = query_string;
        this.record_set = new ArrayList<>();
    }

}
