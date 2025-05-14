package com.edsoft.okey_app.model;

public class Player {
    private final int id;
    private PlayerHand hand;

    public Player(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setHand(PlayerHand hand) {
        this.hand = hand;
    }

    public PlayerHand getHand() {
        return hand;
    }
}
