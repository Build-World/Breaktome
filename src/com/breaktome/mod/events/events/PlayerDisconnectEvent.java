package com.breaktome.mod.events.events;

import com.breaktome.game.entities.Player;
import com.breaktome.game.events.IEvent;

public class PlayerDisconnectEvent implements IEvent {

    private Player player;

    public PlayerDisconnectEvent(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public String getName() {
        return "onPlayerDisconnect";
    }
}
