package com.edsoft.okey_app.manager;

import com.edsoft.okey_app.manager.analyzer.GameAnalyzer;
import com.edsoft.okey_app.manager.distribution.RandomTileDistribution;
import com.edsoft.okey_app.manager.distribution.TileDistributionStrategy;
import com.edsoft.okey_app.manager.generator.TileGenerator;
import com.edsoft.okey_app.manager.generator.TileManager;
import com.edsoft.okey_app.manager.generator.TilePrint;
import com.edsoft.okey_app.manager.indicator.IndicatorManager;
import com.edsoft.okey_app.manager.indicator.IndicatorPrint;
import com.edsoft.okey_app.manager.indicator.IndicatorResult;
import com.edsoft.okey_app.manager.indicator.IndicatorSelector;
import com.edsoft.okey_app.manager.placer.TileSeriesPlacer;
import com.edsoft.okey_app.manager.player.PlayerGenerator;
import com.edsoft.okey_app.manager.player.PlayerManager;
import com.edsoft.okey_app.manager.player.PlayerPrint;
import com.edsoft.okey_app.model.Player;
import com.edsoft.okey_app.model.Tile;

import java.util.List;

public class GameManager {

    public void initializeGame() {
        System.out.println();

        //1.Taşlar oluşturuluyor
        TileManager tileManager = new TileGenerator();
        List<Tile> tiles = tileManager.generateTiles();

        System.out.println("---------------------------");
        System.out.println("Tiles have been created");

        TilePrint tilePrint = new TilePrint(tileManager);

        System.out.println("---------------------------");
        tilePrint.printTilesSize(tiles);
        System.out.println("---------------------------");

        //1.Oyuncular oluşturuluyor
        PlayerManager playerManager = new PlayerGenerator();
        List<Player> players = playerManager.generatePlayers(4);

        System.out.println("---------------------------");
        PlayerPrint playerPrint = new PlayerPrint(playerManager, tilePrint);

        //2.Taşlar karıştırılıp oyuncuların ıstakasına ekleniyor
        TileDistributionStrategy distributionStrategy = new RandomTileDistribution(playerPrint);
        players = distributionStrategy.distributeTiles(players, tiles);

        IndicatorManager indicatorManager = new IndicatorSelector();
        IndicatorPrint indicatorPrint = new IndicatorPrint(indicatorManager);
        IndicatorResult indicatorResult = indicatorManager.selectIndicator(tiles);

        indicatorPrint.print(indicatorResult);
        System.out.println("---------------------------");

        playerPrint.printPlayerSize(players);

        //3.Taşlar diziliyor
        TileSeriesPlacer tileSeriesFinder = new TileSeriesPlacer(indicatorResult.getOkey(), indicatorResult.getIndicator());
        GameAnalyzer analyzer = new GameAnalyzer(indicatorResult.getOkey(), indicatorResult.getIndicator());

        for (Player player : players) {
            System.out.println("---------------------------------");
            System.out.println("\nPlayer " + player.getId() + "\n");
            tileSeriesFinder.printSeries(tileSeriesFinder.findAllValidSets(player.getHand().getTiles()));
            System.out.println("---------------------------------");
        }

        //En iyi oyuncu bulunuyor
        Integer bestPlayer = analyzer.determineBestPlayer(players);
        System.out.println("Best Player ID : Player " + bestPlayer);
    }
}
