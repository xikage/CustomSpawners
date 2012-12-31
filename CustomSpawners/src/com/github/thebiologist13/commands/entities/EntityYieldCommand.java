package com.github.thebiologist13.commands.entities;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import com.github.thebiologist13.CustomSpawners;
import com.github.thebiologist13.SpawnableEntity;

public class EntityYieldCommand extends EntityCommand {

	public EntityYieldCommand(CustomSpawners plugin) {
		super(plugin);
	}

	public EntityYieldCommand(CustomSpawners plugin, String mainPerm) {
		super(plugin, mainPerm);
	}

	@Override
	public void run(SpawnableEntity entity, CommandSender sender, String subCommand, String[] args) {
		
		String in = getValue(args, 0, "1.0");
		
		if(!CustomSpawners.isFloat(in)) {
			PLUGIN.sendMessage(sender, ChatColor.RED + "The yield must be a float.");
			return;
		}
		
		entity.setYield(Float.parseFloat(in));
		
		PLUGIN.sendMessage(sender, getSuccessMessage(entity, "yield", in));
		
	}

}
