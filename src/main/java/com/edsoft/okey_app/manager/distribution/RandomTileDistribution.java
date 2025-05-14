package com.edsoft.okey_app.manager.distribution;

import com.edsoft.okey_app.manager.player.PlayerPrint;
import com.edsoft.okey_app.model.Player;
import com.edsoft.okey_app.model.PlayerHand;
import com.edsoft.okey_app.model.Tile;

import java.util.List;
import java.util.Random;

public class RandomTileDistribution implements TileDistributionStrategy {

    private PlayerPrint playerPrint;

    public RandomTileDistribution(PlayerPrint playerPrint) {
        this.playerPrint = playerPrint;
    }

    /**
     * Distributes tiles randomly to each player.
     * The first player receives 15 tiles, while the others receive 14.
     * Tiles are removed from the main tile list as they are assigned.
     *
     * @param players the list of players who will receive tiles
     * @param tiles   the list of available tiles to distribute
     * @return the list of players with their hands assigned
     */
    @Override
    public List<Player> distributeTiles(List<Player> players, List<Tile> tiles) {
        Random random = new Random();

        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            PlayerHand hand = new PlayerHand(player);

            int tileCount = (i == 0) ? 15 : 14;

            for (int j = 0; j < tileCount; j++) {
                int randomIndex = random.nextInt(tiles.size());
                Tile tile = tiles.remove(randomIndex);
                hand.addTile(tile);
            }

            player.setHand(hand);
            hand.setPlayer(player);
            playerPrint.printTileByPlayer(player, hand);
        }

        return players;
    }
}
