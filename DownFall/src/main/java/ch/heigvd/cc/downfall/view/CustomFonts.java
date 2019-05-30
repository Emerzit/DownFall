package ch.heigvd.cc.downfall.view;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class CustomFonts {
    public static Font SoftFont;
    public static Font UnicornFont;

    static {
        try {
            SoftFont = Font.createFont(Font.TRUETYPE_FONT, CustomFonts.class.getResourceAsStream("/Font/monofonto.ttf"));
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            UnicornFont = Font.createFont(Font.TRUETYPE_FONT, CustomFonts.class.getResourceAsStream("/Font/UnicornMagic2.ttf")).deriveFont(Font.PLAIN,30);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
