package com.breaktome.game.network.server;

import com.jme3.network.Filters;
import com.jme3.network.HostedConnection;
import com.jme3.network.Message;
import com.jme3.network.Server;

import java.util.Set;

public class Sender {

    /**
     * Static Aliases
     */
    public static void broadcast(Server server, Message message)
    {
        server.broadcast(message);
    }

    public static void send(Server server, HostedConnection connection, Message message)
    {
        server.broadcast(Filters.in(connection), message);
    }

    public static void send(Server server, Set<HostedConnection> connections, Message message)
    {
        server.broadcast(Filters.in(connections), message);
    }

    public static void broadcastExceptTo(Server server, HostedConnection connection, Message message)
    {
        server.broadcast(Filters.notIn(connection), message);
    }

    public static void broadcastExceptTo(Server server, Set<HostedConnection> connections, Message message)
    {
        server.broadcast(Filters.notIn(connections), message);
    }

    /**
     * Set server and forget
     */
    private Server server;

    public Sender(Server server) {
        this.server = server;
    }

    public Sender broadcast(Message message)
    {
        server.broadcast(message);
        return this;
    }

    public Sender send(HostedConnection connection, Message message)
    {
        server.broadcast(Filters.in(connection), message);
        return this;
    }

    public Sender send(Set<HostedConnection> connections, Message message)
    {
        server.broadcast(Filters.in(connections), message);
        return this;
    }

    public Sender broadcastExceptTo(HostedConnection connection, Message message)
    {
        server.broadcast(Filters.notIn(connection), message);
        return this;
    }

    public Sender broadcastExceptTo(Set<HostedConnection> connections, Message message)
    {
        server.broadcast(Filters.notIn(connections), message);
        return this;
    }

    /**
     * Set clients and forget
     */
    private Set<HostedConnection> clients;

    public Sender addClient(HostedConnection connection)
    {
        clients.add(connection);
        return this;
    }

    public Sender addClient(Set<HostedConnection> connections)
    {
        clients.addAll(connections);
        return this;
    }

    public Sender send(Message message)
    {
        server.broadcast(Filters.in(clients), message);
        return this;
    }

    public Sender broadcastExceptTo(Message message)
    {
        server.broadcast(Filters.in(clients), message);
        return this;
    }

}
