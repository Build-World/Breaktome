package com.breaktome.game.network.client;

import com.breaktome.Breaktome;
import com.breaktome.BreaktomeClient;
import com.breaktome.game.blocks.BlockLoader;
import com.breaktome.game.blocks.BlockRegistry;
import com.breaktome.game.network.INetworkNode;
import com.breaktome.game.network.client.listeners.ClientStateListener;
import com.breaktome.game.network.client.listeners.messages.BlockRegistryListener;
import com.breaktome.game.network.client.listeners.messages.ChunkListener;
import com.breaktome.game.network.client.listeners.messages.PlayersListener;
import com.breaktome.game.network.messages.BlockRegistryMessage;
import com.breaktome.game.network.messages.ChunkMessage;
import com.breaktome.game.network.messages.PlayersMessage;
import com.jme3.network.Client;
import com.jme3.network.Network;

import java.io.IOException;

public class PlayerClient implements INetworkNode {

    private Client client;

    private Breaktome app;

    private String host;
    private int port;

    public PlayerClient(Breaktome app, String host, int port) {
        this.app = app;
        this.host = host;
        this.port = port;
    }

    @Override
    public void start() {
        try {
            client = Network.connectToServer(host, port);
            //client.addErrorListener(new ErrorListener());
            client.addClientStateListener(new ClientStateListener());

            client.addMessageListener(new PlayersListener(), PlayersMessage.class);
            client.addMessageListener(new BlockRegistryListener((BreaktomeClient)app), BlockRegistryMessage.class);
            client.addMessageListener(new ChunkListener((BreaktomeClient)app), ChunkMessage.class);

            client.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        client.close();
    }
}
