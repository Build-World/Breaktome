package com.breaktome.game.network.client.listeners.messages;

import com.breaktome.BreaktomeClient;
import com.breaktome.game.network.messages.ChunkMessage;
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

            breaktome.chunk = chunk;
            breaktome.updateNeeded = true;


//            for (int ci = 0; ci < 2; ci++) {
//                for (int cj = 0; cj < 4; cj++) {
//                    for (int ck = 0; ck < 2; ck++) {
//
//
//
//                        breaktome.addChunkToScene(ci,cj,ck,chunk);
//
////
////                        for (int i = 0; i < Chunk.size; i++) {
////                            for (int j = 0; j < Chunk.size; j++) {
////                                for (int k = 0; k < Chunk.size; k++) {
////                                    if (!chunk.isAir(i,j,k)) {
////                                        breaktome.addBlockToScene(i + ci*Chunk.size, j + cj*Chunk.size, k + ck*Chunk.size, chunk.getBlock(i,j,k));
////                                    }
////                                }
////                            }
////                        }
//
//
//
//
//                    }
//                }
//            }


//            breaktome.getInstancedNode().instance();
        }
    }
}
