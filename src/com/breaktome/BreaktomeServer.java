package com.breaktome;

import com.breaktome.game.network.server.GameServer;
import com.breaktome.game.network.server.IServerMessageListener;
import com.breaktome.mod.states.server.SandboxServer;
import com.jme3.network.ConnectionListener;
import com.jme3.network.HostedConnection;
import com.jme3.network.MessageListener;

import java.util.HashSet;
import java.util.Set;

public class BreaktomeServer extends Breaktome {

    private GameServer gameServer;

    private SandboxServer sandboxServer;

    public BreaktomeServer() {
        setName("Breaktome Server");
    }

    @Override
    boolean isServer() {
        return true;
    }

    @Override
    public void onBoot() throws Exception {
        gameServer = new GameServer(4242);

        // Adding our game states and game modes
        sandboxServer = new SandboxServer();
        registerAppState(sandboxServer);

        // Adds core connection listeners
        for(ConnectionListener connectionListener : registerConnectionListeners())
        {
            gameServer.getServer().addConnectionListener(connectionListener);
        }

        // Adds mod connection listeners
        for(ConnectionListener connectionListener : modLoader.registerConnectionListeners())
        {
            gameServer.getServer().addConnectionListener(connectionListener);
        }

        // Adds core message listeners
        for(IServerMessageListener messageListener : registerServerMessageListeners())
        {
            gameServer.getServer().addMessageListener(messageListener, messageListener.getMessageType());
        }

        // Adds mod message listeners
        for(IServerMessageListener messageListener : modLoader.registerServerMessageListeners())
        {
            gameServer.getServer().addMessageListener(messageListener, messageListener.getMessageType());
        }

    }


    @Override
    public void onServerLoad() throws Exception {
        super.onServerLoad();
    }

    @Override
    public void onServerReady() throws Exception {
        // Turn on the initial game mode
        sandboxServer.setEnabled(true);
    }

    @Override
    public void onServerPlay() throws Exception {
        gameServer.start();
    }

    @Override
    public void onServerUpdate(float tpf) throws Exception {
        super.onServerUpdate(tpf);
    }

    @Override
    public Set<ConnectionListener> registerConnectionListeners() {
        Set<ConnectionListener> connectionListeners = new HashSet<>();
        connectionListeners.add(new com.breaktome.mod.network.server.listeners.ConnectionListener(getGlobalState().getRegistries()));
        return connectionListeners;
    }

    @Override
    public Set<IServerMessageListener> registerServerMessageListeners() {
        return new HashSet<>();
    }

    @Override
    public void destroy() {
        gameServer.destroy();
        super.destroy();
    }
}
