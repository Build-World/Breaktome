package com.breaktome.mod.network.messages;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

@Serializable
public class ChunkMessage extends AbstractMessage {

    private int[][][] data;

    public ChunkMessage() {
    }

    public ChunkMessage(int[][][] data) {
        this.data = data;
    }

    public int[][][] getData() {
        return data;
    }
}
