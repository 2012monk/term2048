package termui.constants;

public class BorderAttribute extends Attribute {

    private static final char EMPTY_BORDER = ' ';
    private char leftUpperCorner;
    private char rightUpperCorner;
    private char leftLowerCorner;
    private char rightLowerCorner;
    private char horizontalLine;
    private char verticalLine;

    public BorderAttribute(Color fg, Color bg) {
        super(fg, bg);
    }

    public boolean isDefaultBorder() {
        return true;
    }
}
