package com.breaktome.game.network.server;

import com.jme3.network.AbstractMessage;
import com.jme3.network.HostedConnection;
import com.jme3.network.MessageListener;

public interface IServerMessageListener extends MessageListener<HostedConnection> {

    Class<? extends AbstractMessage> getMessageType();

}
