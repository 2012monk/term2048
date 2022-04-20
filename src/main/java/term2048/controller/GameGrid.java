package term2048.controller;

import java.util.ArrayList;
import java.util.List;
import term2048.exceptions.IllegalShiftException;

public class GameGrid {

    private static final int DEFAULT_SIZE = 4;
    private final int[][] grid;
    private int size;

    public GameGrid() {
        this(DEFAULT_SIZE);
    }

    public GameGrid(int size) {
        validateSize(size);
        this.size = DEFAULT_SIZE;
        this.grid = new int[size][size];
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

    public int getSize() {
        return size;
    }

    public void setGrid(int[][] src) {
        for (int i = 0; i < size; i++) {
            System.arraycopy(src[i], 0, this.grid[i], 0, size);
        }
    }

    public int getNumber(int x, int y) {
        return grid[x][y];
    }

    public void setNumber(int x, int y, int number) {
        validateNumber(number);
        grid[x][y] = number;
    }

    public void shiftLeft() {
        validateHorizontalShift();
        fillEmptyLeft();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 1; j++) {
                if (grid[i][j] == 0 || grid[i][j] != grid[i][j + 1]) {
                    continue;
                }
                grid[i][j] += grid[i][j + 1];
                grid[i][j + 1] = 0;
                j++;
            }
        }
        fillEmptyLeft();
    }

    public void shiftRight() {
        validateHorizontalShift();
        fillEmptyRight();
        for (int i = 0; i < size; i++) {
            for (int j = size - 1; j > 0; j--) {
                if (grid[i][j] != grid[i][j - 1]) {
                    continue;
                }
                grid[i][j] += grid[i][j - 1];
                grid[i][j - 1] = 0;
                j--;
            }
        }
        fillEmptyRight();
    }

    public void shiftUp() {
        validateVerticalShift();
        fillEmptyUp();
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size - 1; i++) {
                if (grid[i][j] == 0 || grid[i][j] != grid[i + 1][j]) {
                    continue;
                }
                grid[i][j] += grid[i + 1][j];
                grid[i + 1][j] = 0;
                i++;

            }
        }
        fillEmptyUp();
    }

    public void shiftDown() {
        validateVerticalShift();
        fillEmptyDown();
        for (int j = 0; j < size; j++) {
            for (int i = size - 1; i > 0; i--) {
                if (grid[i][j] != grid[i - 1][j]) {
                    continue;
                }
                grid[i][j] += grid[i - 1][j];
                grid[i - 1][j] = 0;
                i--;
            }
        }
        fillEmptyDown();
    }

    private void fillEmptyDown() {
        for (int j = 0; j < size; j++) {
            for (int i = size - 1; i > 0; i--) {
                if (grid[i][j] != 0) continue;
                int x = i, src = i;
                while (x >= 0 && grid[x][j] == 0) x--;
                while (x >= 0) grid[src--][j] = grid[x--][j];
                while (src >= 0) grid[src--][j] = 0;
            }
        }
    }

    private void fillEmptyUp() {
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size - 1; i++) {
                if (grid[i][j] != 0) continue;
                int x = i, src = i;
                while (x < size && grid[x][j] == 0) x++;
                while (x < size) grid[src++][j] = grid[x++][j];
                while (src < size) grid[src++][j] = 0;
            }
        }
    }

    private void fillEmptyRight() {
        for (int i = 0; i < size; i++) {
            for (int j = size - 1; j > 0; j--) {
                if (grid[i][j] != 0) continue;
                int y = j, src = j;
                while (y >= 0 && grid[i][y] == 0) y--;
                while (y >= 0) grid[i][src--] = grid[i][y--];
                while (src >= 0) grid[i][src--] = 0;
            }
        }
    }

    private void fillEmptyLeft() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 1; j++) {
                if (grid[i][j] != 0) continue;
                int y = j, src = j;
                while (y < size && grid[i][y] == 0) y++;
                while (y < size) grid[i][src++] = grid[i][y++];
                while (src < size) grid[i][src++] = 0;
            }
        }
    }

    private void validateHorizontalShift() {
        if (isMovableHorizontal()) {
            return;
        }
        throw new IllegalShiftException();
    }

    private void validateVerticalShift() {
        if (isMovableVertical()) {
            return;
        }
        throw new IllegalShiftException();
    }

    private boolean isMovableHorizontal() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 1; j++) {
                if (grid[i][j] == 0) return true;
                if (grid[i][j] == grid[i][j + 1]) return true;
            }
        }
        return false;
    }

    private boolean isMovableVertical() {
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size - 1; i++) {
                if (grid[i][j] == 0) return true;
                if (grid[i][j] == grid[i + 1][j]) return true;
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
}
