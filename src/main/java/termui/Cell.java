package termui;

import termui.constants.Attribute;
import termui.constants.Color;
import termui.constants.RGBColor;

public class Cell {

    private static final char DEFAULT_CHAR = ' ';
    private char content;
    private Color fg;
    private Color bg;

    public Cell() {
    }

    public Cell(char content) {
        this.content = content;
    }

    public Cell(Color fg, Color bg) {
        this(DEFAULT_CHAR, fg, bg);
    }

    public Cell(char content, Color fg, Color bg) {
        this.content = content;
        this.fg = fg;
        this.bg = bg;
    }

    public Cell(char content, int fgHex, int bgHex) {
        this(content, new RGBColor(fgHex), new RGBColor(bgHex));
    }

    public char getContent() {
        return content;
    }

    public Color getFg() {
        return fg;
    }

    public Color getBg() {
        return bg;
    }

    public void setAttribute(Attribute attribute) {

    }
}
