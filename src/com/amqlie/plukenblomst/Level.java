package com.amqlie.plukenblomst;
 
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
 
import java.io.File;
import java.io.IOException;

public class Level {
	
	private static File file;
    private static FileConfiguration level;
 
    private Level() {
    	throw new IllegalStateException("Level class");
    }
    
    public static void setup(){
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("PlukEnBlomst").getDataFolder(), "level.yml");
 
        if (!file.exists()){
        	try{
            	file.createNewFile();
            	  if (!file.createNewFile()) {
            		  System.out.println("Kunne ikke lave filen");
            		  }
            }catch (IOException e){
            	System.out.println("Kunne ikke lave filen");
            }
        }
        level = YamlConfiguration.loadConfiguration(file);
    }
 
    public static FileConfiguration get(){
        return level;
    }
 
    public static void save(){
        try{
            level.save(file);
        }catch (IOException e){
            System.out.println("Kunne ikke gemme fil");
        }
    }
 
    public static void reload(){
        level = YamlConfiguration.loadConfiguration(file);
    }
}
