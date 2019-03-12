package com.breaktome.mod.network.server.listeners;

import com.breaktome.mod.network.messages.BlockRegistryMessage;
import com.breaktome.mod.network.messages.ChunkMessage;
import com.breaktome.mod.network.messages.PlayersMessage;
import com.breaktome.game.network.server.AdvancedConnection;
import com.breaktome.game.network.server.ServerSender;
import com.breaktome.mod.network.server.states.PlayerState;
import com.breaktome.game.registries.RegistryService;
import com.breaktome.game.world.Chunk;
import com.breaktome.game.world.advanced.AdvancedChunk;
import com.jme3.network.HostedConnection;
import com.jme3.network.Server;

import java.util.HashSet;
import java.util.Set;

public class ConnectionListener implements com.jme3.network.ConnectionListener {

    private RegistryService registries;

    // temp
    private AdvancedChunk advancedChunk;

    public ConnectionListener(RegistryService registries) {
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
