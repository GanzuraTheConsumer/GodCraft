package com.gedy.godcraft;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;


@Mod(modid = GodCraft.MODID, name = GodCraft.NAME, version = GodCraft.VERSION)


public class GodCraft
{
	public static final String NAME = "GodCraft";
    public static final String MODID = "godcraft";
    public static final String VERSION = "1.8.9-A3";
    
    @SidedProxy(clientSide="com.gedy.godcraft.ClientProxy", serverSide="com.gedy.godcraft.ServerProxy")
    public static CommonProxy proxy;

    //This needs to be your modid not just some random string
    @Mod.Instance(GodCraft.MODID)
    public static GodCraft instance;
    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	this.proxy.preInit(event);
    }
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
    	this.proxy.init(event);
    	Recipes.callRecipes();
    }
    
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	this.proxy.postInit(event);
    }
}