package com.simpleroles.simpleroles.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ScoreboardManager {
    public static void updateScoreboard(Player player, String roleName, String roleColor) {
        Scoreboard sb = Bukkit.getScoreboardManager().getMainScoreboard();
        Team team = sb.getTeam(player.getDisplayName());

        if (team == null) {
            team = sb.registerNewTeam(player.getDisplayName());
        }

        team.setPrefix(ChatColor.getByChar(roleColor) + "[" + roleName + "] ");

        if (!team.hasPlayer(player)) {
            team.addPlayer(player);
        }

        player.setScoreboard(sb);
    }
}
