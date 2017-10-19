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

    public static class PROJECT {

        public static String TITLE_MAIN = "記憶窗櫺";
        public static String TITLE_ENGLISH = "The Window of Our Memories";
        public static String SUBTITLE = "共築臺中印象的角落";

        public static String TITLE = PROJECT.TITLE_MAIN + "—" + PROJECT.SUBTITLE;
        public static String LOGO_PATH = "/element/logo_2.png";
        public static String VERSION = "0.6.2-beta-jswt";
    }

    public static class UMBRA {

        public static String URL = "http://wm.localstudies.info";
        public static String FONT = "'DFKai-sb', 'BiauKai'";
        public static String TITLE_COLOR = "Silver";
        public static String QRCODE_IMG;
    }

    public static class LUNA {

        public static int COLUMN = 8, ROW = 4;
//        public static String FONT = "Arial, 'Microsoft JhengHei', 'Heiti TC'";
        public static String FONT = "'Yu Gothic', SimHei";

        public static class CARD {

            public static int BORDER_WIDTH = 10;
            public static String BORDER_STYLE = "inset";
            public static String[] BORDER_COLOR = {"Silver", "White"};
            public static String COLOR = "#121212";
//            public static String FONT = "'Times New Roman', DFKai-sb, BiauKai";
            public static String FONT = "Meiryo, '微軟正黑體', 'Microsoft JhengHei'";
//            public static String FONT = "source-han-serif-tc-n7";
//            public static String FONT = "source-han-sans-traditional";
            public static String FONT_WEIGHT = "normal";
            public static String FONT_COLOR = "white";

        }

        public static int FLIP_TIME_OUT = 5000; //ms
//        public static int SYSTEM_LOGO_TIME_OUT = 7000; //ms
        public static int SHOW_INTERVAL = 3000; //ms
        public static int SHOW_STAY = 1500; //ms
        public static String QRCODE = "@QR_CODE_TOKEN";
        public static String TEXT = "@TEXT_TOKEN";

        public static int MIN_LOGO() {
            return ((LUNA.ROW * LUNA.COLUMN) / 8);
        }

        public static double TITLE_RATIO = 0.6;
        public static String TITLE_COLOR = "Silver";
        public static double TOP_HEIGHT_RATIO = 0.08;
        public static double BOTTOM_HEIGHT_RATIO = 0.04;

        public static double MOD(int row) {
            return (row > 2) ? 1 : ((row == 1) ? 0.70 : 0.95);
        }
    }

    public static class DATA {

        public static List<String> FILETYPES
                = Arrays.asList(new String[]{"jpg", "png", "JPG", "PNG"});

        public static int LIMIT = (int) (LUNA.ROW * LUNA.COLUMN) / 2;

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
