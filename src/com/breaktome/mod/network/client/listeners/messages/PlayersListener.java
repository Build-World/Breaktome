package com.breaktome.mod.network.client.listeners.messages;

import com.breaktome.game.network.client.IClientMessageListener;
import com.breaktome.mod.network.messages.PlayersMessage;
import com.jme3.network.AbstractMessage;
import com.jme3.network.Client;
import com.jme3.network.Message;

public class PlayersListener implements IClientMessageListener {

    @Override
    public void messageReceived(Client client, Message message) {
        if(message instanceof PlayersMessage)
        {
            PlayersMessage playersMessage = (PlayersMessage)message;
            System.out.println("Client " + client.getId() + ": " + playersMessage.getPlayers());
        }
    }

    @Override
    public Class<? extends AbstractMessage> getMessageType() {
        return PlayersMessage.class;
    }
}
