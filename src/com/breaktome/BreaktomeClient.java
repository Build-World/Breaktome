package com.breaktome;

import com.breaktome.game.blocks.Block;
import com.breaktome.game.network.client.PlayerClient;
import com.breaktome.game.world.Chunk;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.ssao.SSAOFilter;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.instancing.InstancedNode;
import com.jme3.shadow.DirectionalLightShadowFilter;
import com.jme3.shadow.DirectionalLightShadowRenderer;
import com.jme3.shadow.EdgeFilteringMode;

/**
 * Gameplay client featuring single player and multiplayer functionality
 * Singleplayer:
 *   - Incorporates the BreaktomeServer, and launches it
 *   - Client connects to the BreaktomeServer
 * Multiplayer:
 *   - Client connects to a dedicated instance of a BreaktomeServer
 */
public class BreaktomeClient extends Breaktome {

    private PlayerClient playerClient;
    private InstancedNode instancedNode;

    //temp
    public Chunk chunk;
    public boolean updateNeeded = false;

    @Override
    boolean isServer() {
        return false;
    }

    @Override
    public void onBoot() throws Exception {
        registerAssetLocator(assetsPath);

        instancedNode = new InstancedNode("instanced_node");
        instancedNode.setShadowMode(RenderQueue.ShadowMode.CastAndReceive);

        rootNode.attachChild(instancedNode);

        playerClient = new PlayerClient(this,"127.0.0.1", 4242);
        playerClient.start();

        cam.setLocation(new Vector3f(38.373516f, 6.689055f, 38.482082f));
        cam.setRotation(new Quaternion(-0.04004206f, 0.918326f, -0.096310444f, -0.38183528f));
        flyCam.setMoveSpeed(15);
        flyCam.setEnabled(true);

        /** Must add a light to make the lit object visible! */
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(1,-2,-1).normalizeLocal());
        sun.setColor(ColorRGBA.White);
        rootNode.addLight(sun);

        AmbientLight al=new AmbientLight();
        al.setColor(new ColorRGBA(1.8f,1.8f,1.8f,0.2f).mult(0.2f));
        rootNode.addLight(al);


        final int SHADOWMAP_SIZE=4096;
        DirectionalLightShadowRenderer dlsr = new DirectionalLightShadowRenderer(assetManager, SHADOWMAP_SIZE, 3);
        dlsr.setLight(sun);
        viewPort.addProcessor(dlsr);

        DirectionalLightShadowFilter dlsf = new DirectionalLightShadowFilter(assetManager, SHADOWMAP_SIZE, 3);
        dlsf.setLight(sun);
        dlsf.setEnabled(true);
        FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
        fpp.addFilter(dlsf);
//        SSAOFilter ssaoFilter = new SSAOFilter(8f, 10f, 0.16f, 0.61f);
//        fpp.addFilter(ssaoFilter);
        viewPort.addProcessor(fpp);

    }

    public InstancedNode getInstancedNode() {
        return instancedNode;
    }

    public void addBlockToScene(int x, int y, int z, int blockID)
    {
        Block block = getRegistries().getBlockLoader().lookup(blockID);
        Geometry geometry = new Geometry(block.getFQN(), block.getMesh());
        geometry.setMaterial(block.getMaterial(this));
        geometry.setLocalTranslation(x,y,z);
        instancedNode.attachChild(geometry);
    }

    public void addChunkToScene(int x, int y, int z, Chunk chunk)
    {
        InstancedNode instancedNode = new InstancedNode("instanced_node");
        rootNode.attachChild(instancedNode);
        instancedNode.setShadowMode(RenderQueue.ShadowMode.CastAndReceive);

        for (int i = 0; i < Chunk.size; i++) {
            for (int j = 14; j < Chunk.size; j++) {
                for (int k = 0; k < Chunk.size; k++) {
                    if (!chunk.isAir(i,j,k)) {
                        Block block = getRegistries().getBlockLoader().lookup(chunk.getBlock(i,j,k));
                        if(block == null)
                            continue;
                        Geometry geometry = new Geometry(block.getFQN(), block.getMesh());
                        geometry.setMaterial(block.getMaterial(this));
                        geometry.setLocalTranslation(x*Chunk.size + i,y*Chunk.size+ j,z*Chunk.size + k);
                        instancedNode.attachChild(geometry);
                    }
                }
            }
        }

        instancedNode.instance();
    }

    @Override
    public void onClientUpdate(float tpf) throws Exception {

        if(chunk != null && updateNeeded)
        {
            for (int ci = 0; ci < 16; ci++) {
                for (int cj = 0; cj < 1; cj++) {
                    for (int ck = 0; ck < 16; ck++) {



                        addChunkToScene(ci,cj,ck,chunk);

//
//                        for (int i = 0; i < Chunk.size; i++) {
//                            for (int j = 0; j < Chunk.size; j++) {
//                                for (int k = 0; k < Chunk.size; k++) {
//                                    if (!chunk.isAir(i,j,k)) {
//                                        breaktome.addBlockToScene(i + ci*Chunk.size, j + cj*Chunk.size, k + ck*Chunk.size, chunk.getBlock(i,j,k));
//                                    }
//                                }
//                            }
//                        }




                    }
                }
            }

            updateNeeded = false;
        }




    }

    @Override
    public void destroy() {
        playerClient.destroy();
        super.destroy();
    }
}
