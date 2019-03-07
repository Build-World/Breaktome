package com.breaktome.game.network.server.listeners;

import com.breaktome.game.network.messages.PlayersMessage;
import com.breaktome.game.network.server.Sender;
import com.jme3.network.HostedConnection;
import com.jme3.network.Server;

import java.util.HashSet;
import java.util.Set;

public class ConnectionListener implements com.jme3.network.ConnectionListener {

    @Override
    public void connectionAdded(Server server, HostedConnection hostedConnection) {
        System.out.println("New Connection");
        Set<String> connections = new HashSet<>();
        for(HostedConnection connection : server.getConnections())
        {
            connections.add(connection.getAddress());
        }
        Sender.broadcast(server, new PlayersMessage(connections));
    }

    @Override
    public void connectionRemoved(Server server, HostedConnection hostedConnection) {
        System.out.println("Lost Connection");
        Set<String> connections = new HashSet<>();
        for(HostedConnection connection : server.getConnections())
        {
            connections.add(connection.getAddress());
        }
        Sender.broadcast(server, new PlayersMessage(connections));
    }
}
