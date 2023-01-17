package BD.Scripts;

import java.sql.Connection;

public class DBInitialization {

    public void databaseStart(Connection connection) {
        String SQLSchema = "CREATE SCHEMA " + "guilds";

        String SQLGuilds = "CREATE TABLE guilds.guild" +
                "(" +
                "id BIGINT(255) NOT NULL," +
                "name varchar(255) NOT NULL," +
                "owner_id BIGINT(255) NOT NULL," +
                "adm_role_id BIGINT(255)," +
                "mod_role_id BIGINT(255)," +
                "lock_guild_commands BOOL," +
                "PRIMARY KEY(id)" +
                ")";

        String SQLBotUsers = "CREATE TABLE guilds.guild_bot_users" +
                "(" +
                "guild_id BIGINT(255) NOT NULL," +
                "user_id  BIGINT(255)," +
                "FOREIGN KEY (guild_id) REFERENCES guilds.guild(id)" +
                ")";

        String ImageBoardUser =  "CREATE TABLE guilds.guild_image_board" +
                "(" +
                "guild_id BIGINT(255) NOT NULL," +
                "user_id BIGINT(255) NOT NULL," +
                "image_board_name varchar(255)" +
                ")";

    }

}
