package uhc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class UHCmain extends JavaPlugin {

	public static final String StatsBaseDirPath = "plugins/UHC/";

	public static final String CoinStatsFilePath = StatsBaseDirPath + "coin.yml";
	public static final String PlayerStatsFilePath = StatsBaseDirPath + "stats.yml";
	public static final String PlayerLocationFilePath = StatsBaseDirPath + "location.yml";

	public static final YamlConfiguration CoinStatsYaml = new YamlConfiguration();
	public static final YamlConfiguration PlayerStatsYaml = new YamlConfiguration();
	public static final YamlConfiguration LocationStatsYaml = new YamlConfiguration();

	public static String prefix = "§c[UHC] §r";

	public void onEnable() {
		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

		loadCoinStats();
		loadStats();
		loadLocation();
		saveCoinStats();
		saveStats();
		saveLocation();

		IronPack();
	}

	public void onDisable() {
		saveCoinStats();
		saveStats();
		saveLocation();
	}

	public static void loadCoinStats() {
		if (!(new File(StatsBaseDirPath)).exists()) {
			(new File(StatsBaseDirPath)).mkdir();
		}
		if (!(new File(CoinStatsFilePath)).exists()) {
			try {
				(new File(CoinStatsFilePath)).createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			CoinStatsYaml.load((new File(CoinStatsFilePath)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}

	public static void saveCoinStats() {
		if (!(new File(StatsBaseDirPath)).exists()) {
			(new File(StatsBaseDirPath)).mkdir();
		}
		if (!(new File(CoinStatsFilePath)).exists()) {
			try {
				(new File(CoinStatsFilePath)).createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			CoinStatsYaml.save(CoinStatsFilePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loadStats() {
		if (!(new File(StatsBaseDirPath)).exists()) {
			(new File(StatsBaseDirPath)).mkdir();
		}
		if (!(new File(PlayerStatsFilePath)).exists()) {
			try {
				(new File(PlayerStatsFilePath)).createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			PlayerStatsYaml.load((new File(PlayerStatsFilePath)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}

	public static void saveStats() {
		if (!(new File(StatsBaseDirPath)).exists()) {
			(new File(StatsBaseDirPath)).mkdir();
		}
		if (!(new File(PlayerStatsFilePath)).exists()) {
			try {
				(new File(PlayerStatsFilePath)).createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			PlayerStatsYaml.save(PlayerStatsFilePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loadLocation() {
		if (!(new File(StatsBaseDirPath)).exists()) {
			(new File(StatsBaseDirPath)).mkdir();
		}
		if (!(new File(PlayerLocationFilePath)).exists()) {
			try {
				(new File(PlayerLocationFilePath)).createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			LocationStatsYaml.load((new File(PlayerLocationFilePath)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}

	public static void saveLocation() {
		if (!(new File(StatsBaseDirPath)).exists()) {
			(new File(StatsBaseDirPath)).mkdir();
		}
		if (!(new File(PlayerLocationFilePath)).exists()) {
			try {
				(new File(PlayerLocationFilePath)).createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			LocationStatsYaml.save(PlayerLocationFilePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void IronPack() {
		ItemStack iron_ingot = new ItemStack(Material.IRON_INGOT, 10);
		ShapedRecipe iron_pack = new ShapedRecipe(new ItemStack(iron_ingot)).shape("III", "ICI", "III")
				.setIngredient('I', Material.IRON_ORE).setIngredient('C', Material.COAL);
		Bukkit.addRecipe(iron_pack);
	}
}