package termui;

import java.util.List;
import java.util.Map;
import termui.constants.Attribute;
import termui.constants.BorderAttribute;

public interface Buffer {

    void setCell(Point point, Cell cell);

    void setString(Point point, String message);

    void setString(Point point, Attribute attribute, String msg);

    void clear();

    void setAttribute(Point point, Attribute attribute);

    List<Cell> getCells();

    Map<Point, Cell> getCellMap();

    void setBorder(Point min, Point max, BorderAttribute attribute);
}
