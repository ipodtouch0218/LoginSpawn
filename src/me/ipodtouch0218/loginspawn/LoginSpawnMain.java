package me.ipodtouch0218.loginspawn;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class LoginSpawnMain extends JavaPlugin implements Listener {

	Location spawn;
	
	@Override
	public void onEnable() {
		spawn = deserializeLoc(getConfig().getConfigurationSection("spawn"));
		
		if (spawn == null) {
			spawn = new Location(Bukkit.getWorlds().get(0), 0, 64, 0);
		}
		
		Bukkit.getPluginManager().registerEvents(this,  this);
		
		getCommand("setspawn").setExecutor(new CommandSetSpawn(this));
		getCommand("spawn").setExecutor(new CommandSpawn(this));
	}
	
	@Override
	public void onDisable() {
		spawn.getWorld().setSpawnLocation(spawn.getBlockX(), spawn.getBlockY(), spawn.getBlockZ());
		saveLoc("spawn", spawn);
		saveConfig();
	}
	
	@EventHandler
	public void onLogin(PlayerJoinEvent pje) {
		if (pje.getPlayer().isOp() || pje.getPlayer().hasPermission("loginspawn.bypass")) return;
		pje.getPlayer().teleport(spawn);
	}
	
	
	
	private void saveLoc(String configLoc, Location loc) {
		ConfigurationSection section = getConfig().getConfigurationSection(configLoc);
		if (section == null) {
			section = getConfig().createSection(configLoc);
		}
		
		section.set("world", loc.getWorld().getName());
		section.set("x", loc.getX());
		section.set("y", loc.getY());
		section.set("z", loc.getZ());
		section.set("yaw", loc.getYaw());
		section.set("pitch", loc.getPitch());
		
		saveConfig();
	}
	
	private Location deserializeLoc(ConfigurationSection section) {
		if (section == null) return null;
		Location newLoc = new Location(Bukkit.getWorld(section.getString("world")), section.getDouble("x"), section.getDouble("y"),
				section.getDouble("z"));
		
		newLoc.setPitch((float) section.getDouble("pitch"));
		newLoc.setYaw((float) section.getDouble("yaw"));
		
		return newLoc;
	}
}
