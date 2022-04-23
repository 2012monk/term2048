package term2048.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static term2048.controller.GridTestUtil.getResult;
import static term2048.controller.GridTestUtil.setUpGrid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

class GameGridTest {

    void testGameLogic(GameGrid grid, List<Integer> answer, Consumer<GameGrid> move) {
        move.accept(grid);
        assertThat(answer).isEqualTo(getResult(grid));
    }

    @Test
    void testCase1() {
        GameGrid testcase = setUpGrid(
            2, 2, 2, 2,
            8, 0, 0, 2,
            16, 0, 8, 32,
            32, 0, 0, 64
        );
        List<Integer> ans = List.of(
            4,4,0,0,
            8,2,0,0,
            16,8,32,0,
            32,64,0,0
        );
        testGameLogic(testcase, ans, GameGrid::shiftLeft);
    }

    @Test
    void gameLogicTest() {
        GameGrid testcase = setUpGrid(
            2, 2, 2, 2,
            8, 0, 0, 2,
            16, 0, 0, 32,
            32, 0, 0, 64
        );
        List<Integer> answer = List.of(
            0, 0, 0, 0,
            0, 0, 0, 2,
            0, 0, 0, 32,
            0, 0, 0, 128
        );
        testGameLogic(testcase, answer,
            grid -> {
                grid.shiftRight();
                grid.shiftLeft();
                grid.shiftDown();
                grid.shiftDown();
                grid.shiftDown();
                grid.shiftRight();
            }
        );
    }

    @Test
    void shiftVerticalUpTest() {
        GameGrid grid = new GameGrid();
        int[][] testCase = {
            {2, 2, 4, 2},
            {0, 2, 4, 0},
            {0, 0, 8, 4},
            {2, 4, 4, 2}
        };
        int[][] answer = {
            {4, 4, 8, 2},
            {0, 4, 8, 4},
            {0, 0, 4, 2},
            {0, 0, 0, 0}
        };
        grid.setGrid(testCase);
        grid.shiftUp();
        List<Integer> ans = new ArrayList<>();
        Arrays.stream(answer).forEach(
            a -> ans.addAll(Arrays.stream(a).boxed().collect(Collectors.toList()))
        );
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < answer.length; i++) {
            for (int j = 0; j < answer.length; j++) {
                result.add(grid.getNumber(i, j));
            }
        }

        assertThat(result).isEqualTo(ans);
    }

    @Test
    void shiftVerticalDownTest() {
        GameGrid grid = new GameGrid();
        int[][] testCase = {
            {2, 0, 4, 2},
            {2, 4, 4, 4},
            {0, 0, 4, 4},
            {0, 4, 4, 2}
        };
        int[][] answer = {
            {0, 0, 0, 0},
            {0, 0, 0, 2},
            {0, 0, 8, 8},
            {4, 8, 8, 2}
        };
        grid.setGrid(testCase);
        grid.shiftDown();
        for (int i = 0; i < answer.length; i++) {
            for (int j = 0; j < answer.length; j++) {
                assertEquals(answer[i][j], grid.getNumber(i, j));
            }
        }
    }

    @Test
    void shiftTest() {
        GameGrid grid = new GameGrid();
        grid.setNumber(0, 0, 2);
        grid.setNumber(0, 1, 2);

        grid.setNumber(1, 0, 2);
        grid.setNumber(1, 1, 4);
        grid.setNumber(1, 2, 8);
        grid.setNumber(1, 3, 16);

        grid.setNumber(2, 0, 2);
        grid.setNumber(2, 3, 2);

        grid.setNumber(3, 1, 2);
        grid.setNumber(3, 2, 4);
        grid.setNumber(3, 3, 8);

        grid.shiftLeft();
        assertEquals(4, grid.getNumber(0, 0));

        assertEquals(2, grid.getNumber(1, 0));
        assertEquals(4, grid.getNumber(1, 1));
        assertEquals(8, grid.getNumber(1, 2));
        assertEquals(16, grid.getNumber(1, 3));

        assertEquals(2, grid.getNumber(3, 0));
        assertEquals(4, grid.getNumber(3, 1));
        assertEquals(8, grid.getNumber(3, 2));
        for (int i = 1; i < grid.getSize(); i++) {
            assertEquals(grid.getNumber(0, i), 0);
        }
    }

    @Test
    void shiftRightTest() {
        GameGrid grid = new GameGrid();
        grid.setNumber(0, 3, 2);
        grid.setNumber(0, 2, 2);

        grid.setNumber(1, 0, 2);
        grid.setNumber(1, 1, 2);
        grid.setNumber(1, 2, 2);
        grid.setNumber(1, 3, 2);

        grid.setNumber(2, 0, 4);

        grid.setNumber(3, 0, 2);
        grid.setNumber(3, 1, 4);
        grid.shiftRight();
        assertEquals(grid.getNumber(0, 3), 4);

        assertEquals(grid.getNumber(1, 3), 4);
        assertEquals(grid.getNumber(1, 2), 4);
        assertEquals(grid.getNumber(1, 1), 0);
        assertEquals(grid.getNumber(1, 0), 0);

        assertEquals(grid.getNumber(2, 3), 4);
        assertEquals(grid.getNumber(3, 3), 4);
        assertEquals(grid.getNumber(3, 2), 2);
        for (int i = 0; i <= 2; i++) {
            assertEquals(grid.getNumber(0, i), 0);
        }
    }
}