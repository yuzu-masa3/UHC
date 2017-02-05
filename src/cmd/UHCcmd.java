package cmd;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import uhc.UHCmain;

public class UHCcmd implements CommandExecutor {

	private final String notPlayer = ChatColor.translateAlternateColorCodes('&', "&cYou are not Player!!");

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("uhc")) {
			if (args.length == 0) {
			} else {
				if (args[0].equalsIgnoreCase("set")) {
					if (args[1].equalsIgnoreCase("ps")) {
						if (sender instanceof Player) {
							YamlConfiguration yml = UHCmain.LocationStatsYaml;
							ArrayList<String> list = new ArrayList<String>();
							Player player = (Player) sender;
							int x = (int) player.getLocation().getX();
							int y = (int) player.getLocation().getY();
							int z = (int) player.getLocation().getZ();
							String w = player.getWorld().getName();
							String loc = w + "," + x + "," + y + "," + z;
							list.add(loc);
							for (String str : yml.getStringList("PlayerSpawns")) {
								list.add(str);
							}
							yml.set("PlayerSpawns", list);
							try {
								yml.save(new File(UHCmain.PlayerLocationFilePath));
								sender.sendMessage("PS: " + loc);
							} catch (IOException e) {
								e.printStackTrace();
							}
						} else {
							sender.sendMessage(notPlayer);
						}
					} else if (args[1].equalsIgnoreCase("dps")) {
						if (sender instanceof Player) {
							YamlConfiguration yml = UHCmain.LocationStatsYaml;
							ArrayList<String> list = new ArrayList<String>();
							Player player = (Player) sender;
							int x = (int) player.getLocation().getX();
							int y = (int) player.getLocation().getY();
							int z = (int) player.getLocation().getZ();
							String w = player.getWorld().getName();
							String loc = w + "," + x + "," + y + "," + z;
							list.add(loc);
							for (String str : yml.getStringList("DeathMatchPlayerSpawns")) {
								list.add(str);
							}
							yml.set("DeathMatchPlayerSpawns", list);
							try {
								yml.save(new File(UHCmain.PlayerLocationFilePath));
								sender.sendMessage("DPS: " + loc);
							} catch (IOException e) {
								e.printStackTrace();
							}
						} else {
							sender.sendMessage(notPlayer);
						}
					}
				} else {
				}
			}
		}
		return true;
	}
}