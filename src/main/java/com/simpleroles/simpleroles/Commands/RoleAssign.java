package com.simpleroles.simpleroles.Commands;

import com.simpleroles.simpleroles.sql.QueryManager;
import com.simpleroles.simpleroles.utils.ScoreboardManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RoleAssign implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 0) {
                player.sendMessage("Usage: /roleassign <player> <role>");

                return true;
            } else if (args.length > 2) {
                player.sendMessage("Role name cannot contain spaces.");

                return true;
            }

            String targetArg = args[0];
            String roleName = args[1];

            Player target = player.getServer().getPlayer(targetArg);

            if (target == null) {
                player.sendMessage("Player " + targetArg + " not found.");

                return true;
            }

            String[] roleAndColor = QueryManager.getIdAndColor(player, roleName);

            int roleId = Integer.parseInt(roleAndColor[0]);
            String roleColor = roleAndColor[1];

            if (roleId == 0) {
                player.sendMessage("Role " + roleName + " does not exist.");

                return true;
            }

            QueryManager.updateRole(target, player, roleName, roleId);
            ScoreboardManager.updateScoreboard(target, roleName, roleColor);
        }

        return true;
    }
}
