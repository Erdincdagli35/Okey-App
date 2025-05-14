package com.edsoft.okey_app.manager.indicator;

import com.edsoft.okey_app.model.Tile;

import java.util.List;

public interface IndicatorManager {
    IndicatorResult selectIndicator(List<Tile> tiles);
}
