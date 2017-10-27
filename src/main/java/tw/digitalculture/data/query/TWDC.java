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
package tw.digitalculture.data.query;

import static def.jquery.Globals.$;
import def.jquery.JQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import tw.digitalculture.data.Config.DATA;
import static tw.digitalculture.data.Config.DATA.FILETYPES;
import tw.digitalculture.data.bin.TWDC_XML;
import tw.digitalculture.data.model.TWDC_Record;
import static tw.digitalculture.data.Config.DATA.TWDC.URL;
import tw.digitalculture.data.interfaces.Query;
import tw.digitalculture.data.model.Record_Query;

/**
 *
 * @author Jonathan Chang, Chun-yien <ccy@musicapoetica.org>
 */
public class TWDC extends Query<Record_Query> {

    private static List<TWDC_Record> dataset;

    public TWDC(Consumer<Boolean> callback) {
        TWDC.dataset = new ArrayList<>();
        refresh(URL, callback);
    }

    public static void refresh(String url, Consumer<Boolean> callback) {

        TWDC_XML.fetch(url, (String data) -> {
            JQuery xml_records = $(data).find("record");
            System.out.println("processing " + xml_records.length + " records...");
            xml_records.each((t, u) -> {
                TWDC_Record record = new TWDC_Record(
                        $(u).find("header"),
                        $(u).find("metadata"));
                if (!record.uri.isEmpty() && FILETYPES.contains(record.filetype.toLowerCase())) {
                    dataset.add(record);
                    System.out.println(dataset.size() + ". " + record.title);
                }
                return null;
            });
            String resumptionToken = $(data).find("resumptionToken").text();
            if (resumptionToken.isEmpty()) {
                System.out.println(
                        "Initializing twdc dataset completed. Total record fetched = " + dataset.size());
                callback.accept(Boolean.TRUE);
            } else {
                refresh(DATA.TWDC.URL_TOKEN + resumptionToken, callback);
            }
        });
    }

    @Override
    public void query(String text, int limit, Consumer<List<Record_Query>> callback) {
        List<Record_Query> records = new ArrayList();
        int n = limit;
        for (TWDC_Record data : dataset) {
            String result = data.contains(text);
            if (!result.isEmpty()) {
                n--;
                records.add(new Record_Query(data.uri, result));
            }
            if (n == 0) {
                break;
            }
        }
        callback.accept(records);
    }
}
