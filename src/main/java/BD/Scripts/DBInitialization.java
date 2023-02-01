package BD.Scripts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBInitialization {

    public void databaseStart(Connection connection) throws SQLException {
        String SQLSchema = "CREATE SCHEMA " + "guilds";

        String SQLGuilds = "CREATE TABLE guilds.guild" +
                "(" +
                "id BIGINT NOT NULL," +
                "name varchar(255) NOT NULL," +
                "owner_id BIGINT NOT NULL," +
                "adm_role_id BIGINT," +
                "mod_role_id BIGINT," +
                "lock_guild_commands BOOLEAN," +
                "PRIMARY KEY(id)" +
                ")";

        String SQLBotUsers = "CREATE TABLE guilds.guild_bot_users" +
                "(" +
                "guild_id BIGINT NOT NULL," +
                "user_id  BIGINT," +
                "FOREIGN KEY (guild_id) REFERENCES guilds.guild(id)" +
                ")";

        String ImageBoardUser =  "CREATE TABLE guilds.guild_image_board" +
                "(" +
                "guild_id BIGINT NOT NULL," +
                "user_id BIGINT NOT NULL," +
                "image_board_name varchar(255)," +
                "FOREIGN KEY (guild_id) REFERENCES guilds.guild(id)" +
                ")";

        try {
            connection.setAutoCommit(false);

            PreparedStatement schema = connection.prepareStatement(SQLSchema);
            PreparedStatement guilds = connection.prepareStatement(SQLGuilds);
            PreparedStatement botUsers = connection.prepareStatement(SQLBotUsers);
            PreparedStatement imageBoardUser = connection.prepareStatement(ImageBoardUser);

            schema.execute();
            guilds.execute();
            botUsers.execute();
            imageBoardUser.execute();

            connection.commit();
            connection.close();

        } catch (SQLException sqlException) {
            connection.close();
            throw new RuntimeException(sqlException);
        }
    }

}
