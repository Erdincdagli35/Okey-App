package com.edsoft.okey_app.service.implementation;

import com.edsoft.okey_app.exception.InvalidGameSetupException;
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
import com.edsoft.okey_app.response.BestPlayerInfo;
import com.edsoft.okey_app.response.GamePrepareInfo;
import com.edsoft.okey_app.response.GameStartInfo;
import com.edsoft.okey_app.service.OkeyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OkeyServiceImpl implements OkeyService {

    @Override
    public GamePrepareInfo getGamePrepareInfo() {
        TileManager tileManager = new TileGenerator();
        List<Tile> tiles = tileManager.generateTiles();

        TilePrint tilePrint = new TilePrint(tileManager);
        tilePrint.printTilesSize(tiles);

        int tileSize = tilePrint.tileSize(tiles);

        PlayerManager playerManager = new PlayerGenerator();
        List<Player> players = playerManager.generatePlayers(4);

        PlayerPrint playerPrint = new PlayerPrint(playerManager, tilePrint);
        playerPrint.printPlayerSize(players);

        int playerSize = playerPrint.playerSize(players);

        if (tileSize != 106 || playerSize != 4) {
            throw new InvalidGameSetupException("Invalid Game Start : \n tileSize=" + tileSize + ", playerSize=" + playerSize);
        }

        return new GamePrepareInfo(tileSize, playerSize);
    }

    @Override
    public GameStartInfo gameStart() {
        TileManager tileManager = new TileGenerator();
        List<Tile> tiles = tileManager.generateTiles();

        TilePrint tilePrint = new TilePrint(tileManager);
        tilePrint.printTilesSize(tiles);

        PlayerManager playerManager = new PlayerGenerator();
        List<Player> players = playerManager.generatePlayers(4);

        PlayerPrint playerPrint = new PlayerPrint(playerManager, tilePrint);
        playerPrint.printPlayerSize(players);

        TileDistributionStrategy distributionStrategy = new RandomTileDistribution(playerPrint);
        players = distributionStrategy.distributeTiles(players, tiles);

        IndicatorManager indicatorManager = new IndicatorSelector();
        IndicatorPrint indicatorPrint = new IndicatorPrint(indicatorManager);
        IndicatorResult indicatorResult = indicatorManager.selectIndicator(tiles);

        indicatorPrint.print(indicatorResult);

        return new GameStartInfo(indicatorResult.getOkey(), indicatorResult.getIndicator());
    }

    @Override
    public Integer getBestPlayer() {
        //1.Taşlar oluşturuluyor
        TileManager tileManager = new TileGenerator();
        List<Tile> tiles = tileManager.generateTiles();

        System.out.println("---------------------------");
        System.out.println("Tiles have been created");

        TilePrint tilePrint = new TilePrint(tileManager);

        System.out.println("---------------------------");
        tilePrint.printTilesSize(tiles);
        System.out.println("---------------------------");

        PlayerManager playerManager = new PlayerGenerator();
        List<Player> players = playerManager.generatePlayers(4);

        System.out.println("---------------------------");
        PlayerPrint playerPrint = new PlayerPrint(playerManager, tilePrint);

        TileDistributionStrategy distributionStrategy = new RandomTileDistribution(playerPrint);
        players = distributionStrategy.distributeTiles(players, tiles);

        IndicatorManager indicatorManager = new IndicatorSelector();
        IndicatorPrint indicatorPrint = new IndicatorPrint(indicatorManager);
        IndicatorResult indicatorResult = indicatorManager.selectIndicator(tiles);

        indicatorPrint.print(indicatorResult);
        System.out.println("---------------------------");

        playerPrint.printPlayerSize(players);

        TileSeriesPlacer tileSeriesFinder = new TileSeriesPlacer(indicatorResult.getOkey(), indicatorResult.getIndicator());
        GameAnalyzer analyzer = new GameAnalyzer(indicatorResult.getOkey(), indicatorResult.getIndicator());

        for (Player player : players) {
            System.out.println("---------------------------------");
            System.out.println("\nPlayer " + player.getId() + "\n");
            tileSeriesFinder.printSeries(tileSeriesFinder.findAllValidSets(player.getHand().getTiles()));
            System.out.println("---------------------------------");
        }

        Integer bestPlayer = analyzer.determineBestPlayer(players);
        System.out.println("Best Player ID : Player " + bestPlayer);

        return bestPlayer;
    }
}
