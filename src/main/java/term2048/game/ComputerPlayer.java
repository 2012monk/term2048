package term2048.game;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import term2048.controller.GameGrid;
import termui.Point;

public class ComputerPlayer {

    private Random random = new Random();

    public void consumeTurn(GameGrid grid) {
        List<Point> points = grid.getEmptyPoints();
        if (points.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Collections.shuffle(points);
        grid.setNumber(points.get(0), generateRandomNumber());
    }

    private int generateRandomNumber() {
        int next = random.nextInt(100);
        if (next < 90) {
            return 2;
        }
        return 4;
    }

    public void setInitialNumbers(GameGrid grid) {
        List<Point> points = grid.getEmptyPoints();
        if (points.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Collections.shuffle(points);
        grid.setNumber(points.get(0), generateRandomNumber());
        grid.setNumber(points.get(1), generateRandomNumber());
    }
}
