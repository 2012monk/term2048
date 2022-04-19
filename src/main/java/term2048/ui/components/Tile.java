package term2048.ui.components;

import term2048.ui.constants.TileInfo;
import termui.Buffer;
import termui.Cell;
import termui.Point;
import termui.Rectangle;
import termui.constants.Attribute;

public class Tile {

    private static final int WIDTH = 10;
    private static final int HEIGHT = 5;
    private Rectangle rectangle;
    private Rectangle numberRect;
    private TileInfo info;
    private Attribute attribute;

    public Tile(TileInfo info, Point p) {
        this.info = info;
        this.rectangle = new Rectangle(p, getEnd(p));
        this.numberRect = getNumberSpace();
    }

    private Point getEnd(Point p) {
        return new Point(p.getX() + HEIGHT, p.getY() + WIDTH);
    }

    private Rectangle getNumberSpace() {
        Point c = rectangle.getCenterPoint();
        int padding = getPadding();
        Point min = new Point(c.getX(), rectangle.getMin().getY() + padding);
        Point max = new Point(c.getX(), min.getY() + info.getNumberStr().length() - 1);
        return new Rectangle(min, max);
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public void render(Buffer buffer) {
        rectangle.getPoints().forEach(p -> buffer.setCell(p, getCell(p)));
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
}
