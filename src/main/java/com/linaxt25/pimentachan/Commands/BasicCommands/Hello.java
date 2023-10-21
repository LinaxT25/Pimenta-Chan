package com.linaxt25.pimentachan.Commands.BasicCommands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class Hello {

    public static void hello(SlashCommandInteractionEvent event) {
        if(event.getMember().getNickname() != null)
            event.reply("Hello " + event.getMember().getNickname() + " senpai!!!").queue();
        else
            event.reply("Hello " + event.getUser().getEffectiveName() + " senpai!!!").queue();
    }
}
