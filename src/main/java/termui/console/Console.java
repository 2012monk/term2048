package termui.console;

import termui.Cell;
import termui.Point;
import termui.constants.BasicColor;
import termui.constants.BorderAttribute;
import termui.constants.Color;

public class Console {

    private static final char DEFAULT_CLEAR_UNIT = ' ';

    public static void initConsole() {
        System.loadLibrary("Console");
        init();
    }

    public static void clearArea(int x, int y, int width, int height, char chr, Color fg,
        Color bg) {
        clearArea(x, y, width, height, chr, fg.getHex(), bg.getHex());
    }

    public static void drawChar(Point point, Cell cell) {
        drawChar(point.getX(), point.getY(), cell.getContent(), cell.getFg(), cell.getBg());
    }

    public static void drawChar(int x, int y, char chr, Color fg, Color bg) {
        drawChar(x, y, chr, fg.getHex(), bg.getHex());
    }

    public static void drawString(int x, int y, String text) {
        drawString(x, y, text, BasicColor.DEFAULT_FG, BasicColor.DEFAULT_BG);
    }

    public static void drawString(int x, int y, String str, Color fg, Color bg) {
        drawString(x, y, str, fg.getHex(), bg.getHex());
    }

    public static void lock() {
        startDraw();
    }

    public static void unlock() {
        endDraw();
    }

    private static native int setColorPair(int foreGround, int backGround);

    public static native int getScreenWidth();

    public static native int getScreenHeight();

    public static synchronized native int readBytes();

    public static synchronized native int blockingReadBytes();

    private static native void drawChar(int x, int y, char chr, int fg, int bg);

    private static native void drawBorder(int x, int y, int width, int height, int fg,
        int bg);

    private static native void drawBorderMinMax(int x0, int y0, int x1, int y1, int fg, int bg);

    private static native void drawString(int x, int y, String text, int fg, int bg);

    private static native void clearArea(int x, int y, int width, int height, char chr, int fg,
        int bg);

    private static native void startDraw();

    private static native void endDraw();

    private static native void clearLine(int x);

    public static native void clearScreen();

    private static native void init();

    public static native void shutdown();

    private static native void refresh();

    public static void drawBorder(Point min, Point max, BorderAttribute attribute) {
        drawBorderMinMax(min.getX(), min.getY(), max.getX(), max.getY(), attribute.getFg().getHex(),
            attribute.getBg().getHex());
    }
}
