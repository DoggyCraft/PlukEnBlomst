package com.amqlie.plukenblomst;


import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin; 

public class PlukEnBlomst extends JavaPlugin{
	
	public static HashMap<UUID, Integer> numberOfFlowersHarvested = new HashMap<UUID, Integer>();
	public static HashMap<UUID, Integer> numberOfFlowersPickedUp = new HashMap<UUID, Integer>();
	
	
	public  String convertToUTF8(String text)
	{
	  try
	  {
		  return new String(text.getBytes(), "UTF-8");
	  }
	  catch (UnsupportedEncodingException e)
	  {
		  return "Kunne ikke konvertere det der til unicode. Lort.";
	  }
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
		     player.sendMessage(ChatColor.AQUA + playerName + " har samlet " + ChatColor.GOLD + number + ChatColor.AQUA + " blomst(er) op");
		}
	}
	
	public void flowerHarvestMessage(Player player, Player target) {
		
		for(UUID playerId : PlukEnBlomst.numberOfFlowersHarvested.keySet()) {
		     String playerName = getServer().getOfflinePlayer(playerId).getName();
		     int number = PlukEnBlomst.numberOfFlowersHarvested.get(playerId);
		     player.sendMessage(ChatColor.AQUA + playerName + " har plukket " + ChatColor.GOLD + number + ChatColor.AQUA + " blomst(er)");
		}
	}
	
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
	    
		Player player = (Player)sender;
		if (cmd.getName().equalsIgnoreCase("flower")){
			if (sender instanceof Player) {
				Player target = sender.getServer().getPlayer(args[0]);
				
				if (args.length == 0) {
					if(PlukEnBlomst.numberOfFlowersHarvested.containsKey(player.getUniqueId())) {
						player.sendMessage(ChatColor.AQUA + "Du har høstet " + ChatColor.GOLD + numberOfFlowersHarvested.get(player.getUniqueId()) + ChatColor.AQUA +  " blomst(er)");
				    }
				    else {
				        player.sendMessage(ChatColor.AQUA + "Du har ikke høstet nogen blomster endnu. Kom nu igang!");
				    }
					
					if(PlukEnBlomst.numberOfFlowersPickedUp.containsKey(player.getUniqueId())) {
						player.sendMessage(ChatColor.AQUA + "Du har plukket " + ChatColor.GOLD + numberOfFlowersPickedUp.get(player.getUniqueId()) + ChatColor.AQUA +  " blomst(er)");
				    }
				    else {
				        player.sendMessage(ChatColor.AQUA + "Du har ikke plukket nogen blomster endnu. Kom nu igang!");
				    }
				}
				
				if (args.length == 1) {
					switch(args[0].toLowerCase()) {
						case  "help" : break;
						case  "pickedup" : this.flowerPickedUpMessage(player, target); break;
						case  "harvest" : this.flowerHarvestMessage(player, target); break;
					}	
				}
			}
		}
		
		return true;
	}
}