package me.Gon_Bao.Vomit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

class VomitTimer {

    static ArrayList<Player> active = new ArrayList<>();
    private static HashMap<Player, Integer> ticks = new HashMap<>();

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
                player.getWorld().spawnParticle(Particle.SLIME,
                        player.getLocation().getX(),
                        player.getLocation().getY() + 1.5,
                        player.getLocation().getZ(),
                        50,
                        0,
                        0,
                        0);
            }
        }, 0, 0);
    }
}