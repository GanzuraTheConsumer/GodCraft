package com.gedy.godcraft.blocks;

import com.gedy.godcraft.ModBlockTileEntity;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ModBlocks
{
	public static Block sec;
	public static Block sec2;
	
	public static void createBlocks()
	{
		GameRegistry.registerBlock(sec = new BasicBlock("SEC").setLightLevel(1.0f), "SEC");
		
		GameRegistry.registerBlock(sec = new ModBlockTileEntity("SEC2"), "SEC2");
	}
}