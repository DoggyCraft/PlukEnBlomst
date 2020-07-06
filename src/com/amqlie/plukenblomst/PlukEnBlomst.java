package com.amqlie.plukenblomst;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;


public class PlukEnBlomst extends JavaPlugin{
	
	FileConfiguration config = getConfig();
	
	private SpecialFlowers specialFlowers;
	SpecialFlowers loveFlower = new SpecialFlowers();
	
	private LevelTop levelTop;
	LevelTop getTop15 = new LevelTop();
	
	int sekunder = 60;
	static ConsoleCommandSender console = Bukkit.getConsoleSender();
	
	
	private static final String LINJER = "-------";
	
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
    	this.specialFlowers = new SpecialFlowers();
    	getServer().getPluginManager().registerEvents(new BlockListener(this), this);
    	saveDefaultConfig();
        Level.setup();
        Level.save();
        
        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {

			@Override
            public void run() {
            	for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            		specialFlowers.loveFlower(p);
            	}
            }

	    }, 20L * sekunder, 20L * sekunder);
    }
	
	@Override
    public void onDisable()
	{
		//do nothing
	}	
	
	@Override
	public 	boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("blomst") && sender instanceof Player){	
				Player player = (Player)sender;
				int harvested = getConfig().getInt("Harvested.player." + player.getName());
				int pickedup = getConfig().getInt("PickedUp.player." + player.getName());
				int level = Level.get().getInt(BlockListener.levelPath + player.getName());
		        int flowerpower = Level.get().getInt(BlockListener.flowerPowerPath + player.getName());
		        int needed = 2 * (level * level);
	        	int neededFlowerpower = needed - flowerpower;
	        	int levelup = level + 1;
				
				if (args.length == 0) {
					player.sendMessage(ChatColor.YELLOW  + LINJER + ChatColor.GOLD + " Blomster " + ChatColor.YELLOW  + LINJER);
					if(harvested >= 1) {
						player.sendMessage(ChatColor.AQUA + "Du har plukket " + ChatColor.GOLD + harvested + ChatColor.AQUA +  " blomst(er)");
				    }
				    else {
				        player.sendMessage(ChatColor.AQUA + "Du har ikke plukket nogen blomster endnu. Kom nu igang!");
				    }
					
					if(pickedup >= 1) {
						player.sendMessage(ChatColor.AQUA + "Du har samlet " + ChatColor.GOLD + pickedup + ChatColor.AQUA +  " blomst(er) op");
				    }
				    else {
				        player.sendMessage(ChatColor.AQUA + "Du har ikke samlet nogen blomster op endnu. Kom nu igang!");
				    }
					player.sendMessage(ChatColor.YELLOW  + LINJER + ChatColor.GOLD + " Levels " + ChatColor.YELLOW  + LINJER);
					if(flowerpower >= 1) {
						player.sendMessage(ChatColor.AQUA + "Du har " + ChatColor.GOLD + flowerpower + ChatColor.AQUA +  " flowerpower");
				    }else {
				        player.sendMessage(ChatColor.AQUA + "Du har intet flowerpower!");
				    }
					if(level >= 1) {
						player.sendMessage(ChatColor.AQUA + "Du er i level " + ChatColor.GOLD + level + ChatColor.AQUA);
						player.sendMessage(ChatColor.RED + "Du mangler " + ChatColor.DARK_RED + neededFlowerpower + ChatColor.RED + " flowerpower til level " + ChatColor.DARK_RED + levelup);
				    }
				    else {
				        player.sendMessage(ChatColor.AQUA + "Du er ikke i noget level!");
				    }
					if(level >= 10) {
						player.sendMessage(ChatColor.AQUA + "Du har passeret eller er i level " + ChatColor.GOLD + "10");
						player.sendMessage(ChatColor.AQUA + "Så du har " + ChatColor.GOLD + "20%" + ChatColor.AQUA + " chance for en extra blomst!");
					}
					if(level >= 20) {
						player.sendMessage(" ");
						player.sendMessage(ChatColor.AQUA + "Du har passeret eller er i level " + ChatColor.GOLD + "20");
						player.sendMessage(ChatColor.AQUA + "Så du har " + ChatColor.GOLD + "5%" + ChatColor.AQUA + " chance for en fortryllet blomst!");
					}
				}
				
				if (args.length == 1 && args[0].equalsIgnoreCase("help")) {
						player.sendMessage(ChatColor.RED + "Du skal skrive " + ChatColor.GOLD + "/blomst" + ChatColor.RED + " eller " + ChatColor.GOLD + "/blomst info (spiller)");
				}
				if (args.length == 1 && args[0].equalsIgnoreCase("top")) {
					this.levelTop = new LevelTop();
					for(int i = 0; i<5; i++) {
						player.sendMessage(levelTop.sortLevels(i));
					}
				}
				if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
						if(player.hasPermission("PlukEnBlomst.staff")) {
							Level.reload();
							Level.save();
							reloadConfig();
					        saveConfig();
							player.sendMessage(ChatColor.RED + "Du har reloaded alt data");
						} else {
							player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "[FEJL]" + ChatColor.RED + " Dette har du vidst ikke adgang til.");
						}
					}
				if (args.length == 2 && args[0].equalsIgnoreCase("info")) {
							Player target = sender.getServer().getPlayer(args[1]);
							if(target == null) {
								player.sendMessage(ChatColor.RED + "Denne spiller findes ikke");
							} else {
								int harvestedtarget = getConfig().getInt("Harvested.player." + target.getName());
								int pickeduptarget = getConfig().getInt("PickedUp.player." + target.getName());
								int leveltarget = Level.get().getInt(BlockListener.levelPath + target.getName());
								
								player.sendMessage(ChatColor.YELLOW  + LINJER + ChatColor.GOLD + " Blomster " + ChatColor.YELLOW  + LINJER);
								if(harvestedtarget >= 1) {
									player.sendMessage(ChatColor.GOLD +  target.getName() + ChatColor.AQUA + " har plukket " + ChatColor.GOLD + harvestedtarget + ChatColor.AQUA + " blomst(er)"); 
								} else {
									player.sendMessage(ChatColor.GOLD +  target.getName() + ChatColor.AQUA + " har ikke plukket nogen blomster");
								}
								if(pickeduptarget >= 1) {
									player.sendMessage(ChatColor.GOLD +  target.getName() + ChatColor.AQUA + " har samlet " + ChatColor.GOLD + pickeduptarget + ChatColor.AQUA + " blomst(er) op");
								} else {
									player.sendMessage(ChatColor.GOLD +  target.getName() + ChatColor.AQUA + " har ikke samlet nogle blomster op");
							    }
								player.sendMessage(ChatColor.YELLOW  + LINJER + ChatColor.GOLD + " Levels " + ChatColor.YELLOW  + LINJER);
								if(leveltarget >= 1) {
									player.sendMessage(ChatColor.GOLD +  target.getName() + ChatColor.AQUA + " er i level " + ChatColor.GOLD + leveltarget + ChatColor.AQUA);
							    }
							    else {
							        player.sendMessage(ChatColor.GOLD +  target.getName() + ChatColor.AQUA + " er ikke i noget level!");
							    }
							}
				}
		}else if(!(sender instanceof Player)) {
			console.sendMessage(ChatColor.DARK_RED + "Kun spillere kan skrive denne kommando!");
		}
			
		return true;
	}
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		if (args.length == 1){
			List<String> arguments = new ArrayList<>();
			arguments.add("info");
			arguments.add("reload");
			arguments.add("help");
			arguments.add("top");
			return arguments;
		}
			
		if (args.length == 2 && args[0].equalsIgnoreCase("info")) {
				List<String> playerNames = new ArrayList<>();
				Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
				Bukkit.getServer().getOnlinePlayers().toArray(players);
				for (int i = 0; i < players.length; i++){
	            	playerNames.add(players[i].getName());
				}
				
				return playerNames;
			}
		return Collections.emptyList();
    }
}