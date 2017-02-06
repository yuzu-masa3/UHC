package uhc;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import runnable.Game10minRunnable;

public class GameManager {

	private static Main plugin;

	public static boolean preparatory = true;
	public static boolean ingame = false;
	public static ArrayList<Player> ingame_players = new ArrayList<Player>();

	public static void Start(GameType type) {
		YamlConfiguration locyml = Main.LocationStatsYaml;
		if (type.equals(GameType.NORMAL)) {
			preparatory = true;
			ingame = true;
			for (Player players : Bukkit.getOnlinePlayers()) {
				ingame_players.add(players);
			}
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

			//10分間のカウントダウンスタート
			new Game10minRunnable().runTaskTimer(plugin, 20, 20);

		} else if (type.equals(GameType.DEATHMATCH)) {
			for (String locations : locyml.getStringList("DeathMatchPlayerSpawns")) {

			}
		}
	}

	public static void End() {
		ingame = false;
		preparatory = true;
		ingame_players.clear();

	}

	public enum GameType {
		NORMAL, DEATHMATCH;
	}

}
