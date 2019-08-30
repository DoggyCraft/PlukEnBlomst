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
	
	public void flowerPickMessage(player) {
		
		for(UUID playerId : PlukEnBlomst.numberOfFlowersPickedUp.keySet())
		{
		     String playerName = getServer().getOfflinePlayer(playerId).getName();
		     int number = PlukEnBlomst.numberOfFlowersPickedUp.get(playerId);
		     player.sendMessage(ChatColor.AQUA + playerName + " har plukket " + ChatColor.GOLD + number + ChatColor.AQUA + " blomster");
		}
	}
	
	public void flowerHarvestMessage( ) {
		
	}
	
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
	    
		Player player = (Player)sender;
		Entity entity = (Entity)sender;
		if (cmd.getName().equalsIgnoreCase("flower")){
			if (sender instanceof Player) {
				switch(args[0].toLowerCase()) {
					case  "help" : break;
					case  "picked" : this.flowerPickMessage(); break;
					
				}
			}
		}
		
		for(UUID playerId : PlukEnBlomst.numberOfFlowersPickedUp.keySet())
		{
		     String playerName = getServer().getOfflinePlayer(playerId).getName();
		     int number = PlukEnBlomst.numberOfFlowersPickedUp.get(playerId);
		     player.sendMessage(ChatColor.AQUA + playerName + " har plukket " + ChatColor.GOLD + number + ChatColor.AQUA + " blomster");
		}
		for(UUID playerId : PlukEnBlomst.numberOfFlowersHarvested.keySet())
		{
		     String playerName = getServer().getOfflinePlayer(playerId).getName();
		     int number = PlukEnBlomst.numberOfFlowersHarvested.get(playerId);
		     player.sendMessage(ChatColor.AQUA + playerName + " har høstet " + ChatColor.GOLD + number + ChatColor.AQUA + " blomster");
		}
		
		if(PlukEnBlomst.numberOfFlowersHarvested.containsKey(player.getUniqueId()))
	    {
			player.sendMessage(ChatColor.AQUA + "Du har høstet " + ChatColor.GOLD + numberOfFlowersHarvested.get(player.getUniqueId()) + ChatColor.AQUA +  " blomst(er)");
	    }
	    else
	    {
	        player.sendMessage(ChatColor.AQUA + "Du har ikke høstet nogen blomster endnu. Kom nu igang!");
	    }
		
		if(PlukEnBlomst.numberOfFlowersPickedUp.containsKey(player.getUniqueId()))
	    {
			player.sendMessage(ChatColor.AQUA + "Du har plukket " + ChatColor.GOLD + numberOfFlowersPickedUp.get(entity.getUniqueId()) + ChatColor.AQUA +  " blomst(er)");
	    }
	    else
	    {
	        player.sendMessage(ChatColor.AQUA + "Du har ikke plukket nogen blomster endnu. Kom nu igang!");
	    }
		
		
		
		return true;
	}
}