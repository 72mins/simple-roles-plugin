package com.simpleroles.simpleroles.Listeners;

import com.simpleroles.simpleroles.Commands.RoleCreate;
import com.simpleroles.simpleroles.sql.QueryManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuHandler implements Listener {
    @EventHandler
    public void onMenuClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();

        if (e.getView().getTitle().equals(ChatColor.BLUE + "Role Color Picker")) {
            e.setCancelled(true);

            if (e.getCurrentItem() == null) {
                return;
            }

            String roleName = RoleCreate.getRoleName();
            String chosenColor;

            switch (e.getCurrentItem().getType()) {
                case BLACK_DYE:
                    chosenColor = String.valueOf(ChatColor.BLACK.getChar());
                    break;
                case RED_DYE:
                    chosenColor = String.valueOf(ChatColor.RED.getChar());
                    break;
                case GREEN_DYE:
                    chosenColor = String.valueOf(ChatColor.DARK_GREEN.getChar());
                    break;
                case YELLOW_DYE:
                    chosenColor = String.valueOf(ChatColor.YELLOW.getChar());
                    break;
                case BLUE_DYE:
                    chosenColor = String.valueOf(ChatColor.DARK_BLUE.getChar());
                    break;
                case PURPLE_DYE:
                    chosenColor = String.valueOf(ChatColor.LIGHT_PURPLE.getChar());
                    break;
                case CYAN_DYE:
                    chosenColor = String.valueOf(ChatColor.AQUA.getChar());
                    break;
                case WHITE_DYE:
                    chosenColor = String.valueOf(ChatColor.WHITE.getChar());
                    break;
                case ORANGE_DYE:
                    chosenColor = String.valueOf(ChatColor.GOLD.getChar());
                    break;
                case MAGENTA_DYE:
                    chosenColor = String.valueOf(ChatColor.DARK_PURPLE.getChar());
                    break;
                case LIGHT_BLUE_DYE:
                    chosenColor = String.valueOf(ChatColor.BLUE.getChar());
                    break;
                case LIME_DYE:
                    chosenColor = String.valueOf(ChatColor.GREEN.getChar());
                    break;
                case RED_BANNER:
                    chosenColor = String.valueOf(ChatColor.DARK_RED.getChar());
                    break;
                default:
                    return;
            }

            QueryManager.createNewRole(player, roleName, chosenColor);

            player.closeInventory();
        }
    }
}
