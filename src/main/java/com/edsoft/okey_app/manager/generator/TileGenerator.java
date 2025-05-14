package com.edsoft.okey_app.manager.generator;

import com.edsoft.okey_app.model.Color;
import com.edsoft.okey_app.model.Tile;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TileGenerator implements TileManager {

    /**
     * Generates a complete list of Okey tiles, including two of each tile
     * from number 1 to 13 for each color (excluding the fake color 'SAHTE').
     * Also adds two fake Okey tiles.
     *
     * @return A shuffled list of all tiles.
     */
    @Override
    public List<Tile> generateTiles() {
        List<Tile> tiles = Arrays.stream(Color.values())
                .filter(color -> color != Color.SAHTE)
                .flatMap(color ->
                        IntStream.rangeClosed(1, 13)
                                .mapToObj(number -> new Tile(color, number, false))
                                .flatMap(tile -> Stream.of(tile, tile))
                )
                .collect(Collectors.toList());

        generateFakeOkeyTiles(tiles);

        Collections.shuffle(tiles);

        return tiles;
    }

    /**
     * Adds two identical fake Okey tiles (SAHTE) to the given tile list.
     *
     * @param tiles The list to which the fake tiles will be added.
     */
    private void generateFakeOkeyTiles(List<Tile> tiles) {
        Tile fakeOkey = new Tile(Color.SAHTE, -1, true);
        tiles.add(fakeOkey);
        tiles.add(fakeOkey);
    }
}
