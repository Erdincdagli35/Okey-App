package com.edsoft.okey_app.response;

import com.edsoft.okey_app.model.Tile;

public class GameStartInfo {
    private Tile okey;
    private Tile indicator;

    public GameStartInfo(Tile okey, Tile indicator) {
        this.okey = okey;
        this.indicator = indicator;
    }

    public Tile getOkey() {
        return okey;
    }

    public void setOkey(Tile okey) {
        this.okey = okey;
    }

    public Tile getIndicator() {
        return indicator;
    }

    public void setIndicator(Tile indicator) {
        this.indicator = indicator;
    }
}
