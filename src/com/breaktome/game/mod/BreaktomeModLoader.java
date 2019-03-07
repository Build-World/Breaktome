package com.breaktome.game.mod;

import com.breaktome.Breaktome;
import com.shawnclake.morgencore.core.component.filesystem.Files;
import com.shawnclake.morgencore.core.component.services.Services;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

public class BreaktomeModLoader extends BreaktomeMod {

    public final String modsPath;

    public BreaktomeModLoader(Breaktome app, String modsPath) {
        super(app);
        this.modsPath = modsPath;
    }

    public BreaktomeModService modService()
    {
        return Services.getService(BreaktomeModService.class);
    }


    @Override
    public String getKey() {
        return "modloader";
    }

    @Override
    public String getName() {
        return "Mod Loader";
    }

    @Override
    public String getDescription() {
        return "Loads more mods";
    }

    @Override
    public String getVersion() {
        return "0.0.1";
    }

    @Override
    public void onBoot() throws Exception {
        Files fileHelper = new Files();
        List<File> files = fileHelper.listFilesRecursively(modsPath);
        for(File file : files)
        {
            if(!fileHelper.getFileExtension(file, false).equalsIgnoreCase("jar"))
                continue;

            URL url = file.toURI().toURL();
            ClassLoader cl = new URLClassLoader(new URL[]{url});

            BreaktomeMod breaktomeMod = (BreaktomeMod)cl.loadClass("Mod").getDeclaredConstructor(Breaktome.class).newInstance(getApp());
            modService().add(breaktomeMod);
            System.out.println("Loaded mod: " + breaktomeMod.getName() + " - " + breaktomeMod.getVersion());
        }

        for(IMod mod : modService().getItems())
        {
            mod.onBoot();
        }
    }

    @Override
    public void onLoad() throws Exception {
        for(IMod mod : modService().getItems())
        {
            mod.onLoad();
        }
    }

    @Override
    public void onReady() throws Exception {
        for(IMod mod : modService().getItems())
        {
            mod.onReady();
        }
    }

    @Override
    public void onPlay() throws Exception {
        for(IMod mod : modService().getItems())
        {
            mod.onPlay();
        }
    }

    @Override
    public void onEnable() throws Exception {
        for(IMod mod : modService().getItems())
        {
            mod.onEnable();
        }
    }

    @Override
    public void onDisable() throws Exception {
        for(IMod mod : modService().getItems())
        {
            mod.onDisable();
        }
    }

    @Override
    public void onUpdate(float tpf) throws Exception {
        for(IMod mod : modService().getItems())
        {
            mod.onUpdate(tpf);
        }
    }
}
