package Events;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;

public class Ready implements EventListener {

    @Override
    public void onEvent(GenericEvent event) {
        if(event instanceof ReadyEvent)
            System.out.println("API is ready!");
    }
}
