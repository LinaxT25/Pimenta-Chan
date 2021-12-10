package Commands;

import Interfaces.CommandInterface;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;

import java.util.HashMap;

public class SimpleSlashCommands extends ListenerAdapter {

    public HashMap<String, CommandInterface> commands;

    public SimpleSlashCommands(JDA botCommands) {
        CommandListUpdateAction commands = botCommands.updateCommands();
        commands.addCommands(new CommandData("ping", "Devolve pong com valor atual do ping."));
        commands.addCommands(new CommandData("hello", "Retorna o chamado."));
        commands.queue();
    }

    @Override
    public void onSlashCommand(SlashCommandEvent event) {
       commands = new HashMap<>();

        commands.put("ping", () -> Commands.MessageCommands.pingCommand(event));
        commands.put("hello", () -> Commands.MessageCommands.helloCommand(event));

        if(event.getGuild() == null)
            return;

        if(commands.containsKey(event.getName())) {
            commands.get(event.getName()).runCommand();
        }

    }
}


