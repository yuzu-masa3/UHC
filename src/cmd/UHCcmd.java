package cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
						} else {
							sender.sendMessage(notPlayer);
							sender.sendMessage(notPlayer);
						}
					} else {
					}
				} else {
				}
			}
		}
		return true;
	}
}