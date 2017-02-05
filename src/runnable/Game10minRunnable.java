package runnable;

import org.bukkit.scheduler.BukkitRunnable;

import scoreboard.UHCscoreboard;
import uhc.GameManager;

public class Game10minRunnable extends BukkitRunnable {

	private int count = 0;

	public void run() {
		count++;
		UHCscoreboard.setCount(UHCscoreboard.getCount() + 1);
		if (count == 600) {
			//10分
			GameManager.preparatory = false;
			new Game50minRunnable();
			cancel();
		}

	}

}
