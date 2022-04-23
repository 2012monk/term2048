package termui;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import termui.constants.Attribute;
import termui.constants.BorderAttribute;
import termui.constants.Color;

public class StdBuffer implements Buffer {

    private final PrintStream printStream = new PrintStream(System.out);
    private final Rectangle rectangle;
    private Cell[] cellBuffer;

    public StdBuffer(int height, int width) {
        this.rectangle = new Rectangle(0, 0, height, width);
        this.cellBuffer = new Cell[height * width];
        clear();
    }

    public StdBuffer(Rectangle rectangle) {
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

    @Override
    public int getWidth() {
        return rectangle.getWidth();
    }

    @Override
    public int getHeight() {
        return rectangle.getHeight();
    }

    private String colorEscape(Cell cell) {
        Color bg = cell.getBg();
        Color fg = cell.getFg();
        String e = "";
        if (bg != null) {
            e += rgbEscape(48, bg.getRed(), bg.getGreen(), bg.getBlue());
        }
        if (fg != null) {
            e += rgbEscape(38, fg.getRed(), fg.getGreen(), fg.getBlue());
        }
        return e;
    }


    private String rgbEscape(int sel, int r, int g, int b) {
        return "\033[" + sel + ";2;" + r + ";" + g + ";" + b + "m";
    }

    private String resetEscape() {
        return "\033[0m";
    }

    public void flush() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cellBuffer.length; i++) {
            if (i > 0 && i % rectangle.getWidth() == 0) {
                sb.append('\n');
            }
            if (cellBuffer[i] == null) {
                sb.append(' ');
                continue;
            }
            sb.append(colorEscape(cellBuffer[i]))
                .append(cellBuffer[i].getContent())
                .append(resetEscape());
        }
        printStream.println(sb);
    }
}
