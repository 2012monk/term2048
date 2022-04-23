package term2048.controller;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.function.Consumer;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import term2048.exceptions.IllegalShiftException;

public class GameGridInvalidTest {

    void testInvalidMove(GameGrid grid, Consumer<GameGrid> move) {
        assertThatThrownBy(() -> move.accept(grid))
            .isInstanceOf(IllegalShiftException.class);
    }

    void testValidMove(GameGrid grid, Consumer<GameGrid> move) {
        assertThatCode(() -> move.accept(grid)).doesNotThrowAnyException();
    }

    @Test
    void test10() {
        testValidMove(GridTestUtil.setUpGrid(
            4, 8, 4, 0,
            0, 0, 0, 0,
            2, 0, 0, 2,
            0, 0, 0,0
        ), GameGrid::shiftLeft);
    }

    @Test
    void test9() {
        testValidMove(GridTestUtil.setUpGrid(
            16, 4, 0, 0,
            0, 0, 0, 0,
            0, 0, 0, 0,
            4, 2, 0,0
        ), GameGrid::shiftRight);
    }

    @Test
    void test8() {
        testValidMove(GridTestUtil.setUpGrid(
            0, 0, 0, 0, 2, 2, 2, 2, 0, 0, 0, 0, 2, 0, 0, 0
        ), GameGrid::shiftUp);
    }

    @Test
    void testFullLine() {
        testInvalidMove(GridTestUtil.setUpGrid(
            2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
        ), GameGrid::shiftUp);
    }

    @Test
    void test() {
        testValidMove(GridTestUtil.setUpGrid(
            0, 0, 0, 0, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0
        ), GameGrid::shiftUp);
    }

    @Test
    void test2() {
        testValidMove(GridTestUtil.setUpGrid(
            2, 2, 2, 2, 0, 0, 0, 0, 2, 2, 2, 2, 0, 0, 0, 0
        ), GameGrid::shiftUp);
    }

    @Test
    void test3() {
        testValidMove(GridTestUtil.setUpGrid(
            2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0
        ), GameGrid::shiftUp);
    }

    @Test
    void test4() {
        testValidMove(GridTestUtil.setUpGrid(
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2
        ), GameGrid::shiftUp);
    }

    @Test
    void test5() {
        testInvalidMove(GridTestUtil.setUpGrid(
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
        ), GameGrid::shiftUp);
    }

    @Test
    void test6() {
        testInvalidMove(GridTestUtil.setUpGrid(
            2, 2, 2, 2, 4, 4, 4, 4, 2, 2, 2, 2, 4, 4, 4, 4
        ), GameGrid::shiftUp);
    }

    @Test
    void test7() {
        testValidMove(GridTestUtil.setUpGrid(
            2, 2, 2, 2, 4, 4, 4, 4, 0, 0, 2, 2, 4, 4, 4, 4
        ), GameGrid::shiftUp);
    }

    @Test
    void testFullGridDownShift() {
        testInvalidMove(getFixedGrid(), GameGrid::shiftDown);
    }

    @Test
    void testFullGridUp() {
        testInvalidMove(getFixedGrid(), GameGrid::shiftUp);
    }

    @Test
    void testFullGridLeft() {
        testInvalidMove(getFixedGrid(), GameGrid::shiftLeft);
    }

    @Test
    void testFullGridRight() {
        testInvalidMove(getFixedGrid(), GameGrid::shiftRight);
    }

    private GameGrid getFixedGrid() {
        return GridTestUtil.setUpGrid(
            4, 2, 4, 2,
            2, 4, 2, 4,
            4, 2, 4, 2,
            2, 4, 2, 4
        );
    }
}
