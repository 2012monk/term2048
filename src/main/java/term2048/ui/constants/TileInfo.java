package term2048.ui.constants;

import termui.Cell;
import termui.constants.Color;
import termui.constants.RGBColor;

public class TileInfo {

    private String numberStr;
    private int number;
    private Cell defaultCell;

    public TileInfo(String number, Color fg, Color bg) {
        this.number = Integer.parseInt(number);
        this.numberStr = number;
        this.defaultCell = new Cell(fg, bg);
    }

    public TileInfo(int number, Color fg, Color bg) {
        this(String.valueOf(number), fg, bg);
    }

    public TileInfo(int number, int fgHex, int bgHex) {
        this(number, new RGBColor(fgHex), new RGBColor(bgHex));
    }

    public Cell getDefault() {
        return defaultCell;
    }

    public String getNumberStr() {
        return numberStr;
    }

    public int getNumber() {
        return number;
    }

    public Cell getNumberAt(int pos) {
        return new Cell(numberStr.charAt(pos), defaultCell.getFg(), defaultCell.getBg());
    }
}
