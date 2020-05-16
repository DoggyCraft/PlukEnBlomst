package com.amqlie.plukenblomst;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class BlockListener implements Listener
{

    PlukEnBlomst plugin;

    BlockListener(PlukEnBlomst plugin)
    {
        this.plugin = plugin;
    }
	
	
	@EventHandler
	public int onBlockBreak(BlockBreakEvent event)
	{
		Block block = event.getBlock();
		Player player = event.getPlayer();
		ArrayList<Material> flowers = new ArrayList<>( Arrays.asList(

                Material.DANDELION,
                Material.POPPY,
                Material.BLUE_ORCHID,
                Material.ALLIUM,
                Material.AZURE_BLUET,
                Material.RED_TULIP,
                Material.ORANGE_TULIP,
                Material.WHITE_TULIP,
                Material.PINK_TULIP,
                Material.OXEYE_DAISY,
                Material.CORNFLOWER,
                Material.LILY_OF_THE_VALLEY,
                Material.ROSE_BUSH,
                Material.LILAC,
                Material.PEONY,
                Material.SUNFLOWER,
                Material.WITHER_ROSE

               ) );

	    if(flowers.contains(block.getType())){
	    	int harvested = plugin.getConfig().getInt("Harvested.player." + player.getName());
	    	int total = harvested + 1;
	        plugin.config.set("Harvested.player." + player.getName(), total);
	        plugin.saveConfig();
		}
        return 0;
	}
	
	
	@EventHandler
	public int pickupItem(EntityPickupItemEvent event) 
	{
		Entity entity = event.getEntity();
		ArrayList<Material> flowers = new ArrayList<>( Arrays.asList(

                Material.DANDELION,
                Material.POPPY,
                Material.BLUE_ORCHID,
                Material.ALLIUM,
                Material.AZURE_BLUET,
                Material.RED_TULIP,
                Material.ORANGE_TULIP,
                Material.WHITE_TULIP,
                Material.PINK_TULIP,
                Material.OXEYE_DAISY,
                Material.CORNFLOWER,
                Material.LILY_OF_THE_VALLEY,
                Material.ROSE_BUSH,
                Material.LILAC,
                Material.PEONY,
                Material.SUNFLOWER,
                Material.WITHER_ROSE

               ) );

	    if(flowers.contains(event.getItem().getItemStack().getType())){
	    	int pickedup = plugin.getConfig().getInt("PickedUp.player." + entity.getName());
	    	int total = pickedup + 1;
	        plugin.config.set("PickedUp.player." + entity.getName(), total);
	        plugin.saveConfig();
	    	
		}
		return 0;
	}	
}