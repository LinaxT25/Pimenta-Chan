package Commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/* Response to calling command from a member guild */
public class SlashCommandResponse extends ListenerAdapter {
    private CommandsActions commandsActions;

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        commandsActions = new CommandsActions(event);
        /* Only guild can invoke slash commands */
        if(event.getGuild() == null)
            return;
        /* Calling the method in response to event */
        if(commandsActions.getCommandsMap().containsKey(event.getName()))
            commandsActions.getCommandsMap().get(event.getName()).run();
        else
            event.getChannel().sendMessage("Bad news SENPAI!!! No method call for this command found.").queue();

    }

}
