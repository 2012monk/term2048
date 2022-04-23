package termui;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class RectangleTest {

    @Test
    void containsTest() {
        Rectangle rectangle = new Rectangle(new Point(0, 0), new Point(5, 5));
        assertThat(rectangle.contains(new Point(5,5))).isTrue();
        assertThat(rectangle.contains(new Point(0, 0))).isTrue();
        assertThat(rectangle.contains(new Point(5,6))).isFalse();
    }

    @Test
    void convertTest() {
        Rectangle rectangle = new Rectangle(new Point(1, 1), new Point(5, 5));
        Point p = rectangle.getRelative(new Point(1, 1));
        assertThat(p).isEqualTo(new Point(0, 0));
    }
}