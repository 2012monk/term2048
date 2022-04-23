package term2048.ui;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import term2048.ui.constants.TileInfo;

public class TileInfoRepository {

    private static final TreeMap<Integer, TileInfo> storage = new TreeMap<>();
    private static int max;

    public static TileInfo getTileInfo(int number) {
        if (storage.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (max < number) {
            return storage.get(-1);
        }
        if (storage.containsKey(number)) {
            return storage.get(number);
        }
        return storage.get(0);
    }

    public static void addInfos(List<TileInfo> value) {
        value.forEach(v -> storage.put(v.getNumber(), v));
        max = storage.keySet().stream().max(Comparator.comparingInt(v -> v)).orElse(0);
    }

    public static List<TileInfo> tiles() {
        return new ArrayList<>(storage.values());
    }

    public static TileInfo getDefaultTile() {
        return storage.get(0);
    }
}
