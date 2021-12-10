package Events;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Call extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message message = event.getMessage();

        if(message.getMentionedUsers().toString().contains("Pimenta-Chan")) {
            message.getChannel().sendMessage("Senpai!!!").queue();
        }
    }
}
