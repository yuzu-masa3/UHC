package uhc;

import org.bukkit.configuration.file.YamlConfiguration;

public class GameManager {

	public static void Start(GameType type) {
		YamlConfiguration locyml = UHCmain.LocationStatsYaml;
		if (type.equals(GameType.NORMAL)) {
			
			/*
			for (String locations : locyml.getStringList("PlayerSpawns")) {
				String[] locsplit = locations.split(",");
				World w = Bukkit.getWorld(locsplit[0]);
				int x = Integer.parseInt(locsplit[1]);
				int y = Integer.parseInt(locsplit[2]);
				int z = Integer.parseInt(locsplit[3]);
				Location location = new Location(w, x, y, z);
				
			}
			*/
		} else if (type.equals(GameType.DEATHMATCH)) {
			for (String locations : locyml.getStringList("DeathMatchPlayerSpawns")) {

			}
		}
	}

	public static void End() {

	}

	public enum GameType {
		NORMAL, DEATHMATCH;
	}

}
