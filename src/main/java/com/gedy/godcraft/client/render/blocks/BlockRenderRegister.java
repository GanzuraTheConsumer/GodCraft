package com.gedy.godcraft.client.render.blocks;

import com.gedy.godcraft.GodCraft;
import com.gedy.godcraft.blocks.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;

public final class BlockRenderRegister {
	public static String modid = GodCraft.MODID;

	public static void registerBlockRenderer() {
	    reg(ModBlocks.sec);
	}

	public static void reg(Block block) {
	    Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
	    .register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(modid + ":" + block.getUnlocalizedName().substring(5), "inventory"));
	}
}
