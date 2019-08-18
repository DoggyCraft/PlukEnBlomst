package com.amqlie.plukenblomst;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;


public class BlockListener implements Listener
{
	// Will be called when this BlockListener is created
	BlockListener()
	{
	}
	
	// Called when a block is placed by a player.
	// If a Block Place event is cancelled, the block will not be placed.
	@EventHandler
	public void OnBlockPlace(BlockPlaceEvent event)
	{
		// Do stuff		
	}
	
	
	// Called when a block is broken by a player.
	// If a Block Place event is cancelled, the block will not be placed.
	@EventHandler
	public void OnBlockBreak(BlockBreakEvent event)
	{
		// Do stuff		
	}
	
	
	// This event will fire as cancelled if the vanilla behavior is to do nothing (e.g interacting with air). 
	// For the purpose of avoiding doubt, this means that the event will only be in the cancelled state if it is fired as a result of some prediction made by the server where no subsequent code will run,
	// rather than when the subsequent interaction activity (e.g. placing a block in an illegal position (BlockCanBuildEvent) will fail.
	@EventHandler
	public void OnPlayerInteract(PlayerInteractEvent event)
	{		
		// Do stuff
	}		
}