package term2048;

import org.junit.jupiter.api.Test;
import termui.Cell;
import termui.Rectangle;
import termui.StdBuffer;

public class AsciiEscapeTest {

    String rgbEscape(int sel, int r, int g, int b) {
        return "\033[" + sel + ";2;" + r + ";" + g + ";" + b + "m";
    }

    String resetEscape() {
        return "\033[0m";
    }

    @Test
    void test2() {
        StdBuffer buffer = new StdBuffer(20, 10);
        Rectangle rectangle = new Rectangle(0,0, 20, 10);
        Cell cell = new Cell('@', 0xff0f00, 0x00ffff);
        rectangle.getPoints().forEach(p -> buffer.setCell(p,cell));
        buffer.flush();
    }

    @Test
    void test() {
        String e = "";
        e += rgbEscape(38, 200, 100,0);
        e += rgbEscape(48, 244, 143, 100);
        System.out.println("\033[48;5;2mhello World!!\033[0m");
        System.out.println("\033[48;2;244;100;100m^%^%^%^%^%^%^^%\033[0m");
        System.out.println(e + "hello world!" + resetEscape());
    }
}
