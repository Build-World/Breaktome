package com.breaktome.game.network.client.listeners.messages;

import com.breaktome.BreaktomeClient;
import com.breaktome.game.network.messages.ChunkMessage;
import com.breaktome.game.states.client.SandboxClient;
import com.breaktome.game.world.Chunk;
import com.breaktome.game.world.advanced.AdvancedChunk;
import com.jme3.network.Client;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;

public class ChunkListener implements MessageListener<Client> {

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
}
