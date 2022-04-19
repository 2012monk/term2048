package termui.console;

import termui.constants.Color;

public class Console {

    private static final int DEFAULT_FG = 0xFFFFFF;
    private static final int DEFAULT_BG = 0x000000;
    private static final char DEFAULT_CLEAR_UNIT = ' ';

    public static void initConsole() {
        System.loadLibrary("Console");
        init();
    }

    public static void clearArea(int x, int y, int width, int height, char chr, Color fg,
        Color bg) {
        clearArea(x, y, width, height, chr, fg.getHex(), bg.getHex());
    }

    public static void drawChar(int x, int y, char chr, Color fg, Color bg) {
        drawChar(x, y, chr, fg.getHex(), bg.getHex());
    }

    public static void drawString(int x, int y, String text) {
        drawString(x, y, text, DEFAULT_FG, DEFAULT_BG);
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

    private static native void drawChar(int x, int y, char chr, int fg, int bg);

    private static native void drawBorder(int x, int y, int width, int height, int fg,
        int bg);

    private static native void drawString(int x, int y, String text, int fg, int bg);

    private static native void clearArea(int x, int y, int width, int height, char chr, int fg,
        int bg);

    private static native void startDraw();

    private static native void endDraw();

    private static native void clearLine(int x);

    private static native void clearScreen();

    private static native void init();

    public static native void shutdown();

    private static native void refresh();
}
