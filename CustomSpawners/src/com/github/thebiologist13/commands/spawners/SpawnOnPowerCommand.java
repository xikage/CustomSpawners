package com.github.thebiologist13.commands.spawners;

import org.bukkit.command.CommandSender;

import com.github.thebiologist13.CustomSpawners;
import com.github.thebiologist13.Spawner;

public class SpawnOnPowerCommand extends SpawnerCommand {

	public SpawnOnPowerCommand(CustomSpawners plugin) {
		super(plugin);
	}

	public SpawnOnPowerCommand(CustomSpawners plugin, String mainPerm) {
		super(plugin, mainPerm);
	}

	@Override
	public void run(Spawner spawner, CommandSender sender, String subCommand, String[] args) {
		String in = getValue(args, 0, "false");
		spawner.setSpawnOnRedstone(Boolean.parseBoolean(in));
		
		PLUGIN.sendMessage(sender, getSuccessMessage(spawner, "spawn on redstone trigger", in));
	}

}
