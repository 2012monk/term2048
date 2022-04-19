package termui.constants;

public class ColorPair {

    private static final Color DEFAULT_FG = BasicColor.WHITE;
    private static final Color DEFAULT_BG = BasicColor.BLACK;

    private Color fg;
    private Color bg;

    private ColorPair(Color fg, Color bg) {
        this.fg = fg;
        this.bg = bg;
    }

    public ColorPair() {
        this(DEFAULT_FG, DEFAULT_BG);
    }

    public static ColorPair foreGround(int r, int g, int b) {
        return new ColorPair(RGBColor.RGB(r,g,b), DEFAULT_BG);
    }
    public static ColorPair backGround(int r, int g, int b) {
        return new ColorPair(DEFAULT_FG, RGBColor.RGB(r,g,b));
    }
}
