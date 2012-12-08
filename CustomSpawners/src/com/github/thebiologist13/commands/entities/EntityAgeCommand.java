package com.github.thebiologist13.commands.entities;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.thebiologist13.CustomSpawners;
import com.github.thebiologist13.SpawnableEntity;
import com.github.thebiologist13.commands.SpawnerCommand;

public class EntityAgeCommand extends SpawnerCommand {

	public EntityAgeCommand(CustomSpawners plugin) {
		super(plugin);
	}

	@Override
	public void run(CommandSender arg0, Command arg1, String arg2, String[] arg3) {

		//Command Syntax = /entities setage [id] <age>
		//Array Index with selection    0          1
		//Without selection             0    1     2
		
		//Player
		Player p = null;
		//Entity
		SpawnableEntity s = null;
		//Permissions
		String perm = "customspawners.entities.setage";

		if(!(arg0 instanceof Player)) {
			log.info(NO_CONSOLE);
			return;
		}

		p = (Player) arg0;

		if(p.hasPermission(perm)) {
			if(CustomSpawners.entitySelection.containsKey(p) && arg3.length == 2) {

				s = CustomSpawners.getEntity(CustomSpawners.entitySelection.get(p).toString());
				
				if(arg3[1].equalsIgnoreCase("ADULT")) {
					s.setAge(-1);
				} else if(arg3[1].equalsIgnoreCase("BABY")) {
					s.setAge(-2);
				} else {
					if(CustomSpawners.isInteger(arg3[1])) {
						s.setAge(Integer.parseInt(arg3[1]));
					} else {
						p.sendMessage(ChatColor.RED + "Age value must be an integer, \"ADULT\", or \"BABY\".");
						return;
					}
				}
				
				//Success
				p.sendMessage(ChatColor.GREEN + "Successfully set the age of spawnable entity with ID " 
						+ ChatColor.GOLD + s.getId() + ChatColor.GREEN + " to " + ChatColor.GOLD 
						+ arg3[1] + ChatColor.GREEN + "!");
				
			} else if(arg3.length == 2) {
				p.sendMessage(NEEDS_SELECTION);
				return;
			} else if(arg3.length == 3) {

				s = CustomSpawners.getEntity(arg3[1]);

				if(s == null) {
					p.sendMessage(NO_ID);
					return;
				}
				
				if(arg3[2].equalsIgnoreCase("ADULT")) {
					s.setAge(-1);
				} else if(arg3[2].equalsIgnoreCase("BABY")) {
					s.setAge(-2);
				} else {
					if(CustomSpawners.isInteger(arg3[2])) {
						s.setAge(Integer.parseInt(arg3[2]));
					} else {
						p.sendMessage(ChatColor.RED + "Age value must be an integer, \"ADULT\", or \"BABY\".");
						return;
					}
				}
				
				//Success
				p.sendMessage(ChatColor.GREEN + "Successfully set the age of spawnable entity with ID " 
						+ ChatColor.GOLD + plugin.getFriendlyName(s) + ChatColor.GREEN + " to " + ChatColor.GOLD 
						+ arg3[2] + ChatColor.GREEN + "!");
				
			} else {
				p.sendMessage(GENERAL_ERROR);
				return;
			}

		} else {
			p.sendMessage(NO_PERMISSION);
			return;
		}

	}

}
