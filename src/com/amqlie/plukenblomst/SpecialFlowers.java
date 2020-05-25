package com.amqlie.plukenblomst;

import java.security.SecureRandom;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;


public class SpecialFlowers extends JavaPlugin {
	PlukEnBlomst plugin;
	public static void loveFlower(Player player){
		SecureRandom rand = new SecureRandom();
		
		String[] loveFlowerMessages = {"Du er så smuk", "Jeg elsker dig", "Du fortjener alt i verdenen", "Hvordan kan man være så pæn?", "Du er en smuksak", "Man får helt lyst til at kysse dig", "Du er smækker", "Uha hvor er du flot i dag", "Hvaa? Skal du stikkes af min torn?", "Sikke en blomst", "Du ser blomster godt ud", "Du ligner en tulipan", "Den blomst klæder dig", "Hvor er det dog en flot blomst", "Du stråler som en solsikke", "Du er som en rose"};
	    int loveFlowerNumber = rand.nextInt(loveFlowerMessages.length);
        for(ItemStack item : player.getInventory().getContents()){
            if (item != null && item.getItemMeta().getDisplayName().contains("Flower of love")){
                    player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "[FLOWER OF LOVE] " + ChatColor.LIGHT_PURPLE + loveFlowerMessages[loveFlowerNumber]);
                    player.getWorld().playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 5, 3);
                    for(int i = 0; i < 50; i++) {
	                    player.getWorld().spawnParticle(Particle.HEART, player.getLocation(), 2, 1, 0, 1);
                }
            }
        }
     
    }
}
