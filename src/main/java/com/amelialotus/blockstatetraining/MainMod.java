package com.amelialotus.blockstatetraining;

import org.apache.logging.log4j.Logger;

import com.amelialotus.blockstatetraining.block.LightedGlassBlock;
import com.amelialotus.blockstatetraining.config.configInit;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = MainMod.MODID, version = MainMod.VERSION,
guiFactory= MainMod.GUIFACTORY)  //delete guiFactory if config not present and you don't have a configuration GUI
public class MainMod {

	//Declare things here
	public static Block blockLightedGlass;
	
	
	//Stop declaring	
	
	
	public static final String MODID = "blockstatetraining";
    public static final String VERSION = "MC 1.12.2 V1";

    public static final String GUIFACTORY = "com.amelialotus.blockstatetraining.config.BSGuiFactory"; //delete if MBE70 not present

    // The instance of your mod that Forge uses.  Optional.
    @Mod.Instance(MainMod.MODID)
    public static MainMod instance;

 // Says where the client and server 'proxy' code is loaded.
    @SidedProxy(clientSide="com.amelialotus.blockstatetraining.ClientOnlyProxy", 
    		serverSide="com.amelialotus.blockstatetraining.CommonProxy")
    public static CommonProxy proxy;
    
    public static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
      configInit.preInit();
      
      //Easy Register everything with CommonProxy(Server) and ClientOnlyProxy(Client)
      blockLightedGlass = new LightedGlassBlock(Material.GLASS);
      CommonProxy.registerBlockWithItem(blockLightedGlass, "lighted_glass_block");
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    }
    
    public static final CreativeTabs Light_Tab = new CreativeTabs(CreativeTabs.CREATIVE_TAB_ARRAY.length, "LightMod")
    {
    	//Gives the Tab Icon appropriately!
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem()
        {
            return new ItemStack(blockLightedGlass);
        }
        
        @SideOnly(Side.CLIENT)
        public void displayAllRelevantItems(NonNullList<ItemStack> p_78018_1_)
        {
            p_78018_1_.add(new ItemStack(blockLightedGlass));
            for (Item item : Item.REGISTRY)
            {
                item.getSubItems(this, p_78018_1_);
            }
        }
    };
}

