package com.edsoft.okey_app.manager.player;

import com.edsoft.okey_app.model.Player;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PlayerGenerator extends PlayerManager {
    /**
     * Generates a list of players with sequential IDs starting from 1.
     *
     * @param numberOfPlayers the total number of players to generate
     * @return a list of Player objects with assigned IDs
     */
    @Override
    public List<Player> generatePlayers(int numberOfPlayers) {
        return IntStream.rangeClosed(1, numberOfPlayers)
                .mapToObj(Player::new)
                .collect(Collectors.toList());
    }
}
