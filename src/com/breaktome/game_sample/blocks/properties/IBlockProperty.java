package com.breaktome.game_sample.blocks.properties;

import com.breaktome.engine.interfaces.IFactory;
import com.breaktome.engine.interfaces.IKeyNameDescibe;
import com.breaktome.engine.interfaces.IOptional;
import com.breaktome.game_sample.blocks.types.IBlockType;

import java.util.ArrayList;

public interface IBlockProperty extends IKeyNameDescibe, IOptional, IFactory<IBlockProperty> {
    default boolean isTypesWhitelisted()
    {
        return false;
    }
    default ArrayList<IBlockType> getWhitelist()
    {
        return new ArrayList<>();
    }
}
