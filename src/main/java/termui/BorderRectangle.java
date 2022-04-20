package termui;

public class BorderRectangle extends Rectangle {

    private Point borderMin;
    private Point borderMax;

    public BorderRectangle(Point min, Point max) {
        super(new Point(min.getX() + 1, min.getY() + 1), new Point(max.getX() - 1, max.getY() - 1));
        this.borderMin = min;
        this.borderMax = max;
    }

    public Point getBorderMin() {
        return borderMin;
    }

    public Point getBorderMax() {
        return borderMax;
    }
}
