package termui;

import termui.console.Console;
import termui.constants.Char;

public class TerminalInputListener {

    public static Char read() {
        char ch = (char) Console.blockingReadBytes();
        return new Char(ch);
    }
}
