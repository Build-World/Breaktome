package com.breaktome.tests;

import com.breaktome.BreaktomeServer;
import com.jme3.system.JmeContext;

public class ServerTest {
    public static void main(String[] args)
    {
        BreaktomeServer breaktomeServer = new BreaktomeServer();
        breaktomeServer.start(JmeContext.Type.Headless);
    }
}
