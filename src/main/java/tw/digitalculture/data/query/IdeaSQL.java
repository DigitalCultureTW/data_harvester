/*
 * Copyright (C) 2017 Jonathan
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package tw.digitalculture.data.query;

import def.dom.XMLHttpRequest;
import def.js.Array;
import def.js.JSON;
import static def.js.Globals.decodeURIComponent;
import static def.js.Globals.encodeURIComponent;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import tw.digitalculture.data.interfaces.Query;
import tw.digitalculture.data.Config.DATA;
import tw.digitalculture.data.bin.IDEASQL_JSON;
import tw.digitalculture.data.model.IDEASQL_Record;
import tw.digitalculture.data.model.Record_Query;

/**
 *
 * @author Jonathan
 */
public class IdeaSQL extends Query<Record_Query> {

    public String get_url(String query_text, int limit) {
        String api = (query_text.split(" ").length > 1)
                ? DATA.IDEASQL.MULTI_URL : DATA.IDEASQL.URL;
        String url = api + encodeURIComponent(query_text)
                + ((limit > 0) ? "?limit=" + limit : "");
        System.out.println(decodeURIComponent(url));
        return url;
    }

    List<Record_Query> result;

    int count;

    @Override
    public void query(String query_text, int limit, Consumer<List<Record_Query>> callback) {
        count = 0;
        result = new ArrayList<>();
        IDEASQL_JSON.fetch(get_url(query_text, limit), (Array<JSON> fetch_result) -> {
            fetch_result.forEach((JSON rec) -> {
                IDEASQL_Record record = new IDEASQL_Record(rec);
                IsValidImageUrl(record.uri, (Boolean isValid) -> {
                    record.img_link_valid = isValid;
                    String text = record.title.contains(query_text)
                            ? record.title : record.description;
                    result.add(new Record_Query(record.uri, text));
                    count++;
                    if (count == fetch_result.length) {
                        callback.accept(result);
                    }
                });
            });
        });
    }

    public void IsValidImageUrl(String url, Consumer<Boolean> callback) {
        XMLHttpRequest xhr = new XMLHttpRequest();
        xhr.open("GET", url, true);
        xhr.onerror = (t) -> {
            callback.accept(false);
            return null; //To change body of generated lambdas, choose Tools | Templates.
        };
        xhr.onload = (t) -> {
            callback.accept(true);
            return null;
        };
        xhr.send();
    }
}
