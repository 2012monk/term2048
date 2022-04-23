package term2048.ui.components;

import term2048.constants.BorderStyle;
import term2048.ui.constants.TileInfo;
import termui.BorderRectangle;
import termui.Buffer;
import termui.Cell;
import termui.Point;
import termui.Rectangle;
import termui.constants.Attribute;
import termui.constants.BasicColor;
import termui.constants.BorderAttribute;
import termui.constants.Color;
import termui.constants.RGBColor;

public class Tile {

    private static final Color BORDER_COLOR = new RGBColor(0xD7A86E);
    private BorderRectangle rectangle;
    private Rectangle numberRect;
    private TileInfo info;
    private Attribute attribute;

    public Tile(TileInfo info, Point min, Point max) {
        this.rectangle = new BorderRectangle(
            new BorderAttribute(
                BorderStyle.EMPTY, BasicColor.DEFAULT_BG, BORDER_COLOR),
            min, max);
        changeInfo(info);
    }

    public void changeInfo(TileInfo info) {
        this.info = info;
        this.numberRect = getNumberSpace();
    }

    private Rectangle getNumberSpace() {
        Point c = rectangle.getCenterPoint();
        int padding = getPadding();
        Point min = new Point(c.getX(), rectangle.getMin().getY() + padding);
        Point max = new Point(c.getX(), min.getY() + info.length());
        return new Rectangle(min, max);
    }

    public void render(Buffer buffer) {
        rectangle.getPoints().forEach(p -> buffer.setCell(p, getCell(p)));
        buffer.setBorder(rectangle.getBorderMin(), rectangle.getBorderMax(), rectangle.getAttribute());
    }

    private Cell getCell(Point point) {
        if (numberRect.contains(point)) {
            return info.getNumberAt(numberRect.getRelative(point).getY());
        }
        return info.getDefault();
    }

    private int getPadding() {
        return (rectangle.getWidth() - info.length()) / 2;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
