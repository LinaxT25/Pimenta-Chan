package Commands;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class SlashCommands extends ListenerAdapter {

    public SlashCommands(JDA botCommands) {
        botCommands.upsertCommand("ping", "Calculate ping of the bot").queue();
    }

    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        if(!event.getName().equalsIgnoreCase("ping")) return;
        long time = System.currentTimeMillis();
        event.reply("Pong!").setEphemeral(true).flatMap(lambda ->
                event.getHook().editOriginalFormat("Pong: %d ms", System.currentTimeMillis() - time)).queue();
        }


}


