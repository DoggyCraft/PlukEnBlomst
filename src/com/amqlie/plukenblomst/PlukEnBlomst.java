package com.amqlie.plukenblomst;

import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


public class PlukEnBlomst extends JavaPlugin
{
	public static HashMap<UUID, Integer> numberOfFlowersHarvested = new HashMap<UUID, Integer>();
	public static HashMap<UUID, Integer> numberOfFlowersPickedUp;
	
	public void log(String message)
	{
		Logger.getLogger("minecraft").info(message);
	}
	
	public void onEnable()
	{

		getServer().getPluginManager().registerEvents(new BlockListener(), this);
	}

	public void onDisable()
	{

	}	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{

		Player player = (Player)sender;
		player.sendMessage(ChatColor.AQUA + "Du har plukket " + ChatColor.GOLD + numberOfFlowersHarvested.get(player.getUniqueId()) + ChatColor.AQUA +  " blomst(er)");
		player.sendMessage(ChatColor.AQUA + "Du har samlet " + ChatColor.GOLD + numberOfFlowersPickedUp.get(player.getUniqueId()) + ChatColor.AQUA +  " blomst(er) op");
	
		
		return true;
	}
}