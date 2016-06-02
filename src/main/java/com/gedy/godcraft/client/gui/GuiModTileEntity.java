package com.gedy.godcraft.client.gui;

import com.gedy.godcraft.guicontainer.ContainerModTileEntity;
import com.gedy.godcraft.tileentity.ModTileEntity;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;


public class GuiModTileEntity extends GuiContainer {
	private IInventory playerInv;
	private ModTileEntity te;
	
    public GuiModTileEntity(IInventory playerInv, ModTileEntity te) {//constructor
        super(new ContainerModTileEntity(playerInv, te));
        
        this.playerInv = playerInv;
        this.te = te;
        
        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {//used to draw the GUI
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(new ResourceLocation("godcraft:textures/gui/container/sec_tile_entity.png"));
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {//used to draw the GUI
    	
    }
}