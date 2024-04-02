# Simple Roles

A basic Minecraft Plugin allowing players to set roles for themselves.

The roles are visible when joining the server, above the player's head,
on the "tab" list and in the chat.

## Configuration

The plugin uses a basic MySQL database to store the players and the roles.
The server has to have a MySQL server running.

Upon first start, the plugin will create a config.yml file in the plugins'
folder with the default configuration. The configuration must be changed
to match the server's MySQL database.

The plugin automatically creates the necessary tables in the database.

## Available commands

- `/rolecreate <role>`: Create a new role available for players to set.
- `/roledelete <role>`: Delete a role.
- `/roleassign <player> <role>`: Assign a role to a player.
- `/roleremove <player> <role>`: Remove a role from a player.
- `/rolelist`: List all available roles.