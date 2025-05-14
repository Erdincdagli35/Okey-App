package com.edsoft.okey_app.manager.distribution;


import com.edsoft.okey_app.model.Player;
import com.edsoft.okey_app.model.Tile;

import java.util.List;

public interface TileDistributionStrategy {
    List<Player> distributeTiles(List<Player> players, List<Tile> tiles);
}
