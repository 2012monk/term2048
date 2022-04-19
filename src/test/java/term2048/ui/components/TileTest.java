package term2048.ui.components;

import org.junit.jupiter.api.Test;
import term2048.ui.TileInfoRepository;
import term2048.ui.constants.TileInfo;
import term2048.utils.InfoLoader;
import termui.Point;
import termui.StdBuffer;

class TileTest {

    @Test
    void test() {
        InfoLoader.load();
        TileInfo info = TileInfoRepository.getTileInfo(4);
        Tile tile = new Tile(info, new Point(4, 4));
        StdBuffer buffer = new StdBuffer(20, 20);
        tile.render(buffer);
        buffer.flush();
    }
}