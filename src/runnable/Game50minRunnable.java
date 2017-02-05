package runnable;

import org.bukkit.scheduler.BukkitRunnable;

import uhc.GameManager;

public class Game50minRunnable extends BukkitRunnable {

	private int count = 0;

	public void run() {
		count++;
		if (GameManager.ingame) {
			//50分なら
			if (count == 3000) {
				if(GameManager.ingame_players.size() >= 2) {
					
				}
			}
		} else {
			cancel();
		}
	}

}
