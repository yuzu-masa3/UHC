package listener;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import uhc.UHCmain;
import uhc.UHCutil;

public class UHClistener implements Listener {

	public static List<String> ingame = new ArrayList<String>();

	private static UHCmain plugin;

	public static boolean CanDamage = false;

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();

		UHCmain.loadCoinStats();
		UHCmain.loadStats();
		if (!(UHCmain.CoinStatsYaml.isSet("Stats." + player.getUniqueId().toString() + ".Coins"))) {
			UHCmain.CoinStatsYaml.set("Stats." + player.getUniqueId().toString() + ".Coins", 0);
		}
		if (!(UHCmain.PlayerStatsYaml.isSet("Stats." + player.getUniqueId().toString() + ".Kills"))) {
			UHCmain.PlayerStatsYaml.set("Stats." + player.getUniqueId().toString() + ".Kills", 0);
		}
		if (!(UHCmain.PlayerStatsYaml.isSet("Stats." + player.getUniqueId().toString() + ".Deaths"))) {
			UHCmain.PlayerStatsYaml.set("Stats." + player.getUniqueId().toString() + ".Deaths", 0);
		}
		if (!(UHCmain.PlayerStatsYaml.isSet("Stats." + player.getUniqueId().toString() + ".Score"))) {
			UHCmain.PlayerStatsYaml.set("Stats." + player.getUniqueId().toString() + ".Score", 0);
		}
		UHCmain.saveCoinStats();
		UHCmain.saveStats();

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
	public void onEntityDamage(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			if (!CanDamage) {
				event.setCancelled(true);
			}
		}

	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		if ((event.getEntity() instanceof Player) || (event.getEntity().getKiller() instanceof Player)) {
			Player def = event.getEntity();
			Player att = def.getKiller();
			Location loc = def.getLocation();
			if (def.getKiller() == null) {
				return;
			}
			try {
				if (def.getLastDamageCause().getEntityType().equals(EntityType.PLAYER)) {
					if (ingame.contains(def.getUniqueId().toString())
							&& (ingame.contains(att.getUniqueId().toString()))) {
						int CurrentKillCount = UHCmain.PlayerStatsYaml
								.getInt("Stats." + att.getUniqueId().toString() + ".Kills");
						int CurrentDeathCount = UHCmain.PlayerStatsYaml
								.getInt("Stats." + def.getUniqueId().toString() + ".Deaths");
						int CurrentScoreCount = UHCmain.PlayerStatsYaml
								.getInt("Stats." + att.getUniqueId().toString() + ".Score");

						ItemStack bed = new ItemStack(Material.BED);
						ItemMeta bedmeta = bed.getItemMeta();

						bedmeta.setDisplayName("§cReturn to lobby");
						bed.setItemMeta(bedmeta);

						ItemStack head = new ItemStack(Material.SKULL_ITEM);
						SkullMeta headmeta = (SkullMeta) head.getItemMeta();

						head.setDurability((short) 3);
						headmeta.setOwner(def.getName());
						headmeta.setDisplayName("§c" + def.getName() + "'s Head.");
						head.setItemMeta(headmeta);

						UHCmain.loadStats();
						UHCmain.PlayerStatsYaml.set("Stats." + att.getUniqueId().toString() + ".Kills",
								CurrentKillCount + 1);
						UHCmain.PlayerStatsYaml.set("Stats." + def.getUniqueId().toString() + ".Deaths",
								CurrentDeathCount + 1);
						UHCmain.PlayerStatsYaml.set("Stats." + att.getUniqueId().toString() + ".Score",
								CurrentScoreCount + 1);
						UHCmain.saveStats();

						loc.getWorld().dropItem(loc, new ItemStack(head));

						def.getInventory().addItem(bed);
						def.setGameMode(GameMode.ADVENTURE);
						def.setAllowFlight(true);

						if (ingame.contains(def.getUniqueId().toString())) {
							ingame.remove(def.getUniqueId().toString());
						}

						for (Player players : Bukkit.getOnlinePlayers()) {
							def.hidePlayer(players);
						}
					}
				}
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
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
							UHCutil.sendToServer(player, "lobby");
						}
					}, 100);
				}
			}
		}
	}
}