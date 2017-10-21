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
package tw.digitalculture.demo;

import static def.dom.Globals.document;
import def.dom.HTMLElement;
import def.dom.HTMLImageElement;
import static def.jquery.Globals.$;
import static tw.digitalculture.data.Config.SIZE;

/**
 *
 * @author Jonathan Chang, Chun-yien <ccy@musicapoetica.org>
 */
public class Demo_TR {

    HTMLElement tr = document.createElement("tr");
    HTMLElement th_img = document.createElement("td");
    HTMLElement th_text = document.createElement("td");
    HTMLElement div_img = document.createElement("div");
    HTMLElement div_text = document.createElement("div");

    Demo_TR(HTMLImageElement img, String text) {
        $(th_img).append(div_img);
        $(th_text).append(div_text);
        $(tr).append(th_img).append(th_text);

        $(div_img).css("width", SIZE + "px").css("height", SIZE + "px");
        $(div_text).css("height", SIZE + "px");

        $(div_img).addClass("div_img").append(img);
        $(div_text).text(text);
    }
}
