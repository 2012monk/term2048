package term2048.controller;

import java.util.function.Consumer;
import org.junit.jupiter.api.Test;
import term2048.exceptions.IllegalShiftException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameGridInvalidTest {

    void testInvalidMove(GameGrid grid, Consumer<GameGrid> move) {
        assertThatThrownBy(() -> move.accept(grid))
            .isInstanceOf(IllegalShiftException.class);
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
            4,2,4,2,
            2,4,2,4,
            4,2,4,2,
            2,4,2,4
        );
    }
}
