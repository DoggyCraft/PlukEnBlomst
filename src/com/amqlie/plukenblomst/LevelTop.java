package com.amqlie.plukenblomst;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.bukkit.ChatColor;
import org.bukkit.configuration.MemorySection;

public class LevelTop {
	public String sortLevels(int i) {
		// temp storage map
		Map<String, Integer> map = new HashMap<String, Integer>();
		List<String> test = new ArrayList<String>();

		// add players and score to map
		for (String playerName : ((MemorySection) Level.get().get("Level.player.")).getKeys(false)){

			int level = Level.get().getInt("Level.player." + playerName);

			map.put(playerName, level);

		}
		int pos = 1;
		for (String playerName : map.keySet()) {
			
			int level = map.get(playerName);
			
			String test1 = ChatColor.GRAY + "[" + pos + "] " + ChatColor.WHITE + ChatColor.BOLD + playerName + ChatColor.GREEN + " " + level;
			test.add(test1);
			pos++;
		}

		// sort map and return it
		ValueComparator bvc = new ValueComparator(map);
		TreeMap<String, Integer> finalmap = new TreeMap<String, Integer>(bvc);
		finalmap.putAll(map);
		return test.get(i);
	}

	class ValueComparator implements Comparator<Object> {

		Map<String, Integer> base;

		public ValueComparator(Map<String, Integer> base) {
			this.base = base;
		}

		public int compare(Object a, Object b) {
			if ((Integer) base.get(a) < (Integer) base.get(b)) {
				return 1;
			} else if ((Integer) base.get(a) == (Integer) base.get(b)) {
				return 0;
			} else {
				return -1;
			}
		}
	}
}
