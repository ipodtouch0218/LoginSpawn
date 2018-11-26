package me.ipodtouch0218.loginspawn;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSetSpawn implements CommandExecutor {

	private LoginSpawnMain main;
	public CommandSetSpawn(LoginSpawnMain main) {
		this.main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only a player can use this command, duh!");
			return true;
		}

		main.spawn = ((Player) sender).getLocation();
		sender.sendMessage(color("&e[LS] &7Set the world's spawnpoint to &f" + main.spawn.getX() + ", " + main.spawn.getY() + ", " + main.spawn.getZ()));
		
		return true;
	}
	
	private String color(String msg) {
		return ChatColor.translateAlternateColorCodes('&', msg);
	}
}
