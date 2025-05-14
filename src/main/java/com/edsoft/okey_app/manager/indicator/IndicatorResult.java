package com.edsoft.okey_app.manager.indicator;

import com.edsoft.okey_app.model.Tile;

public class IndicatorResult {
    private final Tile indicator;
    private final Tile okey;

    public IndicatorResult(Tile indicator, Tile okey) {
        this.indicator = indicator;
        this.okey = okey;
    }

    public Tile getOkey() {
        return okey;
    }

    public Tile getIndicator() {
        return indicator;
    }
}
