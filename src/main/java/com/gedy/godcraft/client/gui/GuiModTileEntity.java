package com.gedy.godcraft.client.gui;

import com.gedy.godcraft.guicontainer.ContainerModTileEntity;
import com.gedy.godcraft.tileentity.ModTileEntity;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;

public class GuiModTileEntity extends GuiContainer {

    public GuiModTileEntity(IInventory playerInv, ModTileEntity te) {//constructor
        super(new ContainerModTileEntity(playerInv, te));

        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {//used to draw the GUI
    }
}