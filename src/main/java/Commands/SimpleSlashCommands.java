package Commands;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleSlashCommands extends ListenerAdapter {

    private List<String> CommandList = new ArrayList<String>(Arrays.asList(
            "ping",
            "hello"
    ));

    public SimpleSlashCommands(JDA botCommands) {
        CommandListUpdateAction commands = botCommands.updateCommands();
        commands.addCommands(new CommandData("ping", "Devolve pong com valor atual do ping."));
        commands.addCommands(new CommandData("hello", "Retorna o chamado."));
        commands.queue();
    }

    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        if(CommandList.get(0).toString().equalsIgnoreCase(event.getName())) {
            long time = System.currentTimeMillis();
            event.reply("Pong!").setEphemeral(false).flatMap(lambda ->
                    event.getHook().editOriginalFormat("Pong: %d ms", System.currentTimeMillis() - time)).queue();
        }
        else if(CommandList.get(1).toString().equalsIgnoreCase(event.getName())) {
            event.reply("Senpai!!!").setEphemeral(false).queue();
        }
    }

}


