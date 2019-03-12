package com.breaktome.game.network.client;

import com.jme3.network.AbstractMessage;
import com.jme3.network.Client;
import com.jme3.network.MessageListener;

public interface IClientMessageListener extends MessageListener<Client> {

    Class<? extends AbstractMessage> getMessageType();

}
