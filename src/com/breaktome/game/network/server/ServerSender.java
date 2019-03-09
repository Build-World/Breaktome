package com.breaktome.game.network.server;

import com.jme3.network.Filters;
import com.jme3.network.HostedConnection;
import com.jme3.network.Message;
import com.jme3.network.Server;

import java.util.HashSet;
import java.util.Set;

public class ServerSender {

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

    private Set<HostedConnection> clients;

    public ServerSender(Server server) {
        this.server = server;
        clients = new HashSet<>();
    }

    public ServerSender broadcast(Message message)
    {
        server.broadcast(message);
        return this;
    }

    public ServerSender send(HostedConnection connection, Message message)
    {
        server.broadcast(Filters.in(connection), message);
        return this;
    }

    public ServerSender send(Set<HostedConnection> connections, Message message)
    {
        server.broadcast(Filters.in(connections), message);
        return this;
    }

    public ServerSender broadcastExceptTo(HostedConnection connection, Message message)
    {
        server.broadcast(Filters.notIn(connection), message);
        return this;
    }

    public ServerSender broadcastExceptTo(Set<HostedConnection> connections, Message message)
    {
        server.broadcast(Filters.notIn(connections), message);
        return this;
    }

    /**
     * Set clients and forget
     */


    public ServerSender addClient(HostedConnection connection)
    {
        clients.add(connection);
        return this;
    }

    public ServerSender addClient(Set<HostedConnection> connections)
    {
        clients.addAll(connections);
        return this;
    }

    public ServerSender send(Message message)
    {
        server.broadcast(Filters.in(clients), message);
        return this;
    }

    public ServerSender broadcastExceptTo(Message message)
    {
        server.broadcast(Filters.in(clients), message);
        return this;
    }

}
