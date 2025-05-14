package com.edsoft.okey_app.model;

import java.util.ArrayList;
import java.util.List;

public class PlayerHand {
    private Player player; //It has been used aggregation
    private final List<Tile> tiles = new ArrayList<>();

    public PlayerHand(Player player) {
        this.player = player;
    }

    public void addTile(Tile tile) {
        tiles.add(tile);
    }

    public void addTiles(List<Tile> tiles) {
        this.tiles.addAll(tiles);
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
