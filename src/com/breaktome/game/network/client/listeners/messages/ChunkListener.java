package com.breaktome.game.network.client.listeners.messages;

import com.breaktome.game.network.messages.ChunkMessage;
import com.breaktome.game.world.Chunk;
import com.breaktome.game.world.advanced.AdvancedChunk;
import com.jme3.network.Client;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;

public class ChunkListener implements MessageListener<Client> {

    @Override
    public void messageReceived(Client client, Message message) {
        if(message instanceof ChunkMessage)
        {
            ChunkMessage chunkMessage = (ChunkMessage)message;
            System.out.println(AdvancedChunk.wrap(new Chunk(chunkMessage.getData())).__repr__());
        }
    }
}
