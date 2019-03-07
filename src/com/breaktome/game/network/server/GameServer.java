package com.breaktome.game.network.server;

import com.breaktome.game.network.INetworkNode;
import com.breaktome.game.network.messages.PlayersMessage;
import com.breaktome.game.network.server.listeners.ConnectionListener;
import com.jme3.network.Network;
import com.jme3.network.Server;
import com.jme3.network.serializing.Serializer;

import java.io.IOException;

public class GameServer implements INetworkNode {

    private Server server;

    private int port;

    public GameServer(int port) {
        this.port = port;
    }

    public Server getServer() {
        return server;
    }

    public int getPort() {
        return port;
    }

    public void start() {
        Serializer.registerClass(PlayersMessage.class);

        try {
            server = Network.createServer(port);
            server.addConnectionListener(new ConnectionListener());
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
