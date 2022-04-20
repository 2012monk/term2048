package term2048.ui.components;

import java.util.ArrayList;
import java.util.List;
import term2048.controller.GameGrid;
import term2048.ui.TileInfoRepository;
import term2048.ui.constants.TileInfo;
import termui.BorderRectangle;
import termui.CursesTerminal;
import termui.Point;
import termui.Rectangle;

public class TileGrid {

    private static final int TILE_WIDTH = 12;
    private static final int TILE_HEIGHT = 7;
    private Rectangle rectangle;
    private List<Tile> tiles = new ArrayList<>();
    private int tileCount;

    public TileGrid(int tileCount, Point min) {
        this.tileCount = tileCount;
        this.rectangle = new BorderRectangle(min, getEndPoint(min));
        init();
    }

    public void render() {
        CursesTerminal buffer = new CursesTerminal();
        buffer.lock();
        tiles.forEach(t -> t.render(buffer));
        buffer.unlock();
    }

    public void updateStatus(GameGrid grid) {
        List<Integer> status = grid.getStatus();
        for (int i = 0; i < status.size(); i++) {
            tiles.get(i).changeInfo(TileInfoRepository.getTileInfo(status.get(i)));
        }
    }

    private void init() {
        int x = rectangle.getMin().getX();
        int y = rectangle.getMin().getY();
        int h = TILE_HEIGHT;
        int w = TILE_WIDTH;
        TileInfo info = TileInfoRepository.getDefaultTile();
        for (int i = 0; i < tileCount; i++) {
            for (int j = 0; j < tileCount; j++) {
                Point p = new Point(x + i * h, y + j * w);
                Point mx = new Point(p.getX() + h, p.getY() + w);
                tiles.add(new Tile(info, p, mx));
            }
        }
    }

    private Point getEndPoint(Point min) {
        return new Point(min.getX() + TILE_HEIGHT * tileCount, min.getY() + TILE_WIDTH * tileCount);
    }
}
