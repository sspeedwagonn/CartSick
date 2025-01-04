package net.civicraft.cartSick;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Timer {
    static CartSick instance = CartSick.getInstance();
    static ArrayList<Player> active = new ArrayList<>();
    private static final HashMap<Player, Integer> ticks = new HashMap<>();

    static void startTimer() {
        instance.getServer().getScheduler().scheduleSyncRepeatingTask(instance, () -> {
            Iterator<Player> iterator = active.iterator();

            while (iterator.hasNext()) {
                Player player = iterator.next();
                if (ticks.containsKey(player)) {
                    if (ticks.get(player) >= (instance.getConfig().getInt("ticks") * 20) - 2) {
                        ticks.remove(player);
                        iterator.remove();
                        if (instance.getConfig().getBoolean("messages.enabled")) {
                            player.sendMessage(Component.text(instance.getConfig().getString("message.stop"), NamedTextColor.AQUA));
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
            }
        }, 0, 0);
    }
}
