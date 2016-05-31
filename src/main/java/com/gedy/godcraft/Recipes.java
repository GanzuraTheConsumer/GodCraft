package com.gedy.godcraft;

import com.gedy.godcraft.blocks.ModBlocks;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Recipes {
	public static void callRecipes () {
		
		GameRegistry.addRecipe(new ItemStack(ModBlocks.sec), 
				"ABA",
				"CFD",
				"AEA",
				'A', Blocks.sea_lantern, 'B', Items.golden_helmet, 'C', Items.golden_chestplate, 'D', Items.golden_leggings, 'E', Items.golden_boots, 'F', Blocks.enchanting_table);
		
	}
}