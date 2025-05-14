package com.edsoft.okey_app.manager.placer;

import com.edsoft.okey_app.model.Color;
import com.edsoft.okey_app.model.Tile;

import java.util.*;
import java.util.stream.Collectors;

public class SequentialSeriesPlacer implements TileStrategy {

    /**
     * Finds and returns valid series of tiles that have consecutive numbers within the same color.
     * A valid series consists of at least 3 consecutive tiles with the same color.
     * Only tiles that are not marked as "okey" (joker) are considered, and tiles that have already been used are excluded.
     * Once a valid series is found, the tiles are added to the usedTiles set to prevent reuse.
     *
     * @param tiles     the list of all tiles to search from
     * @param usedTiles a set of tiles that have already been used in previous series or groups
     * @return a list of valid tile series (each series is a list of consecutive tiles with the same color)
     */
    @Override
    public List<List<Tile>> findTiles(List<Tile> tiles, Set<Tile> usedTiles) {
        Map<Color, List<Tile>> byColor = tiles.stream()
                .filter(t -> !t.isOkey())
                .collect(Collectors.groupingBy(Tile::getColor));

        List<List<Tile>> seriesList = new ArrayList<>();

        for (List<Tile> colorGroup : byColor.values()) {
            colorGroup.sort(Comparator.comparingInt(Tile::getNumber));
            List<Tile> currentSeries = new ArrayList<>();

            for (Tile tile : colorGroup) {
                if (usedTiles.contains(tile)) continue;

                if (currentSeries.isEmpty() || tile.getNumber() == currentSeries.get(currentSeries.size() - 1).getNumber() + 1) {
                    currentSeries.add(tile);
                } else {
                    if (currentSeries.size() >= 3) {
                        seriesList.add(new ArrayList<>(currentSeries));
                        usedTiles.addAll(currentSeries);
                    }
                    currentSeries.clear();
                    currentSeries.add(tile);
                }
            }

            if (currentSeries.size() >= 3) {
                seriesList.add(currentSeries);
                usedTiles.addAll(currentSeries);
            }
        }

        return seriesList;
    }
}
