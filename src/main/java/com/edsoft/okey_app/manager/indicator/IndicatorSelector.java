package com.edsoft.okey_app.manager.indicator;

import com.edsoft.okey_app.model.Tile;

import java.util.List;
import java.util.Random;

public class IndicatorSelector implements IndicatorManager {
    private final Random random = new Random();

    /**
     * Selects a random indicator tile from the provided tile list (excluding fake tiles),
     * then determines the Okey tile based on the indicator.
     *
     * @param tiles the list of tiles to select the indicator from
     * @return an IndicatorResult containing both the indicator and corresponding Okey tile
     * @throws IllegalArgumentException if the tile list is empty
     */
    @Override
    public IndicatorResult selectIndicator(List<Tile> tiles) {
        Tile indicator;

        if (tiles.isEmpty())
            throw new IllegalArgumentException("Tile list can not be empty.");

        do {
            indicator = tiles.get(random.nextInt(tiles.size()));
        } while (indicator.isFake());

        Tile okeyTile = determineOkeyTile(indicator);
        return new IndicatorResult(indicator, okeyTile);
    }

    /**
     * Determines the Okey tile based on the given indicator tile.
     * Okey is the tile that follows the indicator in number (13 wraps to 1),
     * and has the same color as the indicator.
     *
     * @param indicator the tile selected as the indicator
     * @return the corresponding Okey tile
     */
    private Tile determineOkeyTile(Tile indicator) {
        int nextNumber = indicator.getNumber() == 13 ? 1 : indicator.getNumber() + 1;
        return new Tile(indicator.getColor(), nextNumber, false);
    }
}
