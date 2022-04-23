package term2048.ui.components;

import termui.BorderRectangle;
import termui.Buffer;
import termui.Point;
import termui.constants.BorderAttribute;

public class ScoreBoard {

    private final BorderRectangle rectangle;
    private int score = 0;

    public ScoreBoard(Point min, Point max) {
        this.rectangle = new BorderRectangle(new BorderAttribute(), min, max);
    }

    public void addScore(int score) {
        this.score += score;
    }

    public void render(Buffer buffer) {
        buffer.setString(rectangle.getMin(), "socre: " + score);
        buffer.setBorder(rectangle.getBorderMin(), rectangle.getBorderMax(),
            rectangle.getAttribute());
    }
}
