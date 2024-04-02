package com.simpleroles.simpleroles.Commands;

import com.simpleroles.simpleroles.sql.QueryManager;
import com.simpleroles.simpleroles.utils.ScoreboardManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RoleRemove implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 0) {
                player.sendMessage("Usage: /roleremove <player> here");

                return true;
            } else if (args.length > 1) {
                player.sendMessage("Player name cannot contain spaces.");

                return true;
            }

            Player target = player.getServer().getPlayer(args[0]);

            if (target == null) {
                player.sendMessage("Player " + args[0] + " not found.");

                return true;
            }

            QueryManager.clearRole(player, target);
            ScoreboardManager.updateScoreboard(target, "Default", "f");
        }

        return true;
    }
}
