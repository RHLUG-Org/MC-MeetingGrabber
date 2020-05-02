package com.jasomo;

import com.jasomo.commands.Meeting;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MeetingGrabber extends JavaPlugin {
    private static MeetingGrabber instance;
    private static PluginManager pm;
    private static FileConfiguration config;

    @Override
    public void onEnable() {
        instance = this;
        pm = Bukkit.getServer().getPluginManager();
        config = this.getConfig();
        saveDefaultConfig();
        this.getCommand("meeting").setExecutor(new Meeting(this));
    }

    @Override
    public void onDisable() {
        instance = null;
    }
}
