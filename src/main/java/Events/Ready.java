package Events;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;

import java.util.logging.Logger;

/* Events called when bot is ready to receive commands */
public class Ready implements EventListener {
    private final Logger logger = Logger.getLogger(Ready.class.getName());

    @Override
    public void onEvent(GenericEvent event) {
        if(event instanceof ReadyEvent) {
            logger.info("API is ready!" +
            "\nConnect to: " + event.getJDA().getGuilds() +
            "\nTotal of guilds: " + ((ReadyEvent) event).getGuildTotalCount());

            /*try {
               DBConnection connection = new DBConnection();
                DBInitialization dbInitialization = new DBInitialization();
                dbInitialization.databaseStart(connection.getConnection());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            */
        }
    }
}
