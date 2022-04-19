package termui;

import java.util.List;

public interface Space {

    Point getMin();

    Point getMax();

    int getWidth();

    int getHeight();

    List<Point> getPoints();

    boolean contains(Point point);
}
