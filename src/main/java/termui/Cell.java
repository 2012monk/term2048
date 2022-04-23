package termui;

import termui.constants.Attribute;
import termui.constants.Color;
import termui.constants.RGBColor;

public class Cell {

    private static final char DEFAULT_CHAR = ' ';
    private char content;
    private Attribute attribute;

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
        this.attribute = new Attribute(fg, bg);
    }

    public Cell(char content, int fgHex, int bgHex) {
        this(content, new RGBColor(fgHex), new RGBColor(bgHex));
    }

    public char getContent() {
        return content;
    }

    public Color getFg() {
        return attribute.getFg();
    }

    public Color getBg() {
        return attribute.getBg();
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }
}
