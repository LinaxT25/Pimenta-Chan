package Commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.time.temporal.ChronoUnit;

public class CommandsList {

    public void ping(SlashCommandInteractionEvent event) {
        long ping = event.getInteraction().getTimeCreated().until(event.getTimeCreated(), ChronoUnit.MILLIS);
        event.reply("Your latency is " + ping + " ms.").queue();
    }

}
