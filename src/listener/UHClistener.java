package listener;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import uhc.UHCmain;
import uhc.UHCutil;

public class UHClistener implements Listener {
	
	private static UHCmain plugin;

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();

		event.setJoinMessage(null);
		
		player.setHealth(20);
		player.setMaxHealth(20);
		player.setGameMode(GameMode.ADVENTURE);
		for (Player players : Bukkit.getOnlinePlayers()) {
			int i = Bukkit.getOnlinePlayers().size();
			players.sendMessage("§c" + player.getName() + " §ehas joined §a" + i + "§7/§a20");
		}
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();

		event.setQuitMessage(null);
		
		player.setHealth(20);
		player.setMaxHealth(20);
		player.setGameMode(GameMode.ADVENTURE);
		for (Player players : Bukkit.getOnlinePlayers()) {
			int i = Bukkit.getOnlinePlayers().size();
			players.sendMessage("§c" + player.getName() + " §ehas quit §a" + i + "§7/§a20");
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (player.getItemInHand().getType().equals(Material.BED)) {
			if (player.getItemInHand().getItemMeta().getDisplayName() != null) {
				if (player.getItemInHand().getItemMeta().getDisplayName().equals("§cReturn to lobby")) {
					player.sendMessage(UHCmain.prefix + "§aTeleporting you to the lobby in 5 seconds.");
					Bukkit.getServer().getScheduler().runTaskLater(plugin, new Runnable() {
						public void run() {
							UHCutil.sendToServer(player, "Lobby");
						}
					}, 100);
				}
			}
		}
	}
	
	public void onAAA() {
		
	}
}