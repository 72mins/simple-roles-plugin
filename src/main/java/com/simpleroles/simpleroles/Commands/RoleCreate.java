package com.simpleroles.simpleroles.Commands;

import com.simpleroles.simpleroles.Main;
import com.simpleroles.simpleroles.sql.MySQL;
import com.simpleroles.simpleroles.utils.ColorPicker;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RoleCreate implements CommandExecutor {
    public static String roleName;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 0) {
                player.sendMessage("Usage: /rolecreate <name>");

                return true;
            } else if (args.length > 1) {
                player.sendMessage("Role name cannot contain spaces.");

                return true;
            }

            roleName = args[0];
            ColorPicker.openColorPicker(player, roleName);
        }

        return true;
    }

    public static String getRoleName() {
        return roleName;
    }
}
