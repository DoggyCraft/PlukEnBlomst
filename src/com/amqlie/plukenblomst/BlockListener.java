package com.amqlie.plukenblomst;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class BlockListener implements Listener
{

	BlockListener()
	{
	}
	
	
	@EventHandler
	public void OnBlockBreak(BlockBreakEvent event)
	{
		
		Block block = event.getBlock();
		Player player = event.getPlayer();
		ArrayList<Material> flowers = new ArrayList<Material>( Arrays.asList(

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
        	
	    	int number = PlukEnBlomst.numberOfFlowersHarvested.get(player.getUniqueId());
	    	number++;
	    	PlukEnBlomst.numberOfFlowersHarvested.put(player.getUniqueId(), number);
		}

        
	}
	
	
	@EventHandler
	public void PickupItem(EntityPickupItemEvent event) 
	{
		
		Item item = event.getItem();
		Entity entity = event.getEntity();
		ArrayList<Material> flowers = new ArrayList<Material>( Arrays.asList(

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
        	
	    	int number = PlukEnBlomst.numberOfFlowersPickedUp.get(entity.getUniqueId());
	    	number++;
	    	PlukEnBlomst.numberOfFlowersPickedUp.put(entity.getUniqueId(), number);
		}
		
		
		
		
	}	
}