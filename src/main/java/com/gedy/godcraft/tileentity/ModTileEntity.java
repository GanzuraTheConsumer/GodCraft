package com.gedy.godcraft.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;


public class ModTileEntity extends TileEntity implements IInventory {

	private ItemStack[] inventory; //storage for items
    private String customName; //contains inventory name

    public ModTileEntity() { //constructor for making array of itemStacks
        this.inventory = new ItemStack[this.getSizeInventory()];
    }

    public String getCustomName() { //receiving custom name
        return this.customName;
    }

    public void setCustomName(String customName) { //assigning custom name
        this.customName = customName;
    }
    
    @Override
    public String getName() {//getting inventory name
        return this.hasCustomName() ? this.customName : "container.sec_tile_entity";
    }

    @Override
    public boolean hasCustomName() {//getting inventory name
        return this.customName != null && !this.customName.equals("");
    }

    @Override
    public IChatComponent getDisplayName() {//getting inventory name
        return this.hasCustomName() ? new ChatComponentText(this.getName()) : new ChatComponentTranslation(this.getName());
    }
    
    @Override
    public int getSizeInventory() {//slot count of inventory
        return 4;
    }
    
    @Override
    public ItemStack getStackInSlot(int index) {//checks whether the stack index is valid and returns the stack within
        if (index < 0 || index >= this.getSizeInventory())
            return null;
        return this.inventory[index];
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {//decreases the amount of items in the specified slot by the given amount and returns an ItemStack containing the amount of items that has been removed
        if (this.getStackInSlot(index) != null) {
            ItemStack itemstack;

            if (this.getStackInSlot(index).stackSize <= count) {
                itemstack = this.getStackInSlot(index);
                this.setInventorySlotContents(index, null);
                this.markDirty();
                return itemstack;
            } else {
                itemstack = this.getStackInSlot(index).splitStack(count);

                if (this.getStackInSlot(index).stackSize <= 0) {
                    this.setInventorySlotContents(index, null);
                } else {
                    //Just to show that changes happened
                    this.setInventorySlotContents(index, this.getStackInSlot(index));
                }

                this.markDirty();
                return itemstack;
            }
        } else {
            return null;
        }
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {//clears a slot and returns it's previous content
        ItemStack stack = this.getStackInSlot(index);
        this.setInventorySlotContents(index, null);
        return stack;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {//sets the content of a slot
        if (index < 0 || index >= this.getSizeInventory())
            return;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
            stack.stackSize = this.getInventoryStackLimit();
            
        if (stack != null && stack.stackSize == 0)
            stack = null;

        this.inventory[index] = stack;
        this.markDirty();
    }
    
    @Override
    public int getInventoryStackLimit() {//sets stack limit to 64 (game limit)
        return 64;
    }
    
    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {//returns true if the given player has access to the inventory
        return this.worldObj.getTileEntity(this.getPos()) == this && player.getDistanceSq(this.pos.add(0.5, 0.5, 0.5)) <= 64;
    }
    
    @Override
    public void openInventory(EntityPlayer player) {//not generally needed
    }

    @Override
    public void closeInventory(EntityPlayer player) {//not generally needed
    }
    
    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {//returns whether the given ItemStack can be placed in the given slot
        return true;
    }
    
    @Override
    public int getField(int id) {//synchronize data between server and client
        return 0;
    }

    @Override
    public void setField(int id, int value) {//synchronize data between server and client
    }

    @Override
    public int getFieldCount() {//synchronize data between server and client
        return 0;
    }
    
    @Override
    public void clear() {
        for (int i = 0; i < this.getSizeInventory(); i++)
            this.setInventorySlotContents(i, null);
    }
	
    
    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        NBTTagList list = new NBTTagList();//creates a new NBTTagList
        for (int i = 0; i < this.getSizeInventory(); ++i) {
            if (this.getStackInSlot(i) != null) {
                NBTTagCompound stackTag = new NBTTagCompound();
                stackTag.setByte("Slot", (byte) i);//every ItemStack in the inventory that is not null is added to this list
                this.getStackInSlot(i).writeToNBT(stackTag);//Each ItemStack is written into an NBTTagCompound together with it's slot ID
                list.appendTag(stackTag);
            }
        }
        nbt.setTag("Items", list);//The NBTTagList is saved into the main tag with the name "Items"

        if (this.hasCustomName()) {//the custom name is saved if existent
            nbt.setString("CustomName", this.getCustomName());
        }
    }


    @Override
    public void readFromNBT(NBTTagCompound nbt) {// loads tags and reassembles the inventory content
        super.readFromNBT(nbt);

        NBTTagList list = nbt.getTagList("Items", 10);
        for (int i = 0; i < list.tagCount(); ++i) {
            NBTTagCompound stackTag = list.getCompoundTagAt(i);
            int slot = stackTag.getByte("Slot") & 255;
            this.setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(stackTag));
        }

        if (nbt.hasKey("CustomName", 8)) {
            this.setCustomName(nbt.getString("CustomName"));
        }
    }
}