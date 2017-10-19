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

import def.js.JSON;
import tw.digitalculture.data.interfaces.Record;

/**
 *
 * @author Jonathan Chang, Chun-yien <ccy@musicapoetica.org>
 */
public class IDEASQL_Record extends Record {

    public JSON detail_infos;
    public boolean img_link_valid = false;

    public IDEASQL_Record(JSON rec) {
        super(rec.$get("id").toString(),
                "Foobar",
                rec.$get("content").toString(),
                rec.$get("img_link").toString());
//        ((JSON) rec.$get("detail_infos")).$get("title").toString()
        this.detail_infos = rec.$get("detail_infos");
        System.out.println("title = " + this.detail_infos.$get("title"));

        this.title = this.title.replaceAll("\\n", " ");
        this.description = this.description.replaceAll("\\n", " ");
    }

}
