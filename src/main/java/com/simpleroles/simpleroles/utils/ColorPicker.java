package com.simpleroles.simpleroles.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ColorPicker {

    private static void addItem(Inventory inv, Material material, String displayName, String roleName, ChatColor color) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayName);

        List<String> lore = new ArrayList<>();
        lore.add("Assign the color " + displayName.toLowerCase() + " to the role");
        lore.add("Example: " + color + "[" + roleName + "] " + ChatColor.WHITE + "Player");
        itemMeta.setLore(lore);

        itemStack.setItemMeta(itemMeta);
        inv.addItem(itemStack);
    }

    public static void openColorPicker(Player player, String roleName) {
        Inventory inv = Bukkit.createInventory(player, 18, ChatColor.BLUE + "Role Color Picker");

        addItem(inv, Material.BLACK_DYE, "Black", roleName, ChatColor.BLACK);
        addItem(inv, Material.RED_DYE, "Red", roleName, ChatColor.RED);
        addItem(inv, Material.GREEN_DYE, "Green", roleName, ChatColor.DARK_GREEN);
        addItem(inv, Material.YELLOW_DYE, "Yellow", roleName, ChatColor.YELLOW);
        addItem(inv, Material.BLUE_DYE, "Blue", roleName, ChatColor.DARK_BLUE);
        addItem(inv, Material.PURPLE_DYE, "Purple", roleName, ChatColor.LIGHT_PURPLE);
        addItem(inv, Material.CYAN_DYE, "Cyan", roleName, ChatColor.AQUA);
        addItem(inv, Material.WHITE_DYE, "White", roleName, ChatColor.WHITE);
        addItem(inv, Material.ORANGE_DYE, "Orange", roleName, ChatColor.GOLD);
        addItem(inv, Material.MAGENTA_DYE, "Magenta", roleName, ChatColor.DARK_PURPLE);
        addItem(inv, Material.LIGHT_BLUE_DYE, "Light Blue", roleName, ChatColor.BLUE);
        addItem(inv, Material.LIME_DYE, "Lime", roleName, ChatColor.GREEN);
        addItem(inv, Material.RED_BANNER, "Dark Red", roleName, ChatColor.DARK_RED);

        player.openInventory(inv);
    }
}
