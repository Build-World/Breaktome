package com.breaktome.game_sample.events;

import com.breaktome.game_sample.blocks.Block;

public interface IUpdateable {
    void update(Block updater) throws Exception;
}
