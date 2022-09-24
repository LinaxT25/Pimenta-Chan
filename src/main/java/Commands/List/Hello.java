package Commands.List;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class Hello {

    public static void hello(SlashCommandInteractionEvent event) {
        event.reply("Hello " + event.getMember().getNickname() + " senpai!!!").queue();
    }
}
