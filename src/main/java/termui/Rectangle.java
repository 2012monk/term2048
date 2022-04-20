package termui;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Rectangle implements Space {

    private Point min;
    private Point max;

    public Rectangle(Point min, Point max) {
        this.min = min;
        this.max = max;
    }

    public Rectangle(int x0, int y0, int x1, int y1) {
        this(new Point(x0, y0), new Point(x1, y1));
    }

    @Override
    public Point getMin() {
        return min;
    }

    @Override
    public Point getMax() {
        return max;
    }

    @Override
    public int getWidth() {
        return max.getY() - min.getY();
    }

    @Override
    public int getHeight() {
        return max.getX() - min.getX();
    }

    @Override
    public List<Point> getPoints() {
        int x = min.getX();
        int y = min.getY();
        return getBoundary()
            .mapToObj(p -> new Point(p / getWidth() + x, p % getWidth() + y))
            .collect(Collectors.toList());
    }

    @Override
    public boolean contains(Point point) {
        if (point.getX() < min.getX() || point.getX() > max.getX()) {
            return false;
        }
        return point.getY() >= min.getY() && point.getY() <= max.getY();
    }

    public IntStream getBoundary() {
        return IntStream.range(0, getHeight() * getWidth());
    }

    public Point getRelative(Point point) {
        return new Point(point.getX() - min.getX(), point.getY() - min.getY());
    }

    public Point getCenterPoint() {
        return new Point(min.getX() + getHeight() / 2, min.getY() + getWidth() / 2);
    }
}
