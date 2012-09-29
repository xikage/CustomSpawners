package com.github.thebiologist13.commands.entities;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.thebiologist13.CustomSpawners;
import com.github.thebiologist13.SpawnableEntity;
import com.github.thebiologist13.commands.SpawnerCommand;

public class EntityFuseCommand extends SpawnerCommand {

	public EntityFuseCommand(CustomSpawners plugin) {
		super(plugin);
	}
	
	@Override
	public void run(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		//Player
		Player p = null;
		//SpawnableEntity
		SpawnableEntity s = null;
		//Perm
		String perm = "customspawners.entities.setfuselength";
		//Fuse tick length
		int fuse = 0;

		final String MUST_BE_INTEGER = ChatColor.RED + "The fuse length must be an integer.";

		if(!(arg0 instanceof Player)) {
			log.info(NO_CONSOLE);
			return;
		}

		p = (Player) arg0;

		if(p.hasPermission(perm)) {
			if(CustomSpawners.entitySelection.containsKey(p) && arg3.length == 2) {

				s = plugin.getEntity(CustomSpawners.entitySelection.get(p).toString());

				if(!plugin.isInteger(arg3[1])) {
					p.sendMessage(MUST_BE_INTEGER);
					return;
				}

				fuse = Integer.parseInt(arg3[1]);

			} else if(arg3.length == 2) {
				p.sendMessage(NEEDS_SELECTION);
				return;
			} else if(arg3.length == 3) {

				s = plugin.getEntity(arg3[1]);

				if(s == null) {
					p.sendMessage(NO_ID);
					return;
				}

				if(!plugin.isInteger(arg3[2])) {
					p.sendMessage(MUST_BE_INTEGER);
					return;
				}

				fuse = Integer.parseInt(arg3[2]);

			} else {
				p.sendMessage(GENERAL_ERROR);
				return;
			}

			//Set
			s.setFuseTicks(fuse);

			//Success
			p.sendMessage(ChatColor.GREEN + "Successfully set the length of fuse on spawnable entity with ID " 
					+ ChatColor.GOLD + plugin.getFriendlyName(s) + ChatColor.GREEN + " to " + ChatColor.GOLD 
					+ plugin.convertTicksToTime(fuse) + ChatColor.GREEN + "!");
		} else {
			p.sendMessage(NO_PERMISSION);
			return;
		}
		
	}

}