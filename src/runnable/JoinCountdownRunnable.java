package runnable;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import uhc.GameManager;

public class JoinCountdownRunnable extends BukkitRunnable {

	private int count = 0;

	public void run() {
		count++;
		//20秒たった
		if (Bukkit.getOnlinePlayers().size() >= 20) {
			if (count == 20) {
				GameManager.Start(GameManager.GameType.NORMAL);
				cancel();
			} else {
				Bukkit.broadcastMessage((20 - count) + "/20秒");
			}
		} else {
			//カウントダウン中にOnlinePlayersが20人未満になったら
		}
	}

}
