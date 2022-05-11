package Commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SlashCommandCall extends ListenerAdapter {

    /* Creating a map to store commands and methods call related to them */
    private List<String> commandList;

    public SlashCommandCall () {
        this.commandList = new ArrayList<>();

        this.commandList.add("ping");
        this.commandList.add("hello");
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        /* Only guild can invoke slash commands */
        if(event.getGuild() == null)
            return;

        if(commandList.contains(event.getName())) {
            switch (event.getName()) {
                case "ping":
                    CommandsActions.ping(event);
                case "hello":
                    CommandsActions.hello(event);
            }
        }
        else
            event.getChannel().sendMessage("Bad news SENPAI!!! No method call for this command found.").queue();

    }

}
