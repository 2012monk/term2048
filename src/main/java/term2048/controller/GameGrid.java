package term2048.controller;

import java.util.ArrayList;
import java.util.List;
import term2048.exceptions.IllegalShiftException;
import termui.Point;

public class GameGrid {

    private static final int RIGHT = 0;
    private static final int LEFT = 1;
    private static final int UP = 3;
    private static final int DOWN = 2;
    private static final int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static final int DEFAULT_SIZE = 4;
    private final int[][] grid;
    private final int[] backward, forward;
    private final int size;
    private int score;

    public GameGrid() {
        this(DEFAULT_SIZE);
    }

    public GameGrid(int size) {
        validateSize(size);
        this.size = DEFAULT_SIZE;
        this.grid = new int[size][size];
        this.forward = new int[size];
        this.backward = new int[size];
        for (int i = 0; i < size; i++) {
            forward[i] = i;
            backward[i] = size - i - 1;
        }
    }

    public List<Integer> getStatus() {
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                arr.add(grid[i][j]);
            }
        }
        return arr;
    }

    public int[][] getGrid() {
        return grid;
    }

    public void setGrid(int[][] src) {
        for (int i = 0; i < size; i++) {
            System.arraycopy(src[i], 0, this.grid[i], 0, size);
        }
    }

    public int getSize() {
        return size;
    }

    public int getNumber(int x, int y) {
        return grid[x][y];
    }

    public void setNumber(int x, int y, int number) {
        validateNumber(number);
        grid[x][y] = number;
    }

    public void setNumber(Point point, int number) {
        setNumber(point.getX(), point.getY(), number);
    }


    public void shiftLeft() {
        shift(forward, forward, RIGHT);
    }

    public void shiftRight() {
        shift(forward, backward, LEFT);
    }

    public void shiftUp() {
        shift(forward, forward, DOWN);
    }

    public void shiftDown() {
        shift(backward, forward, UP);
    }

    private void shift(int[] outer, int[] inner, int dir) {
        boolean shifted = false;
        for (int i : outer) {
            for (int j : inner) {
                shifted |= shiftCell(i, j, dir);
            }
        }
        if (shifted) {
            return;
        }
        throw new IllegalShiftException();
    }

    private boolean withinBound(int i, int j) {
        return i >= 0 && i < size && j >= 0 && j < size;
    }

    private boolean shiftCell(int i, int j, int dir) {
        int x = i + direction[dir][0], y = j + direction[dir][1];
        while (withinBound(x, y) && grid[x][y] == 0) {
            x += direction[dir][0];
            y += direction[dir][1];
        }
        if (!withinBound(x, y)) {
            return false;
        }
        if (grid[i][j] == 0) {
            grid[i][j] = grid[x][y];
            grid[x][y] = 0;
            shiftCell(i, j, dir);
            return true;
        }
        if (grid[i][j] == grid[x][y]) {
            grid[i][j] *= 2;
            grid[x][y] = 0;
            score += grid[i][j];
            return true;
        }
        return false;
    }

    public boolean haveMovableDirection() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1; j++) {
                if (grid[i][j] == 0) {
                    return true;
                }
                if (grid[i][j] == grid[i + 1][j] ||
                    grid[i][j] == grid[i][j + 1]) {
                    return true;
                }
            }
        }
        return false;
    }

    private void validateNumber(int number) {
        if (number % 2 == 0) {
            return;
        }
        throw new IllegalArgumentException("number should be even number");
    }

    private void validateSize(int size) {
        if (size > 5) {
            throw new IllegalArgumentException("size should be < 5");
        }
    }

    public List<Point> getEmptyPoints() {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j] != 0) {
                    continue;
                }
                points.add(new Point(i, j));
            }
        }
        return points;
    }

    public int getScore() {
        return score;
    }
}
