package com.amqlie.plukenblomst;

import java.util.logging.Logger;


import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;


public class PlukEnBlomst extends JavaPlugin
{
	public void log(String message)
	{
		Logger.getLogger("minecraft").info(message);
	}
	
	public void onEnable()
	{
		// We want to listen for block events, so register our block listener
		getServer().getPluginManager().registerEvents(new BlockListener(), this);
	}

	public void onDisable()
	{
		// Do stuff when the plugin is disabled, like saving configuration
	}	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		// Do stuff here when a player uses a command
		
		return true;
	}
}