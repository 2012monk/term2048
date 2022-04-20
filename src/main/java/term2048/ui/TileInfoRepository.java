package term2048.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import term2048.ui.constants.TileInfo;

public class TileInfoRepository {

    private static final Map<Integer, TileInfo> storage = new HashMap<>();

    public static TileInfo getTileInfo(int number) {
        if (storage.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (storage.containsKey(number)) {
            return storage.get(number);
        }
        return storage.get(0);
    }

    public static void addInfos(List<TileInfo> value) {
        value.forEach(v -> storage.put(v.getNumber(), v));
    }

    public static List<TileInfo> tiles() {
        return new ArrayList<>(storage.values());
    }

    public static TileInfo getDefaultTile() {
        return storage.get(-1);
    }
}
