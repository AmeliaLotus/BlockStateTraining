package com.amelialotus.blockstatetraining.api;

import net.minecraft.client.renderer.block.statemap.StateMapperBase;
/*
 * @author Draco18s
 */
public interface IBlockWithMapper {
	/**
	 * Used to override the StateMapperBase with a custom mapper
	 * @return
	 */
	public StateMapperBase getStateMapper();
}
