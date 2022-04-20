package termui.constants;

import java.util.Set;

public class Attribute {

    private Color fg;
    private Color bg;

    public Attribute() {
        this.fg = BasicColor.DEFAULT_FG;
        this.bg = BasicColor.DEFAULT_BG;
    }

    public Attribute(Color fg, Color bg) {
        this.fg = fg;
        this.bg = bg;
    }

    public Color getFg() {
        return fg;
    }

    public Color getBg() {
        return bg;
    }
}
