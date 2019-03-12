package com.breaktome.mod.network.client.listeners.messages;

import com.breaktome.BreaktomeClient;
import com.breaktome.game.network.client.IClientMessageListener;
import com.breaktome.mod.network.messages.ChunkMessage;
import com.breaktome.mod.states.client.SandboxClient;
import com.breaktome.game.world.Chunk;
import com.breaktome.game.world.advanced.AdvancedChunk;
import com.jme3.network.AbstractMessage;
import com.jme3.network.Client;
import com.jme3.network.Message;

public class ChunkListener implements IClientMessageListener {

    private BreaktomeClient breaktome;

    public ChunkListener(BreaktomeClient breaktome) {
        this.breaktome = breaktome;
    }

    @Override
    public void messageReceived(Client client, Message message) {
        if(message instanceof ChunkMessage)
        {
            ChunkMessage chunkMessage = (ChunkMessage)message;
            Chunk chunk = new Chunk(chunkMessage.getData());
            System.out.println(AdvancedChunk.wrap(chunk).__repr__());

            breaktome.getStateManager().getState(SandboxClient.class).chunk = chunk;
            breaktome.getStateManager().getState(SandboxClient.class).updateNeeded = true;
        }
    }

    @Override
    public Class<? extends AbstractMessage> getMessageType() {
        return ChunkMessage.class;
    }
}
