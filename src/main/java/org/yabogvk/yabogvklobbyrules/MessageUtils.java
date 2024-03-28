package org.yabogvk.yabogvklobbyrules;

import org.bukkit.ChatColor;
import org.yabogvk.yabogvklobbyrules.YabogvkLobbyRules;

public class MessageUtils {

    private static YabogvkLobbyRules plugin;

    public static void init(YabogvkLobbyRules plugin) {
        MessageUtils.plugin = plugin;
    }

    public static String getMessage(String path) {
        String message = plugin.getConfig().getString(path);
        if (message == null) {
            plugin.getLogger().warning("Отсутствует сообщение для пути: " + path);
            return "Cообщение отсутствует в конфиге";
        }
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
