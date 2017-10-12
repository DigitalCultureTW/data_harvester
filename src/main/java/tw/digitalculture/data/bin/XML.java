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

import def.dom.XMLHttpRequest;
import java.util.function.Consumer;
import static tw.digitalculture.data.Config.DATA.TWDC.URL;

/**
 *
 * @author Jonathan Chang, Chun-yien <ccy@musicapoetica.org>
 */
public class XML {

    public static void main(String[] args) {
        XML.fetch(URL,(t) -> {
        });
    }

    public static void fetch(String url, Consumer<String> callback) {
        XMLHttpRequest xhr = new XMLHttpRequest();
        xhr.open("GET", url, true);
        xhr.onload = (e) -> {
            String data = xhr.response.toString();
            callback.accept(data);
            return null;
        };
        xhr.send();
    }

//TODO
    public void write(String url, Object data) {
        //TODO
//                    methods.write = function (url, data) {
//            fs.writeFile(url, data.toString(), (err) => {
//                if (err) {
//                    console.log(err);
//                } else {
//                    console.log(url + " was saved!");
//                }
//            });
//        };
    }

}
