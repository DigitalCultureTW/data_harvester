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

import tw.digitalculture.data.query.TWDC;
import def.js.JSON;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import tw.digitalculture.data.interfaces.Query;
import tw.digitalculture.data.model.Record_Query;
import tw.digitalculture.data.model.Result;
import tw.digitalculture.data.query.IdeaSQL;

/**
 *
 * @author Jonathan Chang, Chun-yien <ccy@musicapoetica.org>
 */
public class DataCenter {

    TWDC twdc;
    IdeaSQL ideasql;
    List<Query<Record_Query>> queries;

    public DataCenter(int limit, Consumer<Boolean> callback) {
        this.queries = new ArrayList<>();
        this.twdc = new TWDC((t) -> {
            callback.accept(t);
            this.ideasql = new IdeaSQL();
            this.queries.add(twdc);
            this.queries.add(ideasql);
        });
    }

    int count, n;

    public void getResult(JSON data, int limit, Consumer<Result> callback) {
        count = 0;
        n = limit;
        Result result = new Result(data.$get("client"), data.$get("text"));
        System.out.println(result.query_str);
        queries.forEach((Query<Record_Query> source) -> {
            count++;
            if (n > 0) {
                source.query(result.query_str, n, (List<Record_Query> t) -> {
                    System.out.println(source.id + ":" + t.size());
                    n -= t.size();
                    t.forEach((Record_Query rq) -> {
                        result.record_set.add(rq);
                    });
                    if (count == queries.size()) {
                        callback.accept(result);
                    }
                });
            } else {
                callback.accept(result);
            }
        });
    }
}
