package net.civicraft.cartSick;

import org.bukkit.plugin.java.JavaPlugin;

public final class CartSick extends JavaPlugin {
    public static CartSick instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        getCommand("cartsick").setExecutor(new Reload());
        Timer.startTimer();
    }

    public static CartSick getInstance() {
        return instance;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
