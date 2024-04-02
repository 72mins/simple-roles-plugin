package com.simpleroles.simpleroles.Commands;

import com.simpleroles.simpleroles.sql.QueryManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RoleList implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            String[] roles = QueryManager.listRoles();

            player.sendMessage(ChatColor.GREEN + "The available roles are:");
            for (int i = 0; i < roles.length; i++) {
                player.sendMessage(ChatColor.AQUA + String.valueOf(i + 1) + ". " + ChatColor.RESET + roles[i]);
            }
        }

        return true;
    }
}
