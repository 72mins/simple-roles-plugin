package com.simpleroles.simpleroles.Listeners;

import com.simpleroles.simpleroles.sql.QueryManager;
import com.simpleroles.simpleroles.utils.ScoreboardManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class PlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        QueryManager.createPlayer(player);
        String[] roleAndColor = QueryManager.getRoleAndColor(player);

        ScoreboardManager.updateScoreboard(player, roleAndColor[0], roleAndColor[1]);
    }
}
