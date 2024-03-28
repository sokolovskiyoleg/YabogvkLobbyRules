package org.yabogvk.yabogvklobbyrules;

import org.bukkit.plugin.java.JavaPlugin;

public final class YabogvkLobbyRules extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getCommand("rules").setExecutor(new Commands(this));
        getServer().getPluginManager().registerEvents(new EventListener(this), this);
        saveDefaultConfig();
        MessageUtils.init(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

