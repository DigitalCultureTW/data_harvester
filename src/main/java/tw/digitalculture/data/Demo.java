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

import def.dom.Globals;
import static def.dom.Globals.document;
import def.dom.HTMLElement;
import def.dom.HTMLImageElement;
import static def.jquery.Globals.$;
import def.js.JSON;
import java.util.List;
import java.util.function.Consumer;
import tw.digitalculture.data.model.Record_Query;
import tw.digitalculture.data.model.Result;

/**
 *
 * @author Jonathan Chang, Chun-yien <ccy@musicapoetica.org>
 */
public class Demo {

    static DataCenter dc;

    public static void main(String[] args) {

        new Demo(100, (Boolean t) -> {
            if (t) {
                $("#submit").attr("disable", "disable");
                $("#submit").on("click", (e, o) -> {
                    $("#content").children().remove();
                    String text = $("#text").val().toString();
                    String client = "Test Client";
                    JSON data = (JSON) JSON.parse("{\"client\":\"" + client + "\","
                            + "\"text\":\"" + text + "\"}");

                    dc.getResult(data, (Result result) -> {
                        List<Record_Query> rs = result.record_set;
                        rs.forEach((Record_Query r) -> {
                            HTMLElement div = Globals.document.createElement("div");
                            HTMLImageElement img = (HTMLImageElement) document.createElement("img");
                            $(img).css("width", "400px").css("height", "auto");
                            img.src = r.img_url;
                            $(div).text(r.content);
                            HTMLElement table = document.createElement("table");
                            $(table).css("border", "1px solid grey").css("text-align", "left");
                            HTMLElement tr = document.createElement("tr");
                            HTMLElement th1 = document.createElement("th");
                            HTMLElement th2 = document.createElement("th");
                            $(th1).append(img);
                            $(th2).append(div);
                            $(tr).append(th1).append(th2);
                            $(table).append(tr);
                            $("#content").append(table);
                        });
                        $("#submit").removeAttr("disable");
                    });

                    return null;
                });

            } else {
                System.out.println("Nothing happened.");
            }
        });

    }

    public Demo(int limit, Consumer<Boolean> callback) {
        $(document).ready(() -> {
            $("#submit").attr("disable", "disable");
            Demo.dc = new DataCenter(limit, (Boolean e) -> {
                if (e) {
                    $("#submit").removeAttr("disable");
                    callback.accept(true);
                }
            });
            return null;
        });
    }

}
