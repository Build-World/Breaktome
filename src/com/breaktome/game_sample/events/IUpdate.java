package com.breaktome.game_sample.events;

public interface IUpdate extends IUpdateable {
    void update() throws Exception;
    boolean requiresUpdates() throws Exception;
}
