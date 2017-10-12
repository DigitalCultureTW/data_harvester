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

/**
 *
 * @author Jonathan Chang, Chun-yien <ccy@musicapoetica.org>
 */
public class Record_Display {

    public String query_str;
    public String img_path;
    public String content;
    public boolean used;

    public Record_Display(String str, String img, String txt) {
        query_str = str;
        img_path = img;
        content = txt;
        used = false;
    }
}
