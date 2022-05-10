package Commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class SlashCommandCall extends ListenerAdapter {
    CommandsList commandsList = new CommandsList();

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if(event.getGuild() == null)
            return;
        switch (event.getName()) {
            case "ping":
                commandsList.ping(event);
                break;
        }
    }
}
