package com.edsoft.okey_app.model;

public class TileMarker {
    private Tile okey;
    private Tile indicator;

    public TileMarker(Tile okey, Tile indicator) {
        this.okey = okey;
        this.indicator = indicator;
    }

    public void setOkey(Tile okey) {
        this.okey = okey;
    }

    public void setIndicator(Tile indicator) {
        this.indicator = indicator;
    }

    public Tile getOkey() {
        return okey;
    }

    public Tile getIndicator() {
        return indicator;
    }
}
