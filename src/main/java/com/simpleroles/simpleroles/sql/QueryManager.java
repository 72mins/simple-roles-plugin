package com.simpleroles.simpleroles.sql;

import com.simpleroles.simpleroles.Main;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryManager {
    private static MySQL sql = Main.getSQL();

    public static void createInitialTables() {
        try {
            String roleQuery = "CREATE TABLE IF NOT EXISTS roles (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), color VARCHAR(255))";
            String playerQuery = "CREATE TABLE IF NOT EXISTS players (uuid VARCHAR(36) PRIMARY KEY, name VARCHAR(255), role_id INT)";

            sql.getConnection().createStatement().executeUpdate(roleQuery);
            sql.getConnection().createStatement().executeUpdate(playerQuery);
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createNewRole(Player player, String roleName, String chosenColor) {
        String query = "INSERT INTO roles (name, color) " +
                "SELECT * FROM (SELECT '" + roleName + "', '" + chosenColor + "') AS tmp " +
                "WHERE NOT EXISTS (" +
                "SELECT name FROM roles WHERE name = '" + roleName + "' " +
                ") LIMIT 1;";

        try {
            sql.getConnection().createStatement().executeUpdate(query);
            player.sendMessage("Role " + roleName + " created.");
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public static void createPlayer(Player player) {
        try {
            String query = "INSERT INTO players (uuid, name) " +
                    "SELECT * FROM (SELECT '" + player.getUniqueId() + "', '" + player.getName() + "') AS tmp " +
                    "WHERE NOT EXISTS (" +
                    "SELECT uuid FROM players WHERE uuid = '" + player.getUniqueId() + "' " +
                    ") LIMIT 1;";
            sql.getConnection().createStatement().executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String[] getRoleAndColor(Player player) {
        String roleName = "";
        String roleColor = "";

        String playerRoleQuery = "SELECT roles.name, roles.color FROM roles " +
                "JOIN players ON roles.id = players.role_id " +
                "WHERE players.uuid = '" + player.getUniqueId() + "';";

        try {
            ResultSet resultSet = sql.getConnection().createStatement().executeQuery(playerRoleQuery);

            if (resultSet.next()) {
                roleName = resultSet.getString("name");
                roleColor = resultSet.getString("color");
            } else {
                roleName = "Default";
                roleColor = "f";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new String[]{roleName, roleColor};
    }

    public static String[] getIdAndColor(Player player, String roleName) {
        String roleId = "0";
        String roleColor = "";

        String roleQuery = "SELECT id, color FROM roles WHERE name = '" + roleName + "';";

        try {
            ResultSet resultSet = sql.getConnection().createStatement().executeQuery(roleQuery);
            if (resultSet.next()) {
                roleId = String.valueOf(resultSet.getInt("id"));
                roleColor = resultSet.getString("color");
            } else {
                player.sendMessage("Role " + roleName + " does not exist.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new String[]{roleId, roleColor};
    }

    public static void updateRole(Player target, Player player, String roleName, int roleId) {
        String query = "UPDATE players SET role_id = " + roleId + " WHERE uuid = '" + target.getUniqueId() + "';";

        try {
            sql.getConnection().createStatement().executeUpdate(query);
            player.sendMessage("Role " + roleName + " assigned to " + target.getName() + ".");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteRole(Player player, String roleName) {
        String query = "DELETE FROM roles WHERE name = '" + roleName + "';";

        try {
            sql.getConnection().createStatement().executeUpdate(query);
            player.sendMessage("Role " + roleName + " deleted.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clearRole(Player player, Player target) {
        String query = "UPDATE players SET role_id = null WHERE uuid = '" + target.getUniqueId() + "';";
        try {
            sql.getConnection().createStatement().executeUpdate(query);
            player.sendMessage("Role removed from " + target.getName() + ".");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String[] listRoles() {
        List<String> roles = new ArrayList<>();

        String query = "SELECT name FROM roles;";

        try {
            ResultSet resultSet = sql.getConnection().createStatement().executeQuery(query);

            while (resultSet.next()) {
                roles.add(resultSet.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return roles.toArray(new String[0]);
    }
}
