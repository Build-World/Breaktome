package com.breaktome.game.network.server;

import com.breaktome.Breaktome;
import com.breaktome.game.network.INetworkNode;
import com.jme3.network.Network;
import com.jme3.network.Server;

import java.io.IOException;

public class GameServer implements INetworkNode {

    private Server server;

    private int port;

    public GameServer(int port) {
        this.port = port;
        try {
            server = Network.createServer(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Server getServer() {
        return server;
    }

    public int getPort() {
        return port;
    }

    public void start() {
        server.start();
    }

    @Override
    public void destroy() {
        server.close();
    }
}
