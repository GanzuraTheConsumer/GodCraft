package com.gedy.godcraft;

import com.gedy.godcraft.network.ModGUIHandler;
import com.gedy.godcraft.tileentity.ModTileEntity;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ModBlockTileEntity extends BlockContainer {
	
	public ModBlockTileEntity(String unlocalizedName) {
        super(Material.iron);
        this.setUnlocalizedName(unlocalizedName);
        this.setHardness(2.0f);
        this.setResistance(6.0f);
        this.setHarvestLevel("pickaxe", 2);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }
	
    @Override
    public int getRenderType() {
    	return 3;
    }
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new ModTileEntity();
    }
    
    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState blockstate) {//makes items in inventory be dropped if block is broken
        ModTileEntity te = (ModTileEntity) world.getTileEntity(pos);
        InventoryHelper.dropInventoryItems(world, pos, te);
        super.breakBlock(world, pos, blockstate);
    }


    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {//sets custom name of inventory when item used to place the block is named
        if (stack.hasDisplayName()) {
            ((ModTileEntity) worldIn.getTileEntity(pos)).setCustomName(stack.getDisplayName());
        }
    }
    
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {//opens block on right-clicking
        if (!world.isRemote) {
            player.openGui(GodCraft.instance, ModGUIHandler.MOD_TILE_ENTITY_GUI, world, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }
    
    
    
}