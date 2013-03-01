package com.github.thebiologist13.commands.spawners;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;

import com.github.thebiologist13.CustomSpawners;
import com.github.thebiologist13.SpawnableEntity;
import com.github.thebiologist13.Spawner;

public class ConvertCommand extends SpawnerCommand {

	public ConvertCommand(CustomSpawners plugin) {
		super(plugin);
	}

	public ConvertCommand(CustomSpawners plugin, String mainPerm) {
		super(plugin, mainPerm);
	}

	@Override
	public void run(Spawner spawner, CommandSender sender, String subCommand, String[] args) {
		
		String in = getValue(args, 0, "");
		
		if(in.equals("minecart")) {
			SpawnableEntity minecart = new SpawnableEntity(EntityType.MINECART, PLUGIN.getNextEntityId());
			minecart.setSpawnerData(spawner);
			
			CustomSpawners.entities.put(minecart.getId(), minecart);
			
			if(CONFIG.getBoolean("data.autosave") && CONFIG.getBoolean("data.saveOnCreate")) {
				PLUGIN.getFileManager().autosave(minecart);
			}
			
			PLUGIN.sendMessage(sender, ChatColor.GREEN + "Successfully converted spawner " + ChatColor.GOLD + 
					PLUGIN.getFriendlyName(spawner) + ChatColor.GREEN + " to a new minecart entity with ID number " + ChatColor.GOLD + 
					minecart.getId() + ChatColor.GREEN + "!");
			
			return;
		} else if(in.equals("falling_block") || in.equals("fallingblock")) {
			SpawnableEntity falling = new SpawnableEntity(EntityType.FALLING_BLOCK, PLUGIN.getNextEntityId());
			falling.setSpawnerData(spawner);
			
			CustomSpawners.entities.put(falling.getId(), falling);
			
			if(CONFIG.getBoolean("data.autosave") && CONFIG.getBoolean("data.saveOnCreate")) {
				PLUGIN.getFileManager().autosave(falling);
			}
			
			PLUGIN.sendMessage(sender, ChatColor.GREEN + "Successfully converted spawner " + ChatColor.GOLD + 
					PLUGIN.getFriendlyName(spawner) + ChatColor.GREEN + " to a new falling block entity with ID number " + ChatColor.GOLD + 
					falling.getId() + ChatColor.GREEN + "!");
			
		}
		
		try {
			CustomSpawners.getConverter().convert(spawner);
			PLUGIN.sendMessage(sender, ChatColor.GREEN + "Successfully converted spawner with ID " + ChatColor.GOLD + 
					PLUGIN.getFriendlyName(spawner) + ChatColor.GREEN + "!");
		} catch(Exception e) {
			e.printStackTrace();
			PLUGIN.sendMessage(sender, ChatColor.RED + "Could not convert spawner. " +
					"The entity might have been invalid, or an error may have occurred.");
		}
		
	}

}