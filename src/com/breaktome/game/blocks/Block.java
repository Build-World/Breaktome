package com.breaktome.game.blocks;

import com.breaktome.Breaktome;
import com.breaktome.engine.interfaces.IDescribe;
import com.breaktome.engine.interfaces.IFQN;
import com.breaktome.engine.interfaces.IName;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Mesh;

/**
 * Block is a definition of a static block type
 */
abstract public class Block implements IFQN, IDescribe, IName {

    protected Material material;

    /**
     * Lazy loads and returns the mesh
     * @return
     */
    abstract public Mesh getMesh();

    /**
     * Get the texture path
     * @return
     */
    public String getTexturePath()
    {
        return null;
    }

    /**
     * Lazy loads and returns the material
     * @param app
     * @return
     */
    public Material getMaterial(Breaktome app)
    {
        if(material == null)
        {
            material = new Material(app.getAssetManager(), "Common/MatDefs/Light/Lighting.j3md");
            material.setBoolean("UseInstancing",true);
            if(getTexturePath() != null)
            {
                material.setTexture("DiffuseMap", app.getAssetManager().loadTexture(getTexturePath()));
            }
//            material.setBoolean("UseMaterialColors",true); // Lets us define the colors as below
//            material.setColor("Diffuse", ColorRGBA.White);
//            material.setColor("Specular",ColorRGBA.White);
//            material.setFloat("Shininess", 32f);  // [0,128]
        }

        return material;
    }

}
