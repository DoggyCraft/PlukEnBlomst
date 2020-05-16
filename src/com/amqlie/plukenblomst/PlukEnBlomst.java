package com.amqlie.plukenblomst;


import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin; 

public class PlukEnBlomst extends JavaPlugin{
	
	static final Map<UUID, Integer> numberOfFlowersHarvested = new HashMap<>();
	static final Map<UUID, Integer> numberOfFlowersPickedUp = new HashMap<>();
	
	
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

		getServer().getPluginManager().registerEvents(new BlockListener(), this);
	}
	
	@Override
    public void onDisable()
	{
		//do nothing
	}	
	
	public void flowerPickedUpMessage(Player player, Player target) {
		
		for(UUID playerId : PlukEnBlomst.numberOfFlowersPickedUp.keySet()) {
		     String playerName = getServer().getOfflinePlayer(playerId).getName();
		     int number = PlukEnBlomst.numberOfFlowersPickedUp.get(playerId);
		     player.sendMessage(ChatColor.AQUA + playerName + " har samlet " + ChatColor.GOLD + number + ChatColor.AQUA + " blomster op");
		}
	}
	
	public void flowerHarvestMessage(Player player, Player target) {
		
		for(UUID playerId : PlukEnBlomst.numberOfFlowersHarvested.keySet()) {
		     String playerName = getServer().getOfflinePlayer(playerId).getName();
		     int number = PlukEnBlomst.numberOfFlowersHarvested.get(playerId);
		     player.sendMessage(ChatColor.AQUA + playerName + " har plukket " + ChatColor.GOLD + number + ChatColor.AQUA + " blomster");
		}
	}
	
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
	    
		
		if(cmd.getName().equalsIgnoreCase("blomst") && sender instanceof Player){

				Player player = (Player)sender;
				Player target = sender.getServer().getPlayer(args[1]);
				
				player.sendMessage("Test");
				
				if (args.length == 0) {
					if(PlukEnBlomst.numberOfFlowersHarvested.containsKey(player.getUniqueId())) {
						player.sendMessage(ChatColor.AQUA + "Du har høstet " + ChatColor.GOLD + numberOfFlowersHarvested.get(player.getUniqueId()) + ChatColor.AQUA +  " blomst(er)");
				    }
				    else {
				        player.sendMessage(ChatColor.AQUA + "Du har ikke høstet nogen blomster endnu. Kom nu igang!");
				    }
					
					if(PlukEnBlomst.numberOfFlowersPickedUp.containsKey(player.getUniqueId())) {
						player.sendMessage(ChatColor.AQUA + "Du har plukket " 	+ ChatColor.GOLD + numberOfFlowersPickedUp.get(player.getUniqueId()) + ChatColor.AQUA +  " blomst(er)");
				    }
				    else {
				        player.sendMessage(ChatColor.AQUA + "Du har ikke plukket nogen blomster endnu. Kom nu igang!");
				    }
				}
				
				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("help")) {
						player.sendMessage(ChatColor.RED + "Du skal skrive" + ChatColor.GOLD + "/blomst" + ChatColor.RED + "eller" + ChatColor.GOLD + "/blomst (spiller)");
						return true;
					} else {
						this.flowerPickedUpMessage(player, target); 
						this.flowerHarvestMessage(player, target);
					}	
				}
			}		
		
		return true;
	}

}