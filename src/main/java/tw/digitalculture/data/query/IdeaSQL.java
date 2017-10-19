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

    List<Record_Query> result = new ArrayList<>();
    Array<JSON> data;
    int count;

    @Override
    public void query(String query_text, Consumer<List<Record_Query>> callback) {
        IDEASQL_JSON.fetch(get_url(query_text, DATA.LIMIT), (Array<JSON> fetch_result) -> {
            fetch_result.forEach((JSON rec) -> {
                IDEASQL_Record record = new IDEASQL_Record(rec);
                IsValidImageUrl(record.uri, (Boolean isValid) -> {
                    record.img_link_valid = isValid;
                    String text = record.title.contains(query_text)
                            ? record.title : record.description;
                    result.add(new Record_Query(record.uri, text));
                });
            });
            callback.accept(result);
        });
    }

    public void IsValidImageUrl(String url, Consumer<Boolean> callback) {
        XMLHttpRequest xhr = new XMLHttpRequest();
        xhr.open("GET", url);
        xhr.onerror = (t) -> {
            callback.accept(false);
            return null; //To change body of generated lambdas, choose Tools | Templates.
        };
        xhr.onload = (t) -> {
            callback.accept(true);
            return null;
        };
    }
}
/*
(function () {
    'use strict';
    var cf = require('../config.js').DATA;
    var json = require('./json.js')();
    var request = require('request');

    module.exports = function () {
        var methods = {};

        methods.get_url = function (query_text, limit) {
            var api = (query_text.split(" ").length > 1) ?
                    cf.IDEASQL.MULTI_URL : cf.IDEASQL.URL;
            var url = api + encodeURIComponent(query_text) +
                    ((limit > 0) ? "?limit=" + limit : "");
            console.log(decodeURIComponent(url));
            return url;
        };

        methods.query = function (query_text, limit, callback) {
            var records = [];
            var count = 0;
            json.fetch(methods.get_url(query_text, limit), (data) => {
//        console.log("Total records: " + data.length);
                if (data.length === 0)
                    next(0);
                data.forEach((rec) => {
                    var record = new json.IDEASQL_Record(rec.id, rec.description,
                            rec.img_link, JSON.parse(rec.detail_infos));
                    records.push(record);
                    methods.IsValidImageUrl(rec.img_link, (isValid) => {
                        record.img_link_valid = isValid;
                        next(1);
                    });
                });
                function next(n) {
                    count += n;
                    if (count === data.length) {
                        var result = [];
                        records.forEach((record) => {
                            if (record.img_link_valid) {
                                var text = record.title.includes(query_text) ?
                                        record.title : record.description;
                                result.push({
                                    img_url: record.img_link,
                                    description: text
                                });
                            }
                        });
                        callback(result);
                    }
                }
            });
        };

        methods.IsValidImageUrl = function (url, callback) {
            request.get(url, (error, response, body) => {
                callback(!error && response.statusCode === 200);
            });
        };

        methods.getWordbreak = function (description, callback) {
            var text = description;

            var url = cf.IDEASQL.WB_URL + encodeURIComponent(text);
//    console.log(decodeURIComponent(url));
            (url, function (description) {
                callback(description);
            });
        };

        return methods;
    };
}());


 */
