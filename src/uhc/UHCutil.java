package uhc;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import org.bukkit.entity.Player;

public class UHCutil {
	
	private static UHCmain plugin;

	public static void sendToServer(Player player, String server) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("Connect");
			out.writeUTF(server);
		} catch (Exception e) {
			e.printStackTrace();
		}
		player.sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
	}
}