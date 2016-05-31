package com.gedy.godcraft;

import com.gedy.godcraft.blocks.ModBlocks;
import com.gedy.godcraft.network.ModGUIHandler;
import com.gedy.godcraft.tileentity.ModTileEntities;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent event) {
		ModBlocks.createBlocks();
		ModTileEntities.init();
    }

    public void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(GodCraft.instance, new ModGUIHandler());
    }

    public void postInit(FMLPostInitializationEvent event) {

    }
    
}