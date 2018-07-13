package com.amelialotus.blockstatetraining;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Nullable;

import com.amelialotus.blockstatetraining.api.IBlockWithMapper;
import com.amelialotus.blockstatetraining.api.IMetaLookup;
import com.amelialotus.blockstatetraining.api.Props;
import com.google.common.collect.ImmutableList;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * ClientProxy is used to set up the mod and start it running on normal minecraft.  It contains all the code that should run on the
 *   client side only.
 *   For more background information see here http://greyminecraftcoder.blogspot.com/2013/11/how-forge-starts-up-your-code.html
 */
public class ClientOnlyProxy extends CommonProxy
{

  /**
   * Run before anything else. Read your config, create blocks, items, etc, and register them with the GameRegistry
   */
  public void preInit()
  {
    super.preInit();
    com.amelialotus.blockstatetraining.config.StartupClientOnly.preInitClientOnly();
    
  }

  /**
   * Do your mod setup. Build whatever data structures you care about. Register recipes,
   * send FMLInterModComms messages to other mods.
   */
  public void init()
  {
    super.init();
    com.amelialotus.blockstatetraining.config.StartupClientOnly.initClientOnly();
    
    
  }

  /**
   * Handle interaction with other mods, complete your setup based on this.
   */
  public void postInit()
  {
    super.postInit();
    com.amelialotus.blockstatetraining.config.StartupClientOnly.postInitClientOnly();

  }

  @Override
  public boolean playerIsInCreativeMode(EntityPlayer player) {
    if (player instanceof EntityPlayerMP) {
      EntityPlayerMP entityPlayerMP = (EntityPlayerMP)player;
      return entityPlayerMP.interactionManager.isCreative();
    } else if (player instanceof EntityPlayerSP) {
      return Minecraft.getMinecraft().playerController.isInCreativeMode();
    }
    return false;
  }

  @Override
  public boolean isDedicatedServer() {return false;}
  
  //Copy Paste starts here
  /*
   * Below this, the author is as stated.
   * @author Draco18s
   */
  protected List<ModelRegistryObj> modelsToReg = new ArrayList<ModelRegistryObj>();
	protected List<ModelBakeObj> modelsToBake = new ArrayList<ModelBakeObj>();
	protected List<StateMapObj> statesToMap = new ArrayList<StateMapObj>();

	@Override
	public void _registerBlock(Block block, String registryname) {
		super._registerBlock(block, registryname);
	}

	@Override
	public void _registerBlockWithItem(Block block, String registryname) {
		super._registerBlockWithItem(block, registryname);
		_registerBlockResources(block);
	}

	@Override
	public void _registerBlockWithCustomItem(Block block, ItemBlock iBlock, String registryname) {
		super._registerBlockWithCustomItem(block, iBlock, registryname);
		StateMapperBase b = new DefaultStateMapper();
		BlockStateContainer bsc = block.getBlockState();
		ImmutableList<IBlockState> values = bsc.getValidStates();
		for(IBlockState state : values) {
			String str = b.getPropertyString(state.getProperties());
			_registerBlockItemModelForMeta(block, block.getMetaFromState(state), str);
		}
	}

	@Override
	public <T extends Block & IBlockWithMapper> void _registerBlockWithCustomItemAndMapper(T block, ItemBlock iBlock, String registryname) { 
		super._registerBlockWithCustomItemAndMapper(block, iBlock, registryname);
		BlockStateContainer bsc = block.getBlockState();
		ImmutableList<IBlockState> values = bsc.getValidStates();
		StateMapperBase mapper = ((IBlockWithMapper)block).getStateMapper();
		//ModelLoader.setCustomStateMapper(block, mapper);
		statesToMap.add(new StateMapObj(block, mapper));
		
		Collection<IProperty<?>> list = bsc.getProperties();
		boolean hasItemState = false;
		for(IProperty<?> prop : list) {
			if(prop == Props.HAS_2D_ITEM) {
				hasItemState = true;
				break;
			}
		}
		for(IBlockState state : values) {
			if(hasItemState) {
				state = state.withProperty(Props.HAS_2D_ITEM, true);
			}
			String str = mapper.getPropertyString(state.getProperties());
			_registerBlockItemModelForMeta(block, block.getMetaFromState(state), str);
		}
	}

	private void _registerBlockResources(Block block) {
		Item item = blockItems.get(block);
		modelsToReg.add(new ModelRegistryObj(item, 0, new ModelResourceLocation(block.getRegistryName(),"normal")));
		//ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(block.getRegistryName(),"normal"));
	}

	private void _registerBlockItemModelForMeta(Block block, int metadata, String variant) {
		final Item item = blockItems.get(block);
		if (item != null) {
			_registerItemModelForMeta(item, metadata, variant);
		}
	}

	@Override
	public void _registerItem(Item item, String registryname) {
		super._registerItem(item, registryname);
		_registerItemModel(item);
	}
	
	public void _registerItem(Item item, ResourceLocation registryname, String unlocalized) {
		super._registerItem(item, registryname, unlocalized);
		_registerItemModel(item);
	}

	@Override
	public <T extends IMetaLookup> void _registerItemWithVariants(Item item, String registryname, T variant) {
		super._registerItemWithVariants(item, registryname, variant);
		String variantName = variant.getID();
		NonNullList<ItemStack> subItems = NonNullList.create();
		item.getSubItems(CreativeTabs.SEARCH, subItems);
		for (ItemStack stack : subItems) {
			_registerItemModelForMeta(item, stack.getMetadata(), variantName + "=" + variant.getByOrdinal(stack.getMetadata()));
		}
	}

	private void _registerItemModel(Item item) {
		_registerItemModel(item, item.getRegistryName().toString());
	}

	private void _registerItemModel(Item item, String modelLocation) {
		final ModelResourceLocation fullModelLocation = new ModelResourceLocation(modelLocation, "inventory");
		_registerItemModel(item, fullModelLocation);
	}

	private void _registerItemModel(Item item, final ModelResourceLocation fullModelLocation) {
		modelsToBake.add(new ModelBakeObj(item, fullModelLocation, new ItemMeshDefinition()
		{
			public ModelResourceLocation getModelLocation(ItemStack stack)
			{
				return fullModelLocation;
			}
		}));
	}

	private void _registerItemModelForMeta(Item item, int metadata, String variant) {
		ModelResourceLocation res = new ModelResourceLocation(item.getRegistryName(), variant);
		_registerItemModelForMeta(item, metadata, res);
	}

	private void _registerItemModelForMeta(Item item, int metadata, ModelResourceLocation modelResourceLocation) {
		modelsToReg.add(new ModelRegistryObj(item, metadata, modelResourceLocation));
	}
	
	@SubscribeEvent
	public void registerItems(ModelRegistryEvent event) {
	    for(StateMapObj obj : statesToMap) {
	    	ModelLoader.setCustomStateMapper(obj.block, obj.map);
	    }
		for(ModelBakeObj obj : modelsToBake) {
			ModelLoader.setCustomMeshDefinition(obj.item, obj.meshDefinition);
			if(obj.resource == null) {
				NonNullList<ItemStack> subItems = NonNullList.create();
				obj.item.getSubItems(CreativeTabs.SEARCH, subItems);
				for(ItemStack stack : subItems) {
					ModelBakery.registerItemVariants(obj.item, obj.meshDefinition.getModelLocation(stack));
				}
			}
			else {
				ModelBakery.registerItemVariants(obj.item, obj.resource); // Ensure the custom model is loaded and prevent the default model from being loaded
			}
		}
	    for(ModelRegistryObj obj : modelsToReg) {
	    	ModelLoader.setCustomModelResourceLocation(obj.item, obj.meta, obj.resource);
	    }
	}
	
	protected static class ModelRegistryObj {
		final Item item;
		final int meta;
		final ModelResourceLocation resource;
		
		public ModelRegistryObj(Item i, int m, ModelResourceLocation loc) {
			item = i;
			meta = m;
			resource = loc;
		}
	}
	
	protected static class ModelBakeObj {
		final Item item;
		final ModelResourceLocation resource;
		final ItemMeshDefinition meshDefinition;
		public ModelBakeObj(Item i, @Nullable ModelResourceLocation location, ItemMeshDefinition itemMeshDefinition) {
			item = i;
			resource = location;
			meshDefinition = itemMeshDefinition;
		}
	}
	
	protected static class StateMapObj {
		final Block block;
		final StateMapperBase map;
		public StateMapObj(Block b, StateMapperBase mapper) {
			block = b;
			map = mapper;
		}
		
	}

}