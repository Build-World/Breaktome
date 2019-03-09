package com.breaktome.game.blocks.shapes;

import com.breaktome.game.blocks.Block;
import com.breaktome.game.blocks.mesh.ThreeTextureBox;
import com.jme3.scene.Mesh;

abstract public class CubeBlock extends Block {

    public static Mesh cubeMesh = new ThreeTextureBox(0.5f,0.5f,0.5f);

    @Override
    public Mesh getMesh() {
        return cubeMesh;
    }
}
