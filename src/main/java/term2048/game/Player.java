package term2048.game;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import term2048.controller.GameGrid;
import termui.constants.Char;
import termui.constants.SpecialKeyCode;

public class Player {

    private Map<Char, Consumer<GameGrid>> moves = new HashMap<>();

    public Player() {
        moves.put(Char.of(SpecialKeyCode.KEY_UP), GameGrid::shiftUp);
        moves.put(Char.of(SpecialKeyCode.KEY_DOWN), GameGrid::shiftDown);
        moves.put(Char.of(SpecialKeyCode.KEY_LEFT), GameGrid::shiftLeft);
        moves.put(Char.of(SpecialKeyCode.KEY_RIGHT), GameGrid::shiftRight);
    }

    public void consumeTurn(Char input, GameGrid grid) {
        if (!moves.containsKey(input)) {
            return;
        }
        moves.get(input).accept(grid);
    }

    public boolean isMoveKey(Char ch) {
        return moves.containsKey(ch);
    }
}
