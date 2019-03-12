package com.breaktome.game.states.client;

import com.breaktome.game.blocks.Block;
import com.breaktome.game.network.client.PlayerClient;
import com.breaktome.game.states.GameMode;
import com.breaktome.game.world.Chunk;
import com.jme3.app.Application;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.instancing.InstancedNode;
import com.jme3.shadow.DirectionalLightShadowFilter;
import com.jme3.shadow.DirectionalLightShadowRenderer;
import com.jme3.shadow.EdgeFilteringMode;

public class SandboxClient extends GameMode {

    private Node world;

    //temp
    public Chunk chunk;
    public boolean updateNeeded = false;

    @Override
    protected void initialize(Application application) {
        super.initialize(application);

        world = new Node();
        getRootNode().attachChild(world);


        // Temp stuff below

        getCamera().setLocation(new Vector3f(38.373516f, 6.689055f, 38.482082f));
        getCamera().setRotation(new Quaternion(-0.04004206f, 0.918326f, -0.096310444f, -0.38183528f));
        getFlyCam().setMoveSpeed(15);
        getFlyCam().setEnabled(true);

        /** Must add a light to make the lit object visible! */
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(1,-2,-1).normalizeLocal());
        sun.setColor(ColorRGBA.White);
        world.addLight(sun);

        AmbientLight al=new AmbientLight();
        al.setColor(new ColorRGBA(1.8f,1.8f,1.8f,0.2f).mult(0.3f));
        world.addLight(al);


        final int SHADOWMAP_SIZE=1024;
        final int SHADOW_ITERATIONS = 4;
        DirectionalLightShadowRenderer dlsr = new DirectionalLightShadowRenderer(getAssetManager(), SHADOWMAP_SIZE, SHADOW_ITERATIONS);
        dlsr.setLight(sun);
        dlsr.setLambda(0.65f);
        dlsr.setShadowIntensity(0.5f);
        dlsr.setEdgeFilteringMode(EdgeFilteringMode.Nearest);
//        dlsr.displayDebug();
        getViewPort().addProcessor(dlsr);

        DirectionalLightShadowFilter dlsf = new DirectionalLightShadowFilter(getAssetManager(), SHADOWMAP_SIZE, SHADOW_ITERATIONS);
        dlsf.setLight(sun);
        dlsf.setEnabled(true);
        dlsf.setLambda(0.65f);
        dlsf.setShadowIntensity(0.5f);
        dlsf.setEdgeFilteringMode(EdgeFilteringMode.Nearest);

        FilterPostProcessor fpp = new FilterPostProcessor(getAssetManager());
        fpp.addFilter(dlsf);
//        SSAOFilter ssaoFilter = new SSAOFilter(8f, 10f, 0.16f, 0.61f);
//        fpp.addFilter(ssaoFilter);
        getViewPort().addProcessor(fpp);
    }

    @Override
    protected void cleanup(Application application) {
        super.cleanup(application);
    }

    @Override
    protected void onEnable() {
        super.onEnable();
    }

    @Override
    protected void onDisable() {
        super.onDisable();
    }

    public void addChunkToScene(int x, int y, int z, Chunk chunk)
    {
        InstancedNode instancedNode = new InstancedNode("instanced_node");
        world.attachChild(instancedNode);
        instancedNode.setShadowMode(RenderQueue.ShadowMode.CastAndReceive);

        for (int i = 0; i < Chunk.size; i++) {
            for (int j = 14; j < Chunk.size; j++) {
                for (int k = 0; k < Chunk.size; k++) {
                    if (!chunk.isAir(i,j,k)) {
                        Block block = getGlobalState().getRegistries().getBlockLoader().lookup(chunk.getBlock(i,j,k));
                        if(block == null)
                            continue;
                        Geometry geometry = new Geometry(block.getFQN(), block.getMesh());
                        geometry.setMaterial(block.getMaterial(getBreaktomeApplication()));
                        geometry.setLocalTranslation(x*Chunk.size + i,y*Chunk.size+ j,z*Chunk.size + k);
                        instancedNode.attachChild(geometry);
                    }
                }
            }
        }

        instancedNode.instance();
    }

    @Override
    public void update(float tpf) {
        if(chunk != null && updateNeeded)
        {
            for (int ci = 0; ci < 1; ci++) {
                for (int cj = 0; cj < 1; cj++) {
                    for (int ck = 0; ck < 1; ck++) {



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

}
