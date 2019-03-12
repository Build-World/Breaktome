package com.breaktome.game.network.client.listeners.messages;

import com.breaktome.BreaktomeClient;
import com.breaktome.game.network.messages.BlockRegistryMessage;
import com.jme3.network.Client;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;

public class BlockRegistryListener implements MessageListener<Client> {

    private BreaktomeClient breaktome;

    public BlockRegistryListener(BreaktomeClient breaktome) {
        this.breaktome = breaktome;
    }

    @Override
    public void messageReceived(Client client, Message message) {
        if(message instanceof BlockRegistryMessage)
        {
            BlockRegistryMessage blockRegistryMessage = (BlockRegistryMessage)message;
            breaktome.getGlobalState().getRegistries().getBlockLoader().setBlockRegistry(blockRegistryMessage.getBlockRegistry());
            System.out.println(breaktome.getGlobalState().getRegistries().getBlockLoader().getBlockRegistry().__repr__());
        }
    }
}
