package termui;

import java.io.PrintStream;
import java.util.Arrays;
import termui.constants.Attribute;
import termui.constants.Color;

public class StdBuffer implements Buffer {

    private final PrintStream printStream = new PrintStream(System.out);
    private final Rectangle rectangle;
    private Cell[] cellBuffer;
    private int cursor = 0;

    public StdBuffer(int height, int width) {
        this.rectangle = new Rectangle(0, 0, height, width);
        this.cellBuffer = new Cell[height * width];
        clear();
    }

    @Override
    public void setCell(Point point, Cell cell) {
        cellBuffer[point(point)] = cell;
    }

    private int point(Point point) {
        return point.getX() * rectangle.getWidth() + point.getY();
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
    public void addCell(Cell cell) {
        cellBuffer[cursor - 1] = cell;
    }

    @Override
    public void addString(String msg) {
        Point p = new Point(cursor / rectangle.getWidth(), cursor % rectangle.getWidth());
        setString(p, msg);
        setString(p, msg);
        cursor += msg.length();
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
