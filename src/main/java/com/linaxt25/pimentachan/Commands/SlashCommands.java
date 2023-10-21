package com.linaxt25.pimentachan.Commands;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public class SlashCommands  {
    public SlashCommands(JDA botCommands) {
        botCommands.updateCommands().addCommands(
                Commands.slash("ping", "Returns the latency in ms with pong!")
                        .setGuildOnly(true),
                Commands.slash("hello", "Simple Greeting!")
                        .setGuildOnly(true),
                Commands.slash("gelbooru", "Search for images in gelbooru. Use underscore for names " +
                                "with last name, like this: name_surname.")
                        .setGuildOnly(true)
                        .setNSFW(true)
                        .addOption(OptionType.STRING, "tag", "Tag for the search.",true)
                        .addOption(OptionType.STRING, "number", "Number of images.",true))
                .queue();
    }
}


