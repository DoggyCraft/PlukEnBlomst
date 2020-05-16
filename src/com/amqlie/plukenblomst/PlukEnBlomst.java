package com.amqlie.plukenblomst;


import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin; 

public class PlukEnBlomst extends JavaPlugin{
	
	FileConfiguration config = getConfig();
	
	
	public  String convertToUTF8(String text)
	{
		
		return new String(text.getBytes(StandardCharsets.UTF_8));
	  } 
	
	public void log(String message)
	{
		Logger.getLogger("minecraft").info(message);
	}
	
    @Override
    public void onEnable()
    {
    	getServer().getPluginManager().registerEvents(new BlockListener(this), this);
    }
	
	@Override
    public void onDisable()
	{
		//do nothing
	}	
	
	public void flowerPickedUpMessage(Player player, Player target) {
		int pickeduptarget = getConfig().getInt("PickedUp.player." + target.getName());
		player.sendMessage(ChatColor.GOLD +  target.getName() + ChatColor.AQUA + " har samlet " + ChatColor.GOLD + pickeduptarget + ChatColor.AQUA + " blomst(er) op");
	}
	
	public void flowerHarvestMessage(Player player, Player target) {
		int harvestedtarget = getConfig().getInt("Harvested.player." + target.getName());
		player.sendMessage(ChatColor.GOLD +  target.getName() + ChatColor.AQUA + " har plukket " + ChatColor.GOLD + harvestedtarget + ChatColor.AQUA + " blomst(er)");
		
	}
	
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
	    
		
		if(cmd.getName().equalsIgnoreCase("blomst") && sender instanceof Player){
				
				Player player = (Player)sender;
				int harvested = getConfig().getInt("Harvested.player." + player.getName());
				int pickedup = getConfig().getInt("PickedUp.player." + player.getName());
				
				if (args.length == 0) {
					if(harvested >= 1) {
						player.sendMessage(ChatColor.AQUA + "Du har plukket " + ChatColor.GOLD + harvested + ChatColor.AQUA +  " blomst(er)");
				    }
				    else {
				        player.sendMessage(ChatColor.AQUA + "Du har ikke plukket nogen blomster endnu. Kom nu igang!");
				    }
					
					if(pickedup >= 1) {
						player.sendMessage(ChatColor.AQUA + "Du har samlet " + ChatColor.GOLD + pickedup + ChatColor.AQUA +  " blomst(er)");
				    }
				    else {
				        player.sendMessage(ChatColor.AQUA + "Du har ikke samlet nogen blomster op endnu. Kom nu igang!");
				    }
				}
				
				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("help")) {
						player.sendMessage(ChatColor.RED + "Du skal skrive " + ChatColor.GOLD + "/blomst" + ChatColor.RED + " eller " + ChatColor.GOLD + "/blomst (spiller)");
					} else {
						Player target = sender.getServer().getPlayer(args[0]);
						if(target == null) {
							player.sendMessage(ChatColor.RED + "Denne spiller findes ikke");
						} else {
							int harvestedtarget = getConfig().getInt("Harvested.player." + target.getName());
							int pickeduptarget = getConfig().getInt("PickedUp.player." + target.getName());
							if(harvestedtarget >= 1) {
								this.flowerHarvestMessage(player, target);  
							} else {
								player.sendMessage(ChatColor.GOLD +  target.getName() + ChatColor.AQUA + " har ikke plukket nogen blomster");
							}
							if(pickeduptarget >= 1) {
								this.flowerPickedUpMessage(player, target);  
							} else {
								player.sendMessage(ChatColor.GOLD +  target.getName() + ChatColor.AQUA + " har ikke samlet nogle blomster op");
						    }
						}
					}	
				}
			} else {
				System.out.println("Du skal være en spiller for at skrive denne kommando.");
			}
		
		return true;
	}

}