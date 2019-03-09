package com.breaktome.mod.blocks;

import com.breaktome.game.blocks.Block;

public class BlankBlock extends Block {

    @Override
    public String getDescription() {
        return "A blank block";
    }

    @Override
    public String getKey() {
        return "blank_block";
    }

    @Override
    public String getName() {
        return "Blank Block";
    }

    @Override
    public String getNamespace() {
        return "breaktome";
    }
}
