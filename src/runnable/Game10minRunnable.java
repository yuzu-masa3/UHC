package runnable;

import org.bukkit.scheduler.BukkitRunnable;

import uhc.GameManager;

public class Game10minRunnable extends BukkitRunnable {

	private int count = 0;

	public void run() {
		count++;
		if (count == 600) {
			//10分
			GameManager.preparatory = false;
			new Game50minRunnable();
			cancel();
		}

	}

}
