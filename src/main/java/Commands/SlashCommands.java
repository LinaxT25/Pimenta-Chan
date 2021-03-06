package Commands;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;

public class SlashCommands extends ListenerAdapter {

    public SlashCommands(JDA botCommands) {
        CommandListUpdateAction commands = botCommands.updateCommands();

        commands.addCommands(
                Commands.slash("ping", "Returns the latency in ms with pong!"),
                Commands.slash("hello", "Simple Greeting!"));

        commands.queue();

    }
}


