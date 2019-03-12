package com.breaktome.game.network.client;

import com.breaktome.game.network.INetworkNode;
import com.jme3.network.Client;
import com.jme3.network.Network;

import java.io.IOException;

public class PlayerClient implements INetworkNode {

    private Client client;

    private String host;
    private int port;

    public PlayerClient(String host, int port) {
        this.host = host;
        this.port = port;

        try {
            client = Network.connectToServer(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Client getClient() {
        return client;
    }

    @Override
    public void start() {
        client.start();
    }

    @Override
    public void destroy() {
        client.close();
    }
}
