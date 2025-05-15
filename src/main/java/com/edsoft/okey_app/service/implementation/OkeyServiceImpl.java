package com.edsoft.okey_app.service.implementation;

import com.edsoft.okey_app.exception.BestPlayerCalculationException;
import com.edsoft.okey_app.exception.GamePreparationException;
import com.edsoft.okey_app.exception.GameStartException;
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
import com.edsoft.okey_app.response.GamePrepareInfo;
import com.edsoft.okey_app.response.GameStartInfo;
import com.edsoft.okey_app.service.OkeyService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OkeyServiceImpl implements OkeyService {

    private static final Logger logger = LoggerFactory.getLogger(OkeyServiceImpl.class);

    @Override
    public GamePrepareInfo getGamePrepareInfo() {
        try {
            logger.info("Game preparation started.");

            TileManager tileManager = new TileGenerator();
            List<Tile> tiles = tileManager.generateTiles();
            logger.info("Tiles generated. Total tiles: {}", tiles.size());

            TilePrint tilePrint = new TilePrint(tileManager);
            tilePrint.printTilesSize(tiles);
            int tileSize = tilePrint.tileSize(tiles);

            PlayerManager playerManager = new PlayerGenerator();
            List<Player> players = playerManager.generatePlayers(4);
            logger.info("Players generated. Total players: {}", players.size());

            PlayerPrint playerPrint = new PlayerPrint(playerManager, tilePrint);
            playerPrint.printPlayerSize(players);
            int playerSize = playerPrint.playerSize(players);

            if (tileSize != 106 || playerSize != 4) {
                logger.warn("Invalid game setup. tileSize={}, playerSize={}", tileSize, playerSize);
                throw new InvalidGameSetupException("Invalid Game Start: tileSize=" + tileSize + ", playerSize=" + playerSize);
            }

            logger.info("Game preparation completed successfully.");
            return new GamePrepareInfo(tileSize, playerSize);
        } catch (InvalidGameSetupException e) {
            logger.error("Game preparation failed due to invalid setup.", e);
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error during game preparation.", e);
            throw new GamePreparationException("An error occurred while preparing the game", e);
        }
    }

    @Override
    public GameStartInfo gameStart() {
        try {
            logger.info("Game start process initiated.");

            TileManager tileManager = new TileGenerator();
            List<Tile> tiles = tileManager.generateTiles();
            logger.info("Tiles generated. Total tiles: {}", tiles.size());

            TilePrint tilePrint = new TilePrint(tileManager);
            tilePrint.printTilesSize(tiles);

            PlayerManager playerManager = new PlayerGenerator();
            List<Player> players = playerManager.generatePlayers(4);
            logger.info("Players generated. Total players: {}", players.size());

            PlayerPrint playerPrint = new PlayerPrint(playerManager, tilePrint);
            playerPrint.printPlayerSize(players);

            TileDistributionStrategy distributionStrategy = new RandomTileDistribution(playerPrint);
            players = distributionStrategy.distributeTiles(players, tiles);
            logger.info("Tiles distributed to players.");

            IndicatorManager indicatorManager = new IndicatorSelector();
            IndicatorPrint indicatorPrint = new IndicatorPrint(indicatorManager);
            IndicatorResult indicatorResult = indicatorManager.selectIndicator(tiles);
            indicatorPrint.print(indicatorResult);

            logger.info("Indicator selected. Okey: {}, Indicator: {}", indicatorResult.getOkey(), indicatorResult.getIndicator());

            logger.info("Game start completed successfully.");
            return new GameStartInfo(indicatorResult.getOkey(), indicatorResult.getIndicator());
        } catch (Exception e) {
            logger.error("Unexpected error during game start.", e);
            throw new GameStartException("An error occurred while starting the game", e);
        }
    }

    @Override
    public Integer getBestPlayer() {
        try {
            logger.info("Best player calculation started.");

            TileManager tileManager = new TileGenerator();
            List<Tile> tiles = tileManager.generateTiles();
            logger.info("Tiles generated. Total tiles: {}", tiles.size());

            TilePrint tilePrint = new TilePrint(tileManager);
            tilePrint.printTilesSize(tiles);

            PlayerManager playerManager = new PlayerGenerator();
            List<Player> players = playerManager.generatePlayers(4);
            logger.info("Players generated. Total players: {}", players.size());

            PlayerPrint playerPrint = new PlayerPrint(playerManager, tilePrint);
            TileDistributionStrategy distributionStrategy = new RandomTileDistribution(playerPrint);
            players = distributionStrategy.distributeTiles(players, tiles);
            logger.info("Tiles distributed to players.");

            IndicatorManager indicatorManager = new IndicatorSelector();
            IndicatorPrint indicatorPrint = new IndicatorPrint(indicatorManager);
            IndicatorResult indicatorResult = indicatorManager.selectIndicator(tiles);
            indicatorPrint.print(indicatorResult);

            logger.info("Indicator selected. Okey: {}, Indicator: {}", indicatorResult.getOkey(), indicatorResult.getIndicator());

            playerPrint.printPlayerSize(players);

            TileSeriesPlacer tileSeriesFinder = new TileSeriesPlacer(indicatorResult.getOkey(), indicatorResult.getIndicator());
            GameAnalyzer analyzer = new GameAnalyzer(indicatorResult.getOkey(), indicatorResult.getIndicator());

            for (Player player : players) {
                logger.info("---------------------------------");
                logger.info("Analyzing Player {}", player.getId());
                tileSeriesFinder.printSeries(tileSeriesFinder.findAllValidSets(player.getHand().getTiles()));
                logger.info("---------------------------------");
            }

            Integer bestPlayer = analyzer.determineBestPlayer(players);
            logger.info("Best Player ID : Player {}", bestPlayer);

            logger.info("Best player calculation completed.");
            return bestPlayer;
        } catch (Exception e) {
            logger.error("Unexpected error during best player calculation.", e);
            throw new BestPlayerCalculationException("An error occurred while calculating the best player", e);
        }
    }
}
