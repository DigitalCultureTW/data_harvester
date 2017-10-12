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

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jonathan Chang, Chun-yien <ccy@musicapoetica.org>
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
