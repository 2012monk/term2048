package termui.constants;

public class RGBColor implements Color {

    private static final short RED_SHIFT = 4 * 4;
    private static final short GREEN_SHIFT = 4 * 2;
    private static final short BLUE_SHIFT = 0;

    private final int red;
    private final int green;
    private final int blue;

    RGBColor(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public static RGBColor RGB(int red, int green, int blue) {
        return new RGBColor(red,green,blue);
    }

    @Override
    public int getRed() {
        return red;
    }

    @Override
    public int getGreen() {
        return green;
    }

    @Override
    public int getBlue() {
        return blue;
    }

    @Override
    public int getHex() {
        return (red << RED_SHIFT) + (green << GREEN_SHIFT) + (blue << BLUE_SHIFT);
    }
}
