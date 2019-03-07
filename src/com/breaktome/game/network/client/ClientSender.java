package com.breaktome.game.network.client;

import com.jme3.network.Client;
import com.jme3.network.Message;

public class ClientSender {

    private Client client;

    public ClientSender(Client client) {
        this.client = client;
    }

    public void send(Message message)
    {
        client.send(message);
    }
}
