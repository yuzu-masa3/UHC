package scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class GameScoreboard {
	
	private static int count = 0;
	
	public static Scoreboard getScoreBoard() {
		Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		return scoreboard;
	}

	public static Objective getObjective() {
		Objective obj = getScoreBoard().getObjective("Sidebar");
		if (obj == null) {
			obj = getScoreBoard().registerNewObjective("Sidebar", "dummy");
			obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		}
		return obj;
	}

	public static void SidebarCountdown() {
		getObjective().getScoreboard().resetScores("count: " + (count - 1) + "/ 3600");
		getObjective().getScoreboard().resetScores("count: " + (count + 1) + "/ 3600");
		getObjective().getScore("count: " + count + "/ 3600");
	}
	
	public static void setCount(int counts) {
		count = counts;
	}
	
	public static int getCount() {
		return count;
	}
	
}
