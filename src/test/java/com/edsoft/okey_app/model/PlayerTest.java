package com.edsoft.okey_app.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    void testPlayerInitialization() {
        Player player = new Player(1);
        assertEquals(1, player.getId());
        assertNull(player.getHand());
    }

    @Test
    void testSetAndGetHand() {
        Player player = new Player(2);
        PlayerHand hand = new PlayerHand(player);
        player.setHand(hand);

        assertEquals(hand, player.getHand());
        assertEquals(player, hand.getPlayer());
    }
}
