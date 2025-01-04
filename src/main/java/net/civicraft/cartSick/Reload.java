package net.civicraft.cartSick;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Reload implements CommandExecutor {
    static CartSick instance = CartSick.getInstance();
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player && player.hasPermission("cartsick.reload")) {
            instance.reloadConfig();
            return true;
        }
        return false;
    }
}
