package term2048;

import term2048.game.GameRunner;
import term2048.utils.InfoLoader;
import termui.console.Console;

public class Application {

    public static void main(String[] args) {
        Console.initConsole();
        InfoLoader.load();
        GameRunner runner = new GameRunner();
        runner.run();
        Console.shutdown();
    }
}
