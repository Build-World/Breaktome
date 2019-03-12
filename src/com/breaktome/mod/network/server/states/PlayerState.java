package com.breaktome.mod.network.server.states;

import com.breaktome.game.network.server.IConnectionState;

public class PlayerState implements IConnectionState {

    @Override
    public String getName() {
        return "PlayerState";
    }
}
