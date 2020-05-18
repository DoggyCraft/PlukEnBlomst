package com.amqlie.plukenblomst;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class BlockListener implements Listener 
{	
    PlukEnBlomst plugin;
    
    BlockListener(PlukEnBlomst plugin)
    {
        this.plugin = plugin;
    }
	
	
	@EventHandler
	public int onBlockBreak(BlockBreakEvent event) throws NoSuchAlgorithmException, NoSuchProviderException
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
	        
	    	Integer gainedFlowerpower;
	    	switch(block.getType())
	    	{
	    	case DANDELION:
	    	case POPPY:
	    	case BLUE_ORCHID:
	    	case ALLIUM:
	    		gainedFlowerpower = 2;
	    		break;
	    	case AZURE_BLUET:
	    	case RED_TULIP:
	    	case ORANGE_TULIP:
	    	case WHITE_TULIP:
	    		gainedFlowerpower = 1;
	    		break;
	    	case PINK_TULIP:
	    	case OXEYE_DAISY:
	    	case CORNFLOWER:
	    	case LILY_OF_THE_VALLEY:
	    		gainedFlowerpower = 4;
	    		break;
	    	case ROSE_BUSH:
	    	case LILAC:
	    	case PEONY:
	    	case SUNFLOWER:
	    		gainedFlowerpower = 3;
	    		break;
	    	case WITHER_ROSE:
	    		gainedFlowerpower = 20;
	    		break;
			default:
				gainedFlowerpower = 0;
				break;
	    	}
	    	
	        int level = Level.get().getInt("Level.player." + player.getName());
	        int levelup = level + 1;
	        int flowerpower = Level.get().getInt("Flowerpower.player." + player.getName());
	        int flowerpowerTotal = flowerpower + gainedFlowerpower;
        	int needed = 2 * (level * level);
        	int flowerpowerLevelup = flowerpowerTotal - needed;
        	

            SecureRandom secureRandomGenerator = SecureRandom.getInstance("SHA1PRNG", "SUN");
            int choice = secureRandomGenerator.nextInt(100);
            
        	if(choice < 30 && flowerpower >= 0) {
		        		player.sendMessage(ChatColor.AQUA + "Du fik " + ChatColor.GOLD + gainedFlowerpower + ChatColor.AQUA +" flowerpower");
		        		Level.get().set("Flowerpower.player." + player.getName(), flowerpowerTotal);
		        		Level.save();
		        		flowerpower = Level.get().getInt("Flowerpower.player." + player.getName());
		        		needed = 2 * (level * level);
		        		if(flowerpower >= needed) {
			        		do {
				        		player.sendMessage(ChatColor.AQUA + "Og du kom op i level " + ChatColor.GOLD + levelup + ChatColor.AQUA +"!");
				        		Level.get().set("Level.player." + player.getName(), levelup);
				        		Level.get().set("Flowerpower.player." + player.getName(), flowerpowerLevelup);
				    	 	    Level.save();
				    	 	   	level = Level.get().getInt("Level.player." + player.getName());
				   	        	levelup = level + 1;
				   	        	flowerpower = Level.get().getInt("Flowerpower.player." + player.getName());
				   	        	flowerpowerTotal = flowerpower + gainedFlowerpower;
				           		needed = 2 * (level * level);
				           		flowerpowerLevelup = flowerpowerTotal - needed;
			        		}
			        		while(flowerpower >= needed);
		        		}
		        }
        	if(choice < 20 && level >= 10) {
        		event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), new ItemStack(event.getBlock().getType()));
        	}
        	if(choice < 5 && level >= 20) {
        		event.setDropItems(false);
        		ItemStack item = new ItemStack(event.getBlock().getType(), 1);
        		item.addEnchantment(Enchantment.KNOCKBACK, 1);
        		event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), new ItemStack(item));
        		player.sendMessage("Du fik en speciel blomst!");
        	}
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
	    	int antal = event.getItem().getItemStack().getAmount();
	    	int total = pickedup + antal;
	        plugin.config.set("PickedUp.player." + entity.getName(), total);
	        plugin.saveConfig();
	    	
		}
		return 0;
	}	
}