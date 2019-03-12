package com.breaktome.game.network.server;

import com.jme3.network.HostedConnection;
import com.jme3.network.Server;

public class AdvancedConnection {

    public static AdvancedConnection wrap(Server server, HostedConnection connection)
    {
        return new AdvancedConnection(server, connection);
    }

    private HostedConnection connection;
    private ServerSender sender;

    public AdvancedConnection(Server server, HostedConnection connection) {
        this.connection = connection;
        this.sender = new ServerSender(server);
        this.sender.addClient(connection);
    }

    public HostedConnection getConnection() {
        return connection;
    }

    public ServerSender getSender() {
        return sender;
    }

    public AdvancedConnection setState(IConnectionState state)
    {
        connection.setAttribute(state.getName(), state);
        return this;
    }

    public IConnectionState getState(IConnectionState state)
    {
        return connection.getAttribute(state.getName());
    }

    public IConnectionState getState(String stateName)
    {
        return connection.getAttribute(stateName);
    }
}
