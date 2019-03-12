package com.breaktome.mod.events.events;

import com.breaktome.game.entities.Player;
import com.breaktome.game.events.IEvent;

public class PlayerConnectEvent implements IEvent {

    private Player player;

    public PlayerConnectEvent(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public String getName() {
        return "onPlayerConnect";
    }
}
