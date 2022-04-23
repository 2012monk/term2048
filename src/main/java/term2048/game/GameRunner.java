package term2048.game;

import term2048.controller.GameGrid;
import term2048.exceptions.IllegalShiftException;
import term2048.ui.components.TileContainer;
import termui.Point;
import termui.TerminalInputListener;
import termui.constants.Char;

public class GameRunner {

    private GameGrid grid;
    private Player player;
    private ComputerPlayer computer;
    private TileContainer tileGrid;
    private boolean isRunning;

    public void run() {
        setUpGame();
        while (isRunning()) {
            Char ch = TerminalInputListener.read();
            proceedTurn(ch);
        }
    }

    private void proceedTurn(Char ch) {
        if (!player.isMoveKey(ch)) {
            controlGame(ch);
            return;
        }
        try {
            player.consumeTurn(ch, grid);
            computer.consumeTurn(grid);
            isRunning = isGameRunning(grid);
            tileGrid.updateStatus(grid);
            tileGrid.render();
        } catch (IllegalShiftException ignore) {
        }
    }

    private boolean isGameRunning(GameGrid grid) {
        return grid.haveMovableDirection();
    }

    private void controlGame(Char ch) {
        if (ch.is('q')) {
            isRunning = false;
        }
    }

    private boolean isRunning() {
        return isRunning;
    }

    private void setUpGame() {
        this.grid = new GameGrid(4);
        this.player = new Player();
        this.computer = new ComputerPlayer();
        this.tileGrid = new TileContainer(4, new Point(10, 40));
        this.isRunning = true;
        computer.setInitialNumbers(grid);
        tileGrid.updateStatus(grid);
        tileGrid.render();
    }
}
