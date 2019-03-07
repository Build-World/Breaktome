package com.breaktome.game.network.client;

import com.breaktome.game.network.INetworkNode;
import com.breaktome.game.network.client.listeners.ClientStateListener;
import com.breaktome.game.network.client.listeners.messages.PlayersListener;
import com.breaktome.game.network.messages.PlayersMessage;
import com.jme3.network.Client;
import com.jme3.network.Network;
import com.jme3.network.serializing.Serializer;

import java.io.IOException;

public class PlayerClient implements INetworkNode {

    private Client client;

    private String host;
    private int port;

    public PlayerClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void start() {
        Serializer.registerClass(PlayersMessage.class);

        try {
            client = Network.connectToServer(host, port);
            //client.addErrorListener(new ErrorListener());
            client.addClientStateListener(new ClientStateListener());
            client.addMessageListener(new PlayersListener(), PlayersMessage.class);
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
