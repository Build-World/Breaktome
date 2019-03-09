package com.breaktome.mod.blocks;

import com.breaktome.game.blocks.shapes.CubeBlock;

public class GrassBlock extends CubeBlock {

    @Override
    public String getDescription() {
        return "A grass block";
    }

    @Override
    public String getKey() {
        return "grass_block";
    }

    @Override
    public String getName() {
        return "Grass Block";
    }

    @Override
    public String getNamespace() {
        return "breaktome";
    }

    @Override
    public String getTexturePath() {
        return "grass.png";
    }
}
