package term2048.ui.components;

import term2048.ui.constants.TileInfo;
import termui.BorderRectangle;
import termui.Buffer;
import termui.Cell;
import termui.Point;
import termui.Rectangle;
import termui.constants.Attribute;
import termui.constants.BasicColor;
import termui.constants.BorderAttribute;

public class Tile {

    private static final int WIDTH = 12;
    private static final int HEIGHT = 7;
    private BorderRectangle rectangle;
    private Rectangle numberRect;
    private TileInfo info;
    private Attribute attribute;
    private BorderAttribute borderAttribute;

    public Tile(TileInfo info, Point min, Point max) {
        this.info = info;
        this.rectangle = new BorderRectangle(min, max);
        this.numberRect = getNumberSpace();
        borderAttribute = new BorderAttribute(
            BasicColor.DEFAULT_BG, BasicColor.DEFAULT_FG
        );
    }

    public void changeInfo(TileInfo info) {
        this.info = info;
    }

    private Rectangle getNumberSpace() {
        Point c = rectangle.getCenterPoint();
        int padding = getPadding();
        Point min = new Point(c.getX(), rectangle.getMin().getY() + padding);
        Point max = new Point(c.getX(), min.getY() + info.getNumberStr().length() - 1);
        return new Rectangle(min, max);
    }

    public void render(Buffer buffer) {
        rectangle.getPoints().forEach(p -> buffer.setCell(p, getCell(p)));
        buffer.setBorder(rectangle.getBorderMin(), rectangle.getBorderMax(), borderAttribute);
    }

    private Cell getCell(Point point) {
        if (numberRect.contains(point)) {
            return info.getNumberAt(numberRect.getRelative(point).getY());
        }
        return info.getDefault();
    }

    private int getPadding() {
        return (rectangle.getWidth() - info.getNumberStr().length()) / 2;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
