package com.gedy.godcraft.guicontainer;
import com.gedy.godcraft.tileentity.ModTileEntity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerModTileEntity extends Container {
	private ModTileEntity te;
	
    public ContainerModTileEntity(IInventory playerInv, ModTileEntity te) {
    	this.te = te;
    	
        // Tile entity inventory, Slot 0-3, Slot IDs 0-3
        for (int y = 0; y < 2; ++y) {
            for (int x = 0; x < 2; ++x) {
                this.addSlotToContainer(new Slot(te, x + y * 3, 8 + x * 18, 17 + y * 18));//adds tile entity's slots to the container
            }
        }

        // Player inventory, Slot 9-35, Slot IDs 9-35
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));//adds player's slots to the container
            }
        }

        // Player hotbar, Slot 0-8, Slot IDs 36-44
        for (int x = 0; x < 9; ++x) {
            this.addSlotToContainer(new Slot(playerInv, x, 8 + x * 18, 142));//adds player's hotbar's slots to the container
        }
    }
    
    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
    	return this.te.isUseableByPlayer(playerIn);
    }
    
    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
        ItemStack previous = null;
        Slot slot = (Slot) this.inventorySlots.get(fromSlot);

        if (slot != null && slot.getHasStack()) {
            ItemStack current = slot.getStack();
            previous = current.copy();

            // [...] Custom behaviour

            if (current.stackSize == 0)
                slot.putStack((ItemStack) null);
            else
                slot.onSlotChanged();

            if (current.stackSize == previous.stackSize)
                return null;
            slot.onPickupFromSlot(playerIn, current);
        }
        return previous;
    }
}
