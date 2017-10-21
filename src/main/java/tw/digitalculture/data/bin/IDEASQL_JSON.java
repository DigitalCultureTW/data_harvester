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

/**
 *
 * @author Jonathan Chang, Chun-yien <ccy@musicapoetica.org>
 */
public class IDEASQL_JSON {

    public static void fetch(String url, Consumer<Array<JSON>> callback) {
        XMLHttpRequest xhr = new XMLHttpRequest();
        xhr.open("GET", url, true);
        xhr.onload = (Event e) -> {
//            System.out.println(e);
//            String body="";
            String data = xhr.response.toString();
            Array<JSON> result = (Array<JSON>) JSON.parse(data);
//            System.out.println("result=" + result.length);
            callback.accept(result);
            return null;
        };
        xhr.send();
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

        methods.write = function (path, description) {
            var str = JSON.stringify(description, null, 4);
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
