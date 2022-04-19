package termui;

import termui.constants.Attribute;

public interface Buffer {

    void setCell(Point point, Cell cell);

    void setString(Point point, String message);

    void setString(Point point, Attribute attribute, String msg);

    void addCell(Cell cell);

    void addString(String msg);

    void clear();

    void setAttribute(Point point, Attribute attribute);

}
