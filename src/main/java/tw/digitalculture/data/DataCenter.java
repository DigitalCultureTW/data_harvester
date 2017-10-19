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

import def.js.JSON;
import java.util.List;
import java.util.function.Consumer;
import tw.digitalculture.data.model.Record_Query;
import tw.digitalculture.data.model.Result;

/**
 *
 * @author Jonathan Chang, Chun-yien <ccy@musicapoetica.org>
 */
public class DataCenter {

    TWDC twdc;

    public DataCenter(int limit, Consumer<Boolean> callback) {
        this.twdc = new TWDC(limit, callback);
    }

    public void getResult(JSON data, Consumer<Result> callback) {
        Result result = new Result(data.$get("client"), data.$get("text"));
        System.out.println(result.query_str);
        twdc.query(result.query_str, (twdc_result) -> {
            ((List<Record_Query>) twdc_result).forEach((e) -> result.record_set.add(e));
            callback.accept(result);
        });

    }

}
