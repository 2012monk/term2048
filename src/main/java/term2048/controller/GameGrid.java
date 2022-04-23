package term2048.controller;

import java.util.ArrayList;
import java.util.List;
import term2048.exceptions.IllegalShiftException;
import termui.Point;

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

    public void setNumber(Point point, int number) {
        setNumber(point.getX(), point.getY(), number);
    }

    public void shiftLeft() {
        validateShiftLeft();
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
        validateShiftRight();
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
        validateShiftUp();
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
        validateShiftDown();
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

    private void validateShiftLeft() {
        for (int i = 0; i < size; i++) {
            int j = 0, k;
            while (j < size - 1 && grid[i][j] != 0) {
                if (grid[i][j] == grid[i][j + 1]) return;
                j++;
            }
            k = j;
            while (k < size && grid[i][k] == 0) k++;
            if (k != j && k < size) return;
        }
        throw new IllegalShiftException();
    }

    private void validateShiftUp() {
        for (int i = 0; i < size; i++) {
            int j = 0, k;
            while (j < size - 1 && grid[j][i] != 0) {
                if (grid[j][i] == grid[j + 1][i]) return;
                j++;
            }
            k = j;
            while (k < size && grid[k][i] == 0) k++;
            if (k != j && k < size) return;
        }
        throw new IllegalShiftException();
    }

    private void validateShiftRight() {
        for (int i = 0; i < size; i++) {
            int j = size - 1, k;
            while (j > 0 && grid[i][j] != 0) {
                if (grid[i][j] == grid[i][j - 1]) return;
                j--;
            }
            k = j;
            while (k >= 0 && grid[i][k] == 0) k--;
            if (k != j && k >= 0) return;
        }
        throw new IllegalShiftException();
    }

    private void validateShiftDown() {
        for (int i = 0; i < size; i++) {
            int j = size - 1,k;
            while (j > 0 && grid[j][i] != 0) {
                if (grid[j][i] == grid[j-1][i]) return;
                j--;
            }
            k = j;
            while (k >= 0 && grid[k][i] == 0) k--;
            if (k != j && k >= 0) return;
        }
        throw new IllegalShiftException();
    }

    public boolean haveMovableDirection() {
        try {
            validateShiftLeft();
            return true;
        }catch (Exception ignore) {}
        try {
            validateShiftRight();
            return true;
        } catch (Exception ignore) {}
        try {
            validateShiftUp();
            return true;
        } catch (Exception ignore) {}
        try {
            validateShiftDown();
            return true;
        } catch (Exception ignored) {
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
                if (grid[i][j] != 0) continue;
                points.add(new Point(i, j));
            }
        }
        return points;
    }
}
