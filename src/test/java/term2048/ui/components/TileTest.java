package term2048.ui.components;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import term2048.ui.TileInfoRepository;
import term2048.ui.constants.TileInfo;
import term2048.utils.InfoLoader;
import termui.Point;
import termui.StdBuffer;

class TileTest {

    private ByteArrayOutputStream out;
    private PrintStream orig;

    @BeforeEach
    void setUp() {
        orig = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @AfterEach
    void tearDown() {
        System.setOut(orig);
    }

    @Test
    void test() {
        InfoLoader.load();
        TileInfo info = TileInfoRepository.getTileInfo(4);
        Tile tile = new Tile(info,new Point(4, 4), new Point(16,16));
        StdBuffer buffer = new StdBuffer(20, 20);
        tile.render(buffer);
        buffer.flush();
    }

    @Test
    void displayNumberTest() {
        InfoLoader.load();
        TileInfo info = TileInfoRepository.getTileInfo(128);
        Tile tile = new Tile(info,new Point(4, 4), new Point(16,16));
        StdBuffer buffer = new StdBuffer(40, 40);
        tile.render(buffer);
        buffer.flush();
        assertThat(out.toString()).contains("128");
    }
}