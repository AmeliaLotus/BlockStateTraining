package com.amelialotus.blockstatetraining.block;

import com.amelialotus.blockstatetraining.MainMod;

import net.minecraft.block.BlockColored;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class LightedGlassBlock extends BlockColored
{
	//Constructor! 
	public LightedGlassBlock(final Material materialIn) {
		super(materialIn);
		this.setSoundType(SoundType.GLASS);
		this.setHardness(0.3F);
		this.setResistance(2000.0F);
		this.setLightOpacity(0);	
		setCreativeTab(MainMod.Light_Tab);
	}
	
	//This method is just auto-generated. Not finished
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

		//Goal is to allow right clicking 
		// on the glass with any dye to change the color.
		
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) 
	{
		return false;
	}
	
	// Somehow makes the block act like glass... If there's a purple thing under the "glass" block, this needs to be added.
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() 
	{
		return BlockRenderLayer.CUTOUT;
	}
		
	// Somehow makes the block act like glass...
	public boolean isFullCube(IBlockState state) 
	{
		return false;
	}
	
	protected boolean canSilkHarvest() 
	{
		return true;
	}
	
	@Override
	public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
	    return world.getBlockState(pos.offset(side)).getBlock() == this;
	}
	
}