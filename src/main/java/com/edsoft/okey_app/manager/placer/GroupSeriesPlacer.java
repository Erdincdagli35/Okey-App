package com.edsoft.okey_app.manager.placer;

import com.edsoft.okey_app.model.Color;
import com.edsoft.okey_app.model.Tile;

import java.util.*;
import java.util.stream.Collectors;

public class GroupSeriesPlacer implements TileStrategy {

    /**
     * Finds and returns valid groups of tiles that have the same number but different colors.
     * A valid group consists of at least 3 tiles with the same number and different colors.
     * Only tiles that are not marked as "okey" (joker) and not already used are considered.
     * Once a valid group is formed, the tiles are added to the usedTiles set to prevent reuse.
     *
     * @param tiles     the list of all tiles to search from
     * @param usedTiles a set of tiles that have already been used in previous groups
     * @return a list of valid tile groups (each group is a list of 3 tiles)
     */
    @Override
    public List<List<Tile>> findTiles(List<Tile> tiles, Set<Tile> usedTiles) {
        Map<Integer, List<Tile>> byNumber = tiles.stream()
                .filter(t -> !t.isOkey() && !usedTiles.contains(t))
                .collect(Collectors.groupingBy(Tile::getNumber));

        List<List<Tile>> groupList = new ArrayList<>();

        for (List<Tile> group : byNumber.values()) {
            Map<Color, Tile> uniqueByColor = new HashMap<>();
            for (Tile t : group) {
                uniqueByColor.putIfAbsent(t.getColor(), t);
            }

            List<Tile> unique = new ArrayList<>(uniqueByColor.values());
            if (unique.size() >= 3) {
                List<Tile> groupSet = unique.subList(0, 3);
                groupList.add(groupSet);
                usedTiles.addAll(groupSet);
            }
        }

        return groupList;
    }
}
