package com.gedy.godcraft.tileentity;

import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ModTileEntities{

	public static void init() {
		GameRegistry.registerTileEntity(ModTileEntity.class, "sec_tile_entity");
	}
	
}