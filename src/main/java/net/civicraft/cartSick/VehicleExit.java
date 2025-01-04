package net.civicraft.cartSick;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class VehicleExit implements Listener {
    static CartSick instance = CartSick.getInstance();
    @EventHandler
    public void onVehicleExit(VehicleExitEvent event) {
        if (!(event.getExited() instanceof Player)) return;

        Player player = (Player) event.getExited();
        if (event.getVehicle() instanceof Minecart && !Timer.active.contains(player) && player.hasPermission("cartsick.allow")) {
            int ticks = instance.getConfig().getInt("time") * 20;
            Random random = new Random();

            if (random.nextInt(100) <= instance.getConfig().getInt("chance")) {
                if (instance.getConfig().getBoolean("messages.enabled")) {
                    player.sendMessage(Component.text(instance.getConfig().getString("message.start"), NamedTextColor.AQUA));
                }

                player.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA, ticks + 80, 0));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, ticks, 4));
            }
        }
    }
}
