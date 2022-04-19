package termui.constants;

/**
 * pre configured colors
 */
public enum BasicColor implements Color {

    BLACK(0, 0, 0),
    RED(229, 20, 0),
    ORANGE(250, 104, 0),
    YELLOW(227, 200, 0),
    CYAN(27, 161, 226),
    GREEN(96, 169, 23),
    BLUE(0, 80, 239),
    LIGHT_BLUE(79, 195, 247),
    MAGENTA(216, 0, 115) ,
    LIME(164, 196, 0),
    PINK(244, 114, 208),
    VIOLET(170, 0, 255),
    WHITE(255,255,255),
    GREY400(189, 189, 189),
    GREY500(117, 117, 117),
    GREY700(97, 97, 97);

    private final Color color;

    BasicColor(int red, int green, int blue) {
        this.color = new RGBColor(red,green,blue);
    }

    @Override
    public int getRed() {
        return color.getRed();
    }

    @Override
    public int getGreen() {
        return color.getGreen();
    }

    @Override
    public int getBlue() {
        return color.getBlue();
    }


    @Override
    public int getHex() {
        return color.getHex();
    }
}
