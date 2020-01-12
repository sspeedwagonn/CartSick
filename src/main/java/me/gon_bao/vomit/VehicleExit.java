package me.gon_bao.vomit;

import org.bukkit.ChatColor;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class VehicleExit implements Listener {

    @EventHandler
    public void onVehicleExit(VehicleExitEvent event) {
        if (!(event.getExited() instanceof Player)) return;

        Player player = (Player) event.getExited();

        if (event.getVehicle() instanceof Minecart && !VomitTimer.active.contains(player) && player.hasPermission("vomit.allow")) {
            int ticks = Main.time * 20;
            Random random = new Random();

            if (random.nextInt(100) <= Main.chance) {
                if (Main.messageenable) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.message));
                }

                player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, ticks + 80, 0));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, ticks, 4));

                VomitTimer.active.add(player);
            }
        }
    }
}