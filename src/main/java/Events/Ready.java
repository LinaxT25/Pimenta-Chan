package Events;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;

import java.util.logging.Logger;

/* Events called when bot is ready to receive commands */
public class Ready implements EventListener {
    private final Logger logger = Logger.getLogger(Ready.class.getName());

    @Override
    public void onEvent(GenericEvent event) {
        if(event instanceof ReadyEvent)
            logger.info("API is ready!");
    }
}
