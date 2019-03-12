package com.breaktome.mod.network.messages;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

import java.util.Set;

@Serializable
public class PlayersMessage extends AbstractMessage {

    private Set<String> players;

    public PlayersMessage() {
    }

    public PlayersMessage(Set<String> players) {
        this.players = players;
    }

    public Set<String> getPlayers() {
        return players;
    }
}
