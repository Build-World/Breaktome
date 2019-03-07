package com.breaktome.game_sample.world.generators;

import com.breaktome.engine.interfaces.IKeyNameDescibe;
import com.breaktome.game_sample.world.interfaces.IGenerate;
import com.breaktome.game_sample.world.maps.types.FillHeightMap;

abstract public class Country implements IGenerate, IKeyNameDescibe {

    public void generate(FillHeightMap fillHeightMap)
    {

    }

    @Override
    public void generate() {

    }
}
