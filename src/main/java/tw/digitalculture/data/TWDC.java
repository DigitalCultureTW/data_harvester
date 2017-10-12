/*
 * Copyright (C) 2017 Jonathan Chang, Chun-yien <ccy@musicapoetica.org>
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
package tw.digitalculture.data;

import static def.jquery.Globals.$;
import def.jquery.JQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import static tw.digitalculture.data.Config.DATA.FILETYPES;
import tw.digitalculture.data.bin.XML;
import tw.digitalculture.data.model.Record;
import static tw.digitalculture.data.Config.DATA.TWDC.URL;
import tw.digitalculture.data.model.Record_Query;

/**
 *
 * @author Jonathan Chang, Chun-yien <ccy@musicapoetica.org>
 */
public class TWDC {

    public int limit;
    private static List<Record> dataset;

    public TWDC(int limit, Consumer<Boolean> callback) {
        this.limit = limit;
        TWDC.dataset = new ArrayList<>();
        refresh(callback);
    }

    public static void refresh(Consumer<Boolean> callback) {

        XML.fetch(URL, (String data) -> {
            JQuery xml_records = $(data).find("record");

            xml_records.each((t, u) -> {
                Record record = new Record(
                        $(u).find("header"),
                        $(u).find("metadata"));
                if (!record.link.isEmpty() && FILETYPES.contains(record.filetype)) {
                    dataset.add(record);
                }
                return null;
            });
            System.out.println(
                    "Initializing twdc dataset completed. Total record fetched = " + dataset.size());
            callback.accept(Boolean.TRUE);
        });
    }

    public void query(String text, Consumer<List<Record_Query>> callback) {
        List<Record_Query> records = new ArrayList();
        int n = this.limit;
        for (Record data : dataset) {
            String result = data.contains(text);
            if (!result.isEmpty()) {
                n--;
                records.add(new Record_Query(data.link, result));
            }
            if (n == 0) {
                break;
            }
        }
        callback.accept(records);
    }

}