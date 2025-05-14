package com.edsoft.okey_app.manager.placer;

import com.edsoft.okey_app.model.Tile;

import java.util.List;
import java.util.Set;

public interface TileStrategy {
    List<List<Tile>> findTiles(List<Tile> tiles, Set<Tile> usedTiles);
}
