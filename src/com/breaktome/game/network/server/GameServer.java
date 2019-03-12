package com.breaktome.game.network.server;

import com.breaktome.Breaktome;
import com.breaktome.game.network.INetworkNode;
import com.breaktome.game.network.server.listeners.ConnectionListener;
import com.jme3.network.Network;
import com.jme3.network.Server;

import java.io.IOException;

public class GameServer implements INetworkNode {

    private Server server;

    private Breaktome app;

    private int port;

    public GameServer(Breaktome app, int port) {
        this.app = app;
        this.port = port;
    }

    public Breaktome getApp() {
        return app;
    }

    public Server getServer() {
        return server;
    }

    public int getPort() {
        return port;
    }

    public void start() {
        try {
            server = Network.createServer(port);
            server.addConnectionListener(new ConnectionListener(app.getGlobalState().getRegistries()));
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        server.close();
    }
}
