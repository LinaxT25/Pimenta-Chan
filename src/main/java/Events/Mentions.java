package Events;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/* Trigger events to respond at any mention to bot */
public class Mentions extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(event.getMessage().getMentions().toString().contains("Pimenta-Chan")) {
            event.getChannel().sendMessage("Senpai!!!").queue();
        }
    }
}
