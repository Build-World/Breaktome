package com.breaktome.game.network.server.listeners;

import com.breaktome.game.network.messages.BlockRegistryMessage;
import com.breaktome.game.network.messages.ChunkMessage;
import com.breaktome.game.network.messages.PlayersMessage;
import com.breaktome.game.network.server.AdvancedConnection;
import com.breaktome.game.network.server.ServerSender;
import com.breaktome.game.network.server.states.PlayerState;
import com.breaktome.game.registries.Registries;
import com.breaktome.game.world.Chunk;
import com.breaktome.game.world.advanced.AdvancedChunk;
import com.jme3.network.HostedConnection;
import com.jme3.network.Server;

import java.util.HashSet;
import java.util.Set;

public class ConnectionListener implements com.jme3.network.ConnectionListener {

    private Registries registries;

    // temp
    private AdvancedChunk advancedChunk;

    public ConnectionListener(Registries registries) {
        this.registries = registries;
    }

    @Override
    public void connectionAdded(Server server, HostedConnection hostedConnection) {


        // temp
        if(advancedChunk == null)
        {
            System.out.println("Random Chunk Generated");
            advancedChunk = AdvancedChunk.wrap(new Chunk());
            advancedChunk.randomize(registries.getBlockLoader().getBlockRegistry());
        }

        PlayerState playerState = new PlayerState();
        AdvancedConnection.wrap(server, hostedConnection)
                .setState(playerState)
                .getSender()
                .send(new BlockRegistryMessage(registries.getBlockLoader().getBlockRegistry().exportOrderedNames()))
                // temp
                .send(new ChunkMessage(advancedChunk.getChunk().getData()));

        System.out.println("New Connection");
        ServerSender.broadcast(server, new PlayersMessage(getPlayerList(server)));
    }

    @Override
    public void connectionRemoved(Server server, HostedConnection hostedConnection) {
        System.out.println("Lost Connection");
        ServerSender.broadcast(server, new PlayersMessage(getPlayerList(server)));
    }

    private Set<String> getPlayerList(Server server)
    {
        Set<String> connections = new HashSet<>();
        for(HostedConnection connection : server.getConnections())
        {
            connections.add(connection.getAddress());
        }
        return connections;
    }
}
