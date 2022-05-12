package Commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class CommandsActions {

    /* Creating a map to store commands and methods call related to them */
    private Map<String, Runnable> commandsMap;

    CommandsActions (SlashCommandInteractionEvent event) {
        commandsMap = new HashMap<>();

        commandsMap.put("ping", () -> ping(event));
        commandsMap.put("hello", () -> hello(event));
    }

    public Map<String, Runnable> getCommandsMap() {
        return commandsMap;
    }

    public static void ping(SlashCommandInteractionEvent event) {
        long ping = event.getTimeCreated().until(
                event.getChannel().sendMessage("Senpai!!!").complete().getTimeCreated(), ChronoUnit.MILLIS);
        event.reply("Your latency is " + ping + " ms.").queue();
    }

    public static void hello(SlashCommandInteractionEvent event) {
        event.reply("Hello " + event.getMember().getNickname() + " senpai!!!").queue();
    }
}
