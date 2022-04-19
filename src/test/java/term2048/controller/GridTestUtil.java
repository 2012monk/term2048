package term2048.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GridTestUtil {


    public static List<Integer> getResult(GameGrid grid) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < grid.getSize(); i++) {
            for (int j = 0; j < grid.getSize(); j++) {
                result.add(grid.getNumber(i, j));
            }
        }
        return result;
    }

    public static GameGrid setUpGrid(int... numbers) {
        int size = (int) Math.sqrt(numbers.length);
        GameGrid grid = new GameGrid(size);

        for (int i = 0; i < numbers.length; i++) {
            grid.setNumber(i / size, i % size, numbers[i]);
        }
        return grid;
    }

    String gridStatusAsString(int[][] grid) {
        return Arrays.stream(grid)
            .map(r -> Arrays.stream(r)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" ")))
            .collect(Collectors.joining("\n"));
    }
}
