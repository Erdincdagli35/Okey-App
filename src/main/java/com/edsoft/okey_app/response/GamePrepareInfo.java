package com.edsoft.okey_app.response;

public class GamePrepareInfo {
    private Integer tileSize;
    private Integer playerSize;

    public GamePrepareInfo(Integer tileSize, Integer playerSize) {
        this.tileSize = tileSize;
        this.playerSize = playerSize;
    }

    public Integer getTileSize() {
        return tileSize;
    }

    public void setTileSize(Integer tileSize) {
        this.tileSize = tileSize;
    }

    public Integer getPlayerSize() {
        return playerSize;
    }

    public void setPlayerSize(Integer playerSize) {
        this.playerSize = playerSize;
    }
}
