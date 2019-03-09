package com.breaktome.game.registries;

import com.breaktome.game.blocks.BlockLoader;

public class Registries {

    private BlockLoader blockLoader;

    public Registries() {

        blockLoader = new BlockLoader();

    }

    public BlockLoader getBlockLoader() {
        return blockLoader;
    }
}
