package org.yabogvk.yabogvklobbyrules;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Commands implements CommandExecutor {

    private YabogvkLobbyRules plugin;

    public Commands(YabogvkLobbyRules plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(MessageUtils.getMessage("messages.no-player"));
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            List<String> rules = plugin.getConfig().getStringList("rules");
            for (String rule : rules) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', rule));
            }
            return true;
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("accept")) {
            File verifiedFile = new File(plugin.getDataFolder(), "verified.yml");
            YamlConfiguration yamlConfig = YamlConfiguration.loadConfiguration(verifiedFile);

            yamlConfig.set(player.getName(), true);
            try {
                yamlConfig.save(verifiedFile);
                sender.sendMessage(MessageUtils.getMessage("messages.rules-accepted"));
                player.setWalkSpeed(0.2f);
                player.removePotionEffect(PotionEffectType.JUMP);
            } catch (IOException e) {
                sender.sendMessage(MessageUtils.getMessage("messages.save-error"));
                e.printStackTrace();
            }
            return true;
        }

        sender.sendMessage(MessageUtils.getMessage("messages.usage"));
        return true;
    }
}

