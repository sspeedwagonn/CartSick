package me.gon_bao.vomit;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("vomit") && commandSender.hasPermission("vomit.reload")) {
            Main.getPlugin().reloadConfig();
            Main.chance = Main.getPlugin().getConfig().getInt("Chance to puke (%)");
            Main.time = Main.getPlugin().getConfig().getInt("Time (seconds)");
            Main.messageenable = Main.getPlugin().getConfig().getBoolean("Message.Enable");
            Main.message = Main.getPlugin().getConfig().getString("Message.Start vomit");
            Main.messageend = Main.getPlugin().getConfig().getString("Message.Stop vomit");
            commandSender.sendMessage(ChatColor.GREEN + "[Vomit]" + ChatColor.RESET + " The config file has been reloaded");
            return true;
        }
        return false;
    }
}