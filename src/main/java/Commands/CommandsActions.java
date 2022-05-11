package Commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.time.temporal.ChronoUnit;

public class CommandsActions {

    public static void ping(SlashCommandInteractionEvent event) {
        long ping = event.getTimeCreated().until(
                event.getChannel().sendMessage("Senpai!!!").complete().getTimeCreated(), ChronoUnit.MILLIS);
        event.reply("Your latency is " + ping + " ms.").queue();
    }

    public static void hello(SlashCommandInteractionEvent event) {
        event.reply("Hello " + event.getMember().getNickname() + " senpai!!!").queue();
    }
}
