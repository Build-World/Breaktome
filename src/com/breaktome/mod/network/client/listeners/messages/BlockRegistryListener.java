package com.breaktome.mod.network.client.listeners.messages;

import com.breaktome.BreaktomeClient;
import com.breaktome.game.network.client.IClientMessageListener;
import com.breaktome.mod.network.messages.BlockRegistryMessage;
import com.jme3.network.AbstractMessage;
import com.jme3.network.Client;
import com.jme3.network.Message;

public class BlockRegistryListener implements IClientMessageListener {

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

    @Override
    public Class<? extends AbstractMessage> getMessageType() {
        return BlockRegistryMessage.class;
    }
}
