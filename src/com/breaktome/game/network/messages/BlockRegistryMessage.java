package com.breaktome.game.network.messages;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

@Serializable
public class BlockRegistryMessage extends AbstractMessage {

    private String[] blockRegistry;

    public BlockRegistryMessage() {
    }

    public BlockRegistryMessage(String[] blockRegistry) {
        this.blockRegistry = blockRegistry;
    }

    public String[] getBlockRegistry() {
        return blockRegistry;
    }
}
