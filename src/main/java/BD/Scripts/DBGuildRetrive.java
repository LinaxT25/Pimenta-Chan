package BD.Scripts;

import net.dv8tion.jda.api.entities.Guild;

import java.sql.Connection;
import java.sql.SQLException;

public class DBGuildRetrive {

    public void getGuildData(Connection connection, Guild guild) throws SQLException {
        String SQL = "INSERT INTO guilds.guild VALUES(?,?,?,?,?,?)";


    }
}
