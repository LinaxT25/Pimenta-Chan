package Commands;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public class MessageCommands {

    public MessageCommands() {}

    public static void pingCommand(SlashCommandEvent event) {
        long time = System.currentTimeMillis();
        event.reply("Pong!").setEphemeral(false).flatMap(lambda ->
                event.getHook().editOriginalFormat("Pong: %d ms", System.currentTimeMillis() - time)).queue();
    }

    public static void helloCommand(SlashCommandEvent event) {
        event.reply("Senpai!!!").setEphemeral(false).queue();
    }


}
