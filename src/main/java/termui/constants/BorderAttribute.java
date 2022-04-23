package termui.constants;

import term2048.constants.BorderStyle;

public class BorderAttribute extends Attribute {

    private static final char EMPTY_BORDER = ' ';
    private BorderStyle style = BorderStyle.DEFAULT;

    private char leftUpperCorner = '┌';
    private char rightUpperCorner = '┐';
    private char leftLowerCorner = '└';
    private char rightLowerCorner = '┘';
    private char horizontalLine = '─';
    private char verticalLine = '│';

    public BorderAttribute(Color fg, Color bg) {
        super(fg, bg);
    }

    public BorderAttribute() {
        super();
    }

    public BorderAttribute(BorderStyle style, Color fg, Color bg) {
        super(fg, bg);
        this.style = style;
        if (style == BorderStyle.EMPTY) {
            rightUpperCorner = leftLowerCorner = EMPTY_BORDER;
            rightLowerCorner = horizontalLine = EMPTY_BORDER;
            leftUpperCorner = verticalLine = EMPTY_BORDER;
        }
    }

    public boolean isDefaultBorder() {
        return style == BorderStyle.DEFAULT;
    }

    public BorderStyle getStyle() {
        return style;
    }

    public char getLeftUpperCorner() {
        return leftUpperCorner;
    }

    public char getRightUpperCorner() {
        return rightUpperCorner;
    }

    public char getLeftLowerCorner() {
        return leftLowerCorner;
    }

    public char getRightLowerCorner() {
        return rightLowerCorner;
    }

    public char getHorizontalLine() {
        return horizontalLine;
    }

    public char getVerticalLine() {
        return verticalLine;
    }
}
