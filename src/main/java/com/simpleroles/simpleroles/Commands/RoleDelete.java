package com.simpleroles.simpleroles.Commands;

import com.simpleroles.simpleroles.sql.QueryManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RoleDelete implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 0) {
                player.sendMessage("Usage: /roledelete <name>");

                return true;
            } else if (args.length > 1) {
                player.sendMessage("Role name cannot contain spaces.");

                return true;
            }

            String roleName = args[0];
            QueryManager.deleteRole(player, roleName);
        }

        return true;
    }
}
