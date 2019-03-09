package com.breaktome.game.network.client.listeners.messages;

import com.breaktome.game.network.messages.BlockRegistryMessage;
import com.breaktome.game.registries.Registries;
import com.jme3.network.Client;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;

public class BlockRegistryListener implements MessageListener<Client> {

    private Registries registries;

    public BlockRegistryListener(Registries registries) {
        this.registries = registries;
    }

    @Override
    public void messageReceived(Client client, Message message) {
        if(message instanceof BlockRegistryMessage)
        {
            BlockRegistryMessage blockRegistryMessage = (BlockRegistryMessage)message;
            registries.getBlockLoader().setBlockRegistry(blockRegistryMessage.getBlockRegistry());
            System.out.println(registries.getBlockLoader().getBlockRegistry().__repr__());
        }
    }
}
