package com.amelialotus.blockstatetraining.block;

import com.amelialotus.blockstatetraining.MainMod;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

/**
 * A base class for this mod's blocks.
 */
public class BlockRegister extends Block {
	public BlockRegister(final Material material, final MapColor mapColor, final String blockName) {
		super(material, mapColor);
		setBlockName(this, blockName);
		//setCreativeTab(MainMod.creativeTab);
	}

	public BlockRegister(final Material materialIn, final String blockName) {
		this(materialIn, materialIn.getMaterialMapColor(), blockName);
	}

	/**
	 * Set the registry name of {@code block} to {@code blockName} and the unlocalised name to the full registry name.
	 *
	 * @param block     The block
	 * @param blockName The block's name
	 */
	public static void setBlockName(final Block block, final String blockName) {
		block.setRegistryName(MainMod.MODID, blockName);
		block.setUnlocalizedName(block.getRegistryName().toString());
	}
}