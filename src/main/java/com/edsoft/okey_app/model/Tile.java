package com.edsoft.okey_app.model;

import java.util.Objects;

public class Tile {
    private Color color;
    private final int number; //1-13 between (fake okey should be 0)
    private final boolean isFake;
    private boolean isOkey;

    public Tile(Color color, int number, boolean isFake) {
        this.color = color;
        this.number = number;
        this.isFake = isFake;
    }

    public Color getColor() {
        return color;
    }

    public int getNumber() {
        return number;
    }

    public boolean isFake() {
        return isFake;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isOkey() {
        return isOkey;
    }

    @Override
    public String toString() {
        return this.color + " -> " + this.number;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return number == tile.number && isFake == tile.isFake && color == tile.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, number, isFake);
    }
}
