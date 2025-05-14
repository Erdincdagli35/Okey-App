package com.edsoft.okey_app.manager.placer;

import com.edsoft.okey_app.model.Tile;

import java.util.*;
import java.util.stream.Collectors;

public class PairPlacer implements TileStrategy {

    /**
     * Finds and returns all valid pairs of tiles with the same number and color.
     * A valid pair consists of exactly two tiles that share both the same number and color.
     * Only tiles that are not marked as "okey" (joker) and not already used are considered.
     * Once a valid pair is found, the tiles are added to the usedTiles set to prevent reuse.
     *
     * @param tiles     the list of all tiles to search from
     * @param usedTiles a set of tiles that have already been used in previous groups or pairs
     * @return a list of valid tile pairs (each pair is a list of 2 tiles)
     */
    @Override
    public List<List<Tile>> findTiles(List<Tile> tiles, Set<Tile> usedTiles) {
        Map<String, List<Tile>> grouped = tiles.stream()
                .filter(t -> !t.isOkey() && !usedTiles.contains(t))
                .collect(Collectors.groupingBy(t -> t.getNumber() + "-" + t.getColor()));

        List<List<Tile>> pairs = new ArrayList<>();

        for (List<Tile> group : grouped.values()) {
            while (group.size() >= 2) {
                Tile t1 = group.remove(0);
                Tile t2 = group.remove(0);
                pairs.add(Arrays.asList(t1, t2));
                usedTiles.add(t1);
                usedTiles.add(t2);
            }
        }

        return pairs;
    }
}
