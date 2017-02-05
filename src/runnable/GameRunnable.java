package runnable;

import org.bukkit.scheduler.BukkitRunnable;

import listener.UHClistener;

public class GameRunnable extends BukkitRunnable {
	
	private int count = 0;
	
	public void run() {
		count++;
		if(count == 600) {
			//10分
			UHClistener.CanDamage = true;
			cancel();
		}
		
	}

}
