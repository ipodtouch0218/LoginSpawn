package me.ipodtouch0218.loginspawn;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSpawn implements CommandExecutor {

	private LoginSpawnMain main;
	public CommandSpawn(LoginSpawnMain main) {
		this.main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only a player can use this command, duh!");
			return true;
		}
		if (main.spawn == null) {
			sender.sendMessage(ChatColor.RED + "The operators have not set a spawn for the world!");
			return true;
		}
		
		((Player) sender).teleport(main.spawn);

		return true;
	}

}
