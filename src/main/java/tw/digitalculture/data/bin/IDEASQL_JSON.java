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
package tw.digitalculture.data.bin;

import def.dom.Event;
import def.dom.XMLHttpRequest;
import def.js.Array;
import def.js.JSON;
import java.util.function.Consumer;
import tw.digitalculture.data.Config.DATA;

/**
 *
 * @author Jonathan Chang, Chun-yien <ccy@musicapoetica.org>
 */
public class IDEASQL_JSON {

    private static final IDEASQL_JSON instance = new IDEASQL_JSON();

    public static IDEASQL_JSON getInstance() {
        return instance;
    }

    public void fetch(String url, Consumer<Array<JSON>> callback) {
        XMLHttpRequest xhr = new XMLHttpRequest();
        xhr.open("GET", url, true);
        xhr.onload = (Event e) -> {
//            System.out.println(e);
//            String body="";
            String data = xhr.response.toString();
            callback.accept((Array<JSON>) JSON.parse(data));
            return null;
        };
        xhr.send();
    }

    public Record createRecord(String id, String content, String img_link, JSON detail_infos) {
        return new Record(id, content, img_link, detail_infos);
    }

    public class Record {

        public String id;
        public String content;
        public String img_link;
        public JSON detail_infos;
        public String title;
        public boolean img_link_valid = false;

        public Record(String id, String content, String img_link, JSON detail_infos) {
            this.id = id;
            this.content = content.replaceAll("\\n", " ");
            this.img_link = img_link;
            this.detail_infos = detail_infos;
            System.out.println("id = " + id);
            System.out.println("content = " + content);
            System.out.println("img_link = " + img_link);
            System.out.println("detail_infos.title: " + detail_infos.$get("title"));
//            this.title = detail_infos.$get("title").replaceAll("\\n", " ");
            this.img_link_valid = false;
        }
    ;
}
//                    http.get(url, (res) => {
//                var body = '';
//                res.on('data', (chunk) => {
//                    body += chunk;
//                });
//                res.on('end', () => {
//                    try {
//                        callback(JSON.parse(body));
//                    } catch (err) {
//                        callback(JSON.parse('[]'));
//                    }
//                });
//            }).on('error', (e) => {
//                console.log("Got an error: ", e);
//            });
//    }
}
/*
(function () {
    'use strict';
    var http = require('http');
    var fs = require("fs");

        methods.fetch = function (url, callback) {
            http.get(url, (res) => {
                var body = '';
                res.on('data', (chunk) => {
                    body += chunk;
                });
                res.on('end', () => {
                    try {
                        callback(JSON.parse(body));
                    } catch (err) {
                        callback(JSON.parse('[]'));
                    }
                });
            }).on('error', (e) => {
                console.log("Got an error: ", e);
            });
        };

        methods.write = function (path, content) {
            var str = JSON.stringify(content, null, 4);
            fs.writeFile(path, str, (err) => {
                if (err)
                    console.log(err);
                else
                    console.log(path + " saved.");
            });
        };
        


        return methods;
    };
}());
 */
