package me.gon_bao.vomit;

import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class Main extends JavaPlugin {

    private static Plugin plugin;
    static int chance;
    static int time;
    static boolean messageenable;
    static String message;
    static String messageend;

    private void loadConfiguration() {
        this.saveDefaultConfig();
        chance = this.getConfig().getInt("Chance to puke (%)");
        time = this.getConfig().getInt("Time (seconds)");
        messageenable = this.getConfig().getBoolean("Message.Enable");
        message = this.getConfig().getString("Message.Start vomit");
        messageend = this.getConfig().getString("Message.Stop vomit");
    }

    public void onEnable() {
        plugin = this;
        Bukkit.getServer().getPluginManager().registerEvents(new VehicleExit(), this);
        Objects.requireNonNull(Bukkit.getPluginCommand("vomit")).setExecutor(new Commands());
        loadConfiguration();
        new Metrics(this);
        VomitTimer.startTimer();
        System.out.println("[Vomit] has been enabled");
    }

    public void onDisable() {
        plugin = null;
        System.out.println("[Vomit] Vomit has been disabled");
    }

    static Plugin getPlugin() {
        return plugin;
    }
}