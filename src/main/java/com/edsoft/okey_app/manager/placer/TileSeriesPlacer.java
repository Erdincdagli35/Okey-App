package com.edsoft.okey_app.manager.placer;

import com.edsoft.okey_app.model.Color;
import com.edsoft.okey_app.model.Tile;

import java.util.*;
import java.util.stream.Collectors;

public class TileSeriesPlacer {

    private final Tile okeyTile;
    private final Tile indicatorTile;
    private final List<TileStrategy> strategies;

    public TileSeriesPlacer(Tile okeyTile, Tile indicatorTile) {
        this.okeyTile = okeyTile;
        this.indicatorTile = indicatorTile;
        this.strategies = Arrays.asList(new SequentialSeriesPlacer(),
                new GroupSeriesPlacer(),
                new PairPlacer());
    }

    public List<List<Tile>> findAllValidSets(List<Tile> tiles) {
        List<List<Tile>> allSets = new ArrayList<>();
        Set<Tile> usedTiles = new HashSet<>();
        int okeyCount = (int) tiles.stream().filter(Tile::isOkey).count();

        for (TileStrategy strategy : strategies) {
            allSets.addAll(strategy.findTiles(tiles, usedTiles));
        }

        List<List<Tile>> strategicSets = useOkeyTilesStrategically(tiles, okeyCount, usedTiles);
        allSets.addAll(strategicSets);

        return allSets;
    }

    public Map<String, Integer> countSetTypes(List<Tile> tiles) {
        Map<String, Integer> counts = new HashMap<>();
        Set<Tile> usedTiles = new HashSet<>();

        int seriesCount = new SequentialSeriesPlacer().findTiles(tiles, usedTiles).size();
        int groupCount = new GroupSeriesPlacer().findTiles(tiles, usedTiles).size();
        int pairCount = new PairPlacer().findTiles(tiles, usedTiles).size();

        counts.put("series", seriesCount);
        counts.put("group", groupCount);
        counts.put("pair", pairCount);

        return counts;
    }

    private List<List<Tile>> useOkeyTilesStrategically(List<Tile> tiles, int okeyCount, Set<Tile> usedTiles) {
        List<List<Tile>> sets = new ArrayList<>();

        Map<Color, List<Tile>> byColor = tiles.stream()
                .filter(t -> !t.isOkey() && !usedTiles.contains(t))
                .collect(Collectors.groupingBy(Tile::getColor));

        for (List<Tile> group : byColor.values()) {
            group.sort(Comparator.comparingInt(Tile::getNumber));
            for (int i = 0; i < group.size(); i++) {
                if (usedTiles.contains(group.get(i))) continue;

                List<Tile> potentialSeries = new ArrayList<>();
                int expected = group.get(i).getNumber();
                int tempOkeyCount = okeyCount;

                for (int j = i; j < group.size() || tempOkeyCount > 0; j++) {
                    Tile current = (j < group.size()) ? group.get(j) : null;

                    if (current != null && current.getNumber() == expected && !usedTiles.contains(current)) {
                        potentialSeries.add(current);
                    } else if ((current == null || current.getNumber() > expected) && tempOkeyCount > 0) {
                        potentialSeries.add(new Tile(group.get(i).getColor(), expected, true)); // Okey ile boşluk doldur
                        tempOkeyCount--;
                        j--;
                    } else {
                        break;
                    }
                    expected++;
                }

                if (potentialSeries.size() >= 3) {
                    sets.add(new ArrayList<>(potentialSeries));
                    for (Tile t : potentialSeries) {
                        if (t.isOkey()) okeyCount--;
                        else usedTiles.add(t);
                    }
                }
            }
        }

        Map<Integer, List<Tile>> byNumber = tiles.stream()
                .filter(t -> !t.isOkey() && !usedTiles.contains(t))
                .collect(Collectors.groupingBy(Tile::getNumber));

        for (Map.Entry<Integer, List<Tile>> entry : byNumber.entrySet()) {
            List<Tile> group = entry.getValue();
            Map<Color, Tile> unique = new HashMap<>();
            for (Tile tile : group) unique.putIfAbsent(tile.getColor(), tile);
            List<Tile> groupSet = new ArrayList<>(unique.values());

            int missing = 3 - groupSet.size();
            if (groupSet.size() >= 1 && groupSet.size() + okeyCount >= 3 && missing <= okeyCount) {
                for (Color color : Color.values()) {
                    if (groupSet.stream().noneMatch(t -> t.getColor() == color)) {
                        groupSet.add(new Tile(color, entry.getKey(), true));
                        missing--;
                        if (--okeyCount == 0 || missing == 0) break;
                    }
                }
                sets.add(new ArrayList<>(groupSet));
                usedTiles.addAll(group);
                usedTiles.addAll(groupSet.stream().filter(Tile::isOkey).collect(Collectors.toList()));
            }
        }

        for (List<Tile> set : sets) {
            if (okeyCount == 0) break;
            // Sıralı set mi kontrol et
            boolean isSequence = set.stream().allMatch(t -> t.getColor() == set.get(0).getColor());
            if (isSequence) {
                set.sort(Comparator.comparingInt(Tile::getNumber));
                int nextNumber = set.get(set.size() - 1).getNumber() + 1;
                set.add(new Tile(set.get(0).getColor(), nextNumber, true));
                okeyCount--;
            }
        }

        if (okeyCount > 0) {
            for (List<Tile> group : byNumber.values()) {
                for (Tile t : group) {
                    if (usedTiles.contains(t)) continue;
                    sets.add(Arrays.asList(t, new Tile(t.getColor(), t.getNumber(), true)));
                    usedTiles.add(t);
                    okeyCount--;
                    if (okeyCount == 0) break;
                }
                if (okeyCount == 0) break;
            }
        }

        return sets;
    }

    public void printSeries(List<List<Tile>> seriesList) {
        int index = 1;
        for (List<Tile> series : seriesList) {
            System.out.print("Series " + index++ + ": ");
            series.forEach(tile -> System.out.print(tile + " "));
            System.out.println();
        }
    }
}
