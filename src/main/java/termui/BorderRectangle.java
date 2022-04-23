package termui;

import termui.constants.BorderAttribute;

public class BorderRectangle extends Rectangle {

    private Point borderMin;
    private Point borderMax;
    private BorderAttribute attribute;

    public BorderRectangle(BorderAttribute attribute, Point min, Point max) {
        super(new Point(min.getX() + 1, min.getY() + 1), new Point(max.getX() - 1, max.getY() - 1));
        this.attribute = attribute;
        this.borderMin = min;
        this.borderMax = max;
    }

    public BorderRectangle(Point min, Point max) {
        this(new BorderAttribute(), min, max);
    }

    public Point getBorderMin() {
        return borderMin;
    }

    public Point getBorderMax() {
        return borderMax;
    }

    public BorderAttribute getAttribute() {
        return attribute;
    }
}
