package termui;

import java.util.List;
import java.util.Map;
import termui.console.Console;
import termui.constants.Attribute;
import termui.constants.BorderAttribute;

public class CursesTerminal implements Buffer{

    private Cell[] cells = new Cell[Console.getScreenHeight() * Console.getScreenWidth()];

    @Override
    public void setCell(Point point, Cell cell) {
        Console.drawChar(point, cell);
    }

    @Override
    public void setString(Point point, String message) {
        Console.drawString(point.getX(), point.getY(), message);
    }

    @Override
    public void setString(Point point, Attribute attribute, String msg) {
        Console.drawString(point.getX(), point.getY(), msg, attribute.getFg(), attribute.getBg());
    }

    @Override
    public void clear() {
        Console.clearScreen();
    }

    @Override
    public void setAttribute(Point point, Attribute attribute) {
    }

    @Override
    public List<Cell> getCells() {
        return null;
    }

    @Override
    public Map<Point, Cell> getCellMap() {
        return null;
    }

    @Override
    public void setBorder(Point min, Point max, BorderAttribute attribute) {
        Console.drawBorder(min, max, attribute);
    }

    public void lock() {
        Console.lock();
    }

    public void unlock() {
        Console.unlock();
    }
}
