package me.gon_bao.vomit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

class VomitTimer {

    static ArrayList<Player> active = new ArrayList<>();
    private static final HashMap<Player, Integer> ticks = new HashMap<>();

    static void startTimer() {
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), () -> {

            Iterator<Player> iterator = active.iterator();

            while (iterator.hasNext()) {
                Player player = iterator.next();

                if (ticks.containsKey(player)) {
                    if (ticks.get(player) >= (Main.time * 20) - 2) {
                        ticks.remove(player);
                        iterator.remove();
                        if (Main.messageenable) {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.messageend));
                        }
                    } else {
                        ticks.put(player, ticks.get(player) + 1);
                    }
                } else {
                    ticks.put(player, 0);
                }
                Location playerLocation = player.getLocation();
                Location spawnParticleLocation = new Location(playerLocation.getWorld(), playerLocation.getX(), playerLocation.getY() + 1.5, playerLocation.getZ());
                player.getWorld().spawnParticle(Particle.ITEM_SLIME, spawnParticleLocation, 50);
            }
        }, 0, 0);
    }
}