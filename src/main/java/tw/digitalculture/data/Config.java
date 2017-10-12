package tw.digitalculture.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Config {

    public static class PROJECT {

        public static String TITLE_MAIN = "記憶窗櫺";
        public static String TITLE_ENGLISH = "The Window of Our Memories";
        public static String SUBTITLE = "共築臺中印象的角落";

        public static String TITLE = PROJECT.TITLE_MAIN + "—" + PROJECT.SUBTITLE;
        public static String LOGO_PATH = "/element/logo_2.png";
        public static String VERSION = "0.5.3a-beta-JSWEET";
    }

    public static class UMBRA {

        public static String URL = "http://wm.localstudies.tw";
        public static String FONT = "DFKai-sb";
        public static String TITLE_COLOR = "Silver";

        public static String QRCODE_IMG;
    }

    public static class LUNA {

        public static int COLUMN = 8, ROW = 4;
        public static String FONT = "Microsoft JhengHei";

        public static class CARD {

            public static int BORDER_WIDTH = 10;
            public static String BORDER_STYLE = "inset";
            public static String[] BORDER_COLOR = {"Silver", "White"};
            public static String COLOR = "#121212";
        }

        public static int FLIP_TIME_OUT = 5000; //ms
        public static int SYSTEM_LOGO_TIME_OUT = 7000; //ms
        public static int SHOW_INTERVAL = 2500; //ms
        public static int SHOW_STAY = 1500; //ms
        public static String QRCODE = "@QR_CODE_TOKEN";

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

        public static int LIMIT() {
            return (int) (LUNA.ROW * LUNA.COLUMN) / 2;
        }

        public static class TWDC {

            public static String URL = "http://data.digitalculture.tw/taichung/oai?verb=ListRecords&metadataPrefix=oai_dc";
        }

        public static class IDEASQL {

            public static String URL = "http://designav.io/api/image/search/";
            public static String MULTI_URL = "http://designav.io/api/image/search_multi/";
            public static String WB_URL = "http://designav.io/api/image/wordbreak/";
        }
    }
}
