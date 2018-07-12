package com.amelialotus.blockstatetraining;

import com.amelialotus.blockstatetraining.config.configInit;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MainMod.MODID, version = MainMod.VERSION,
guiFactory= MainMod.GUIFACTORY)  //delete guiFactory if MBE70 not present and you don't have a configuration GUI
public class MainMod {

	public static final String MODID = "blockstatetraining";
    public static final String VERSION = "MC 1.12.2 V1";

    public static final String GUIFACTORY = "com.amelialotus.blockstatetraining.config.BSGuiFactory"; //delete if MBE70 not present

    // The instance of your mod that Forge uses.  Optional.
    @Mod.Instance(MainMod.MODID)
    public static MainMod instance;

 // Says where the client and server 'proxy' code is loaded.
    @SidedProxy(clientSide="com.amelialotus.blockstatetraining.ClientOnlyProxy", 
    		serverSide="com.amelialotus.blockstatetraining.DedicatedServerProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
      proxy.preInit();
      configInit.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
      proxy.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
      proxy.postInit();
    }

    /**
     * Prepend the name with the mod ID, suitable for ResourceLocations such as textures.
     * @param name
     * @return eg "minecraftbyexample:myblockname"
     */
    public static String prependModID(String name) {return MODID + ":" + name;}
}

