package termui;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import termui.console.Console;
import termui.constants.Attribute;
import termui.constants.BorderAttribute;

public class CellBuffer implements Buffer {

    private Rectangle rectangle;
    private Cell[] cellBuffer;
    private int cursor = 0;

    public CellBuffer(Rectangle rectangle) {
        this.rectangle = rectangle;
        this.cellBuffer = new Cell[rectangle.getWidth() * rectangle.getHeight()];
    }

    @Override
    public void setCell(Point point, Cell cell) {
        cellBuffer[point(point)] = cell;
    }

    private int point(Point point) {
        int zero = rectangle.getMin().getX() * rectangle.getWidth() + rectangle.getMin().getY();
        return point.getX() * rectangle.getWidth() + point.getY() - zero;
    }

    @Override
    public void setString(Point point, String message) {
        int start = point.getY() * point.getX();
        for (int i = 0; i < message.length(); i++) {
            cellBuffer[i + point(point)] = new Cell(message.charAt(i));
        }
    }

    @Override
    public void setString(Point point, Attribute attribute, String msg) {
    }


    @Override
    public void clear() {
        Arrays.fill(cellBuffer, null);
    }

    @Override
    public void setAttribute(Point point, Attribute attribute) {
        if (cellBuffer[point(point)] == null) {
            cellBuffer[point(point)] = new Cell();
        }
        cellBuffer[point(point)].setAttribute(attribute);
    }

    @Override
    public List<Cell> getCells() {
        return List.of(cellBuffer);
    }

    @Override
    public Map<Point, Cell> getCellMap() {
        Map<Point, Cell> out = new HashMap<>();
        List<Point> points = rectangle.getPoints();
        for (int i = 0; i < cellBuffer.length; i++) {
            if (cellBuffer[i] == null) {
                continue;
            }
            out.put(points.get(i), cellBuffer[i]);
        }
        return out;
    }

    @Override
    public void setBorder(Point min, Point max, BorderAttribute attribute) {

    }

    private Point point(int i) {
        return new Point(i / rectangle.getWidth(), i % rectangle.getWidth());
    }

    private Cell[] getCellBuffer() {
        return cellBuffer;
    }

    public void flush() {
        List<Point> points = rectangle.getPoints();
        for (int i = 0; i < cellBuffer.length; i++) {
            if (cellBuffer[i] == null) {
                continue;
            }
            Console.drawChar(points.get(i), cellBuffer[i]);
        }
    }

}
