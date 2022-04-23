package term2048.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Consumer;
import termui.Point;
import termui.constants.Char;
import termui.constants.SpecialKeyCode;

public class GameController {

    private GameGrid grid;
    private Random random = new Random();
    private Map<Char, Consumer<GameGrid>> moves = new HashMap<>();

    public GameController() {
        moves.put(Char.of(SpecialKeyCode.KEY_UP), GameGrid::shiftUp);
        moves.put(Char.of(SpecialKeyCode.KEY_DOWN), GameGrid::shiftDown);
        moves.put(Char.of(SpecialKeyCode.KEY_LEFT), GameGrid::shiftLeft);
        moves.put(Char.of(SpecialKeyCode.KEY_RIGHT), GameGrid::shiftRight);
    }

    public GameGrid move(Char ch) {
        if (!moves.containsKey(ch)) {
            throw new IllegalArgumentException();
        }
        moves.get(ch).accept(grid);
        setRandomCell(1);
        return grid;
    }

    public GameGrid createNewGame(int size) {
        this.grid = new GameGrid(size);
        setRandomCell(2);
        return grid;
    }

    private void setRandomCell(int count) {
        List<Point> points = grid.getEmptyPoints();
        if (points.isEmpty() || points.size() < count) {
            throw new IllegalArgumentException();
        }
        Collections.shuffle(points);
        for (int i = 0; i < count; i++) {
            grid.setNumber(points.get(i), generateRandomNumber());
        }
    }

    public boolean isRunning() {
        return grid.haveMovableDirection();
    }

    private int generateRandomNumber() {
        int next = random.nextInt(100);
        if (next < 90) {
            return 2;
        }
        return 4;
    }

    public boolean containsKey(Char ch) {
        return moves.containsKey(ch);
    }
}
