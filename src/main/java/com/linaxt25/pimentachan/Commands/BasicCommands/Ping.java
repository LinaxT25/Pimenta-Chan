package com.linaxt25.pimentachan.Commands.BasicCommands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class Ping {

    public static void ping(SlashCommandInteractionEvent event) {
        long startTime = System.currentTimeMillis();

        event.deferReply().flatMap(message ->
            event.getHook().editOriginal(
                    "Senpai! Your ping is: " + (System.currentTimeMillis() - startTime) + "ms.")
        ).queue();
    }
}
