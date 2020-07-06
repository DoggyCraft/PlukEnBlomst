package com.amqlie.plukenblomst;
 
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;

public class Level {
	static ConsoleCommandSender console = Bukkit.getConsoleSender();
	private static File file;
    private static FileConfiguration fileconfig;
 
    private Level() {
    	throw new IllegalStateException("Level class");
    }
    
    public static void setup(){
    	file = new File(Bukkit.getServer().getPluginManager().getPlugin("PlukEnBlomst").getDataFolder(), "level.yml");
 
        if (!file.exists()){
        	try{
        		file.createNewFile();
        	}catch (IOException e){
            	console.sendMessage("Kunne ikke lave filen");
            }
        }
        fileconfig = YamlConfiguration.loadConfiguration(file);
    }
 
    public static FileConfiguration get(){
        return fileconfig;
    }
 
    public static void save(){
        try{
        	fileconfig.save(file);
        }catch (IOException e){
        	console.sendMessage("Kunne ikke gemme fil");
        }
    }
 
    public static void reload(){
    	fileconfig = YamlConfiguration.loadConfiguration(file);
    }
}