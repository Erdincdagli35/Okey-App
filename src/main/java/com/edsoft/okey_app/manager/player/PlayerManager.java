package com.edsoft.okey_app.manager.player;

import com.edsoft.okey_app.model.Player;

import java.util.List;

public abstract class PlayerManager {
    public abstract List<Player> generatePlayers(int numberOfPlayers);
}
