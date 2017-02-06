package runnable;

import org.bukkit.scheduler.BukkitRunnable;

public class DeathMatch15minRunnable extends BukkitRunnable {

	private int count = 0;

	public void run() {
		count++;
		//15分なら
		if (count == 900) {

		}
	}

}
