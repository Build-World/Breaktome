package com.breaktome.mod.blocks;

import com.breaktome.Breaktome;
import com.breaktome.game.blocks.shapes.CubeBlock;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;

public class BlankBlock extends CubeBlock {

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

    @Override
    public Material getMaterial(Breaktome app)
    {
        if(material == null)
        {
            material = new Material(app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
            material.setBoolean("UseInstancing",true);
            this.material.setColor("Color", ColorRGBA.Magenta);
        }

        return material;
    }
}
