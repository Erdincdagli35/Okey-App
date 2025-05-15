package com.edsoft.okey_app.model;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PlayHandTest {

    @Test
    void testAddSingleTile() {
        Player player = new Player(1);
        PlayerHand hand = new PlayerHand(player);
        Tile tile = new Tile(Color.MAVI, 5, false);

        hand.addTile(tile);

        assertEquals(1, hand.getTiles().size());
        assertTrue(hand.getTiles().contains(tile));
    }

    @Test
    void testAddMultipleTiles() {
        Player player = new Player(1);
        PlayerHand hand = new PlayerHand(player);
        List<Tile> tiles = List.of(
                new Tile(Color.SARI, 2, false),
                new Tile(Color.KIRMIZI, 3, false)
        );

        hand.addTiles(tiles);

        assertEquals(2, hand.getTiles().size());
        assertTrue(hand.getTiles().containsAll(tiles));
    }

    @Test
    void testSetAndGetPlayer() {
        Player player = new Player(1);
        PlayerHand hand = new PlayerHand(null);

        hand.setPlayer(player);
        assertEquals(player, hand.getPlayer());
    }
}
