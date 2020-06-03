package com.amqlie.plukenblomst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.configuration.MemorySection;

public class LevelTop {
	public String sortLevels(int a) {
		// temp storage map
		Map<String, Integer> map = new HashMap<String, Integer>();
		List<String> finalScore = new ArrayList<String>();

		// add players and score to map
		for (String playerName : ((MemorySection) Level.get().get("Level.player.")).getKeys(false)){

			int level = Level.get().getInt("Level.player." + playerName);

			map.put(playerName, level);

		}
		for (int i = 0; i < 15; i++) {

			String topName = "";
			int topScore = 0;

			for (String playerName : map.keySet()) {

				int score = map.get(playerName);

				if (score > topScore) {

					topName = playerName;
					topScore = score;

				}

			}

			if (!topName.equals("")) {

				map.remove(topName);

				int level = Level.get().getInt("Level.player." + topName);
				int position = i + 1;

				String finalString = ChatColor.GRAY + "[" + position + "] " + ChatColor.WHITE + ChatColor.BOLD + topName + ChatColor.GREEN + " " + level;

				finalScore.add(finalString);

			} else
				break;

		}
		
		return finalScore.get(a);
	}
}