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
package tw.digitalculture.data.model;

import def.jquery.JQuery;
import static def.jquery.Globals.$;
import static def.js.Globals.decodeURIComponent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jonathan Chang, Chun-yien <ccy@musicapoetica.org>
 */
public class Record {

    public String identifier;
    public String title;
    public List<String> subjects = new ArrayList<>();
    public String description;
    public String link;
    public String filename;
    public String filetype;

    public Record(JQuery header, JQuery metadata) {
        this.identifier = $(header).find("identifier").text();
        this.title = $(metadata).find("dc\\:title").text();
        $(metadata).find("dc\\:subject").each((i, s) -> {
            return subjects.add($(s).text());
        });
        this.description = $(metadata).find("dc\\:description").filter((t, u) -> {
            return (!$(u).text().startsWith("http://"));
        }).text();//.replaceAll("\n", "\n\n");
        this.link = decodeURIComponent($(metadata).find("dc\\:description").filter((t, u) -> {
            return ($(u).text().startsWith("http://"));
        }).text());
        this.filename = this.link.split("/")[this.link.split("/").length - 1];
        this.filetype = this.link.split(".")[this.link.split(".").length - 1];
        System.out.println("filename = " + filename);
        System.out.println("filetype = " + filetype);
    }

    public String contains(String keyword) {

        String result = "";
        if (this.subjects.size() > 0) {
            for (int index = 0; index < this.subjects.size(); index++) {
                if (this.subjects.get(index).contains(keyword)) {
                    result = this.subjects.get(index);
                }
            }
            if (!result.isEmpty()) {
                result += ':' + this.title + '。' + this.description;
            }
        }
        if (this.title.contains(keyword)) {
            result = this.title + '。' + this.description;
        } else if (this.description.contains(keyword)) {
            result = this.description;
        }
        return result;
    }
}
