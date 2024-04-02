package com.simpleroles.simpleroles;

import com.simpleroles.simpleroles.Commands.RoleAssign;
import com.simpleroles.simpleroles.Commands.RoleCreate;
import com.simpleroles.simpleroles.Commands.RoleDelete;
import com.simpleroles.simpleroles.Commands.RoleRemove;
import com.simpleroles.simpleroles.Listeners.MenuHandler;
import com.simpleroles.simpleroles.Listeners.PlayerJoin;
import com.simpleroles.simpleroles.sql.MySQL;
import com.simpleroles.simpleroles.sql.QueryManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class Main extends JavaPlugin {
    public static MySQL sql;

    @Override
    public void onEnable() {
        CustomConfig.setup();
        CustomConfig.get().options().copyDefaults(true);
        CustomConfig.save();

        sql = new MySQL();

        try {
            sql.connect();
        } catch (ClassNotFoundException | SQLException e) {
            Bukkit.getLogger().info("Could not connect to MySQL database.");
        }

        if (sql.isConnected()) {
            Bukkit.getLogger().info("Connected to MySQL database.");
        }

        QueryManager.createInitialTables();

        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new MenuHandler(), this);

        getCommand("rolecreate").setExecutor(new RoleCreate());
        getCommand("roledelete").setExecutor(new RoleDelete());
        getCommand("roleassign").setExecutor(new RoleAssign());
        getCommand("roleremove").setExecutor(new RoleRemove());
    }

    @Override
    public void onDisable() {
        sql.disconnect();
    }

    public static MySQL getSQL() {
        return sql;
    }
}
