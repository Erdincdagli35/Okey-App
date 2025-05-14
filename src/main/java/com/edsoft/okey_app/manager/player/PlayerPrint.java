package com.edsoft.okey_app.manager.player;


import com.edsoft.okey_app.manager.generator.TilePrint;
import com.edsoft.okey_app.model.Player;
import com.edsoft.okey_app.model.PlayerHand;
import com.edsoft.okey_app.model.Tile;

import java.util.List;

public class PlayerPrint {
    private PlayerManager playerManager;
    private TilePrint tilePrint;

    public PlayerPrint(PlayerManager playerManager, TilePrint tilePrint) {
        this.playerManager = playerManager;
        this.tilePrint = tilePrint;
    }

    public void printPlayerSize(List<Player> players) {
        System.out.println("Oyuncu Sayısı : " + players.size());
    }

    public Integer playerSize(List<Player> players) {
        return players.size();
    }

    public void printTileByPlayer(Player player, PlayerHand hand) {
        List<Tile> tiles = hand.getTiles();
        String tileList = tilePrint.tilesPrintSpecificByString(tiles);

        System.out.println("Player : " + player.getId() +
                "\nHand : " +
                "{" + tileList + "}");
    }
}
