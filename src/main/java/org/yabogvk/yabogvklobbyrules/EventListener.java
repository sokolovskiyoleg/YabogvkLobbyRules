package org.yabogvk.yabogvklobbyrules;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.File;

public class EventListener implements Listener {
    private YabogvkLobbyRules plugin;

    public EventListener(YabogvkLobbyRules plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        File verifiedFile = new File(plugin.getDataFolder(), "verified.yml");
        YamlConfiguration yamlConfig = YamlConfiguration.loadConfiguration(verifiedFile);

        if (!yamlConfig.contains(player.getName())) {
            player.setWalkSpeed(0);
            player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 128));
            player.sendMessage(MessageUtils.getMessage("messages.confirm-rules"));
        }
    }
}
