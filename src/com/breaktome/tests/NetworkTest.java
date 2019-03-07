package com.breaktome.tests;

import com.breaktome.BreaktomeClient;
import com.breaktome.BreaktomeServer;
import com.jme3.system.JmeContext;

import java.util.HashSet;
import java.util.Set;

public class NetworkTest {

    public static int clientCount = 1;

    public static void main(String[] args)
    {
        BreaktomeServer breaktomeServer = new BreaktomeServer();
        breaktomeServer.start(JmeContext.Type.Headless);

        Set<BreaktomeClient> clients = new HashSet<>();
        for(int i = 0; i < clientCount; i++)
        {
            BreaktomeClient breaktomeClient = new BreaktomeClient();
            breaktomeClient.start();

            clients.add(breaktomeClient);
        }

    }

}
