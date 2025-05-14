package com.edsoft.okey_app.manager.analyzer;

import com.edsoft.okey_app.manager.placer.TileSeriesPlacer;
import com.edsoft.okey_app.model.Player;
import com.edsoft.okey_app.model.Tile;

import java.util.List;
import java.util.Map;

public class GameAnalyzer {
    private final TileSeriesPlacer finder;

    public GameAnalyzer(Tile okeyTile, Tile indicatorTile) {
        this.finder = new TileSeriesPlacer(okeyTile, indicatorTile);
    }

    /**
     * Determines the best player based on their hand of tiles and calculates the highest score.
     * The score is calculated based on the number of sets (series, groups, pairs) a player has in their hand.
     * The player with the highest score is determined and returned.
     *
     * @param players the list of players to evaluate
     * @return the ID of the player with the highest score
     */
    public Integer determineBestPlayer(List<Player> players) {
        Integer bestPlayer = null;
        int bestScore = -1;

        for (Player player : players) {
            List<Tile> tiles = player.getHand().getTiles();

            Map<String, Integer> counts = finder.countSetTypes(tiles);
            int score = calculateScore(counts);

            System.out.println("Player: " + player.getId() + ", Series: " + counts.get("series") +
                    ", Groups: " + counts.get("group") +
                    ", Pairs: " + counts.get("pair") +
                    ", Score: " + score);

            if (score > bestScore) {
                bestScore = score;
                bestPlayer = player.getId();
            }
        }

        return bestPlayer;
    }

    /**
     * Calculates the score based on the counts of series, groups, and pairs.
     * The score is calculated as follows:
     * - Each series is worth 5 points
     * - Each group is worth 3 points
     * - Each pair is worth 1 point
     *
     * @param counts a map containing the counts of series, groups, and pairs
     * @return the total score calculated based on the counts of sets
     */
    private int calculateScore(Map<String, Integer> counts) {
        return counts.getOrDefault("series", 0) * 5 +
                counts.getOrDefault("group", 0) * 3 +
                counts.getOrDefault("pair", 0) * 1;
    }
}
