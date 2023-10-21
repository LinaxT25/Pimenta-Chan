package com.linaxt25.pimentachan.Commands;

import com.linaxt25.pimentachan.Commands.BasicCommands.Hello;
import com.linaxt25.pimentachan.Commands.BasicCommands.Ping;
import com.linaxt25.pimentachan.Commands.Nsfw.Gelbooru;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.Objects;

public class CommandCall extends ListenerAdapter {
    private static final ArrayList<String> commandList = new ArrayList<>();

    public CommandCall(JDA bot) {
        SlashCommands slashCommands = new SlashCommands(bot); //Updating all commands

        //TODO Methods documentation
        //TODO Ban with time
        //TODO Auto commands
        //TODO Reverse image search
        //TODO Myanimelist recommendation

        commandList.add("ping");
        commandList.add("hello");
        commandList.add("sexy-red");
        commandList.add("gelbooru");
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent eventListener) {
        /* Calling the method in response to event */
        for (String s : commandList) {
            if (Objects.equals(s, eventListener.getName())) {
                switch (s) {
                    case "ping":
                        Ping.ping(eventListener);
                        break;
                    case "hello":
                        Hello.hello(eventListener);
                        break;
                    case "gelbooru":
                        Gelbooru.searchGelbooru(eventListener);
                }
            }
        }
    }
}
