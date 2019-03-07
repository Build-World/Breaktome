package com.breaktome.game.network.client.listeners.messages;

import com.breaktome.game.network.messages.PlayersMessage;
import com.jme3.network.Client;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;

public class PlayersListener implements MessageListener<Client> {

    @Override
    public void messageReceived(Client client, Message message) {
        if(message instanceof PlayersMessage)
        {
            PlayersMessage playersMessage = (PlayersMessage)message;
            System.out.println("Client " + client.getId() + ": " + playersMessage.getPlayers());
        }
    }
}
