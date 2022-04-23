package term2048.game;

import term2048.controller.GameController;
import term2048.controller.GameGrid;
import term2048.exceptions.IllegalShiftException;
import term2048.ui.components.TileContainer;
import termui.Buffer;
import termui.TerminalInputListener;
import termui.constants.Char;

public class GameRunner {

    private GameController controller;
    private TileContainer tileGrid;
    private Buffer buffer;
    private boolean isRunning;

    public GameRunner(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        setUpGame();
        while (isRunning()) {
            Char ch = TerminalInputListener.read();
            proceedTurn(ch);
        }
    }

    private void proceedTurn(Char ch) {
        if (!controller.containsKey(ch)) {
            controlGame(ch);
            return;
        }
        try {
            GameGrid grid = controller.move(ch);
            tileGrid.updateStatus(grid);
            tileGrid.render(buffer);
        } catch (IllegalShiftException | IllegalArgumentException ignored) {
        }
    }

    private boolean isRunning() {
        return controller.isRunning() && isRunning;
    }

    private void controlGame(Char ch) {
        if (ch.is('q')) {
            isRunning = false;
        }
    }

    private void setUpGame() {
        this.tileGrid = new TileContainer(4, buffer);
        this.isRunning = true;
        this.controller = new GameController();
        tileGrid.updateStatus(controller.createNewGame(4));
        tileGrid.render(buffer);
    }
}
