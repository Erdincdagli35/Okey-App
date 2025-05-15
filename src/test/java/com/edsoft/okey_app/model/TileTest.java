package com.edsoft.okey_app.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TileTest {

    @Test
    void testTileInitialization() {
        Tile tile = new Tile(Color.SIYAH, 10, false);

        assertEquals(Color.SIYAH, tile.getColor());
        assertEquals(10, tile.getNumber());
        assertFalse(tile.isFake());
        assertFalse(tile.isOkey()); // default
    }

    @Test
    void testSetColor() {
        Tile tile = new Tile(Color.MAVI, 8, false);
        tile.setColor(Color.KIRMIZI);

        assertEquals(Color.KIRMIZI, tile.getColor());
    }

    @Test
    void testToString() {
        Tile tile = new Tile(Color.MAVI, 5, false);
        assertEquals("MAVI -> 5", tile.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        Tile t1 = new Tile(Color.SARI, 3, false);
        Tile t2 = new Tile(Color.SARI, 3, false);
        Tile t3 = new Tile(Color.SARI, 3, true);

        assertEquals(t1, t2);
        assertEquals(t1.hashCode(), t2.hashCode());
        assertNotEquals(t1, t3);
    }
}
