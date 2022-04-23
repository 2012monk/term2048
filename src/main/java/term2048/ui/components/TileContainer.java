package term2048.ui.components;

import java.util.ArrayList;
import java.util.List;
import term2048.controller.GameGrid;
import term2048.ui.TileInfoRepository;
import term2048.ui.constants.TileInfo;
import termui.Buffer;
import termui.Point;
import termui.Rectangle;

public class TileContainer {

    private static final int TILE_WIDTH = 12;
    private static final int TILE_HEIGHT = 7;
    private final Rectangle rectangle;
    private final List<Tile> tiles = new ArrayList<>();
    private final int tileCount;

    public TileContainer(int tileCount, Buffer buffer) {
        this.tileCount = tileCount;
        Point p = new Point(0, (buffer.getWidth() - getWidth()) / 2);
        this.rectangle = new Rectangle(p, new Point(getHeight(), p.getY() + getWidth()));
        init();
    }

    public TileContainer(int tileCount, Point min) {
        this.tileCount = tileCount;
        this.rectangle = new Rectangle(min, getEndPoint(min));
        init();
    }

    public Point getMax() {
        return rectangle.getMax();
    }

    private int getWidth() {
        return TILE_WIDTH * tileCount - tileCount - 1;
    }

    private int getHeight() {
        return TILE_HEIGHT * tileCount - tileCount - 1;
    }

    public void render(Buffer buffer) {
        tiles.forEach(t -> t.render(buffer));
        buffer.flush();
    }

    public void updateStatus(GameGrid grid) {
        List<Integer> status = grid.getStatus();
        for (int i = 0; i < status.size(); i++) {
            tiles.get(i).changeInfo(TileInfoRepository.getTileInfo(status.get(i)));
        }
    }

    private void init() {
        List<Point> points = rectangle.getPoints();
        TileInfo info = TileInfoRepository.getDefaultTile();
        for (int i = 0; i < rectangle.getHeight(); i += TILE_HEIGHT - 1) {
            for (int j = 0; j < rectangle.getWidth(); j += TILE_WIDTH - 1) {
                Point s = points.get(i * rectangle.getWidth() + j);
                Point e = new Point(s.getX() + TILE_HEIGHT, s.getY() + TILE_WIDTH);
                tiles.add(new Tile(info, s, e));
            }
        }
    }

    private Point getEndPoint(Point min) {
        return new Point(min.getX() + (TILE_HEIGHT) * tileCount - tileCount - 1,
            min.getY() + (TILE_WIDTH) * tileCount - tileCount - 1);
    }
}
