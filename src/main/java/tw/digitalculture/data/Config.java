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

import java.util.Arrays;
import java.util.List;

public class Config {

    public static int SIZE = 180;

    public static class DATA {

        public static List<String> FILETYPES
                = Arrays.asList(new String[]{"jpg", "png"});

        public static class TWDC {

            public static String URL = "http://data.digitalculture.tw/taichung/oai?verb=ListRecords&metadataPrefix=oai_dc";
            public static String URL_BASE = "http://data.digitalculture.tw/taichung/oai?verb=ListRecords&metadataPrefix=oai_dc";
            public static String URL_TOKEN = "http://data.digitalculture.tw/taichung/oai?verb=ListRecords&&resumptionToken=";
        }

        public static class IDEASQL {

            public static String URL = "http://designav.io/api/image/search/";
            public static String MULTI_URL = "http://designav.io/api/image/search_multi/";
            public static String WB_URL = "http://designav.io/api/image/wordbreak/";
        }
    }
}
