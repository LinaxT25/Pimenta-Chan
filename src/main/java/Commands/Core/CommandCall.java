package Commands.Core;

import Commands.List.Hello;
import Commands.List.Ping;
import Commands.List.PlayMusic;
import Commands.List.SexyRed;
import Commands.Nsfw.Gelbooru;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CommandCall extends ListenerAdapter {
    private static final ArrayList<String> commandList = new ArrayList<>();
    private Map<Guild, PlayMusic> playMusicGuilds = new HashMap<>();

    public CommandCall(JDA bot) {
        SlashCommands slashCommands = new SlashCommands(bot); //Updating all commands

        commandList.add("ping");
        commandList.add("hello");
        commandList.add("play");
        commandList.add("stop");
        commandList.add("next-track");
        commandList.add("sexy-red");
        commandList.add("gelbooru");
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent eventListener) {
        //Storing guilds to mapping for consistency
        if(!playMusicGuilds.containsKey(eventListener.getGuild()))
            playMusicGuilds.put(eventListener.getGuild(), new PlayMusic());

        /* Calling the method in response to event */
        for (String s : commandList) {
            if (Objects.equals(s, eventListener.getName())) {
                switch (s) {
                    case "play":
                        playMusicGuilds.get(eventListener.getGuild()).playTrack(eventListener);
                        break;
                    case "next-track":
                        playMusicGuilds.get(eventListener.getGuild()).nextTrack(eventListener);
                        break;
                    case "stop":
                        playMusicGuilds.get(eventListener.getGuild()).stopTrack(eventListener);
                        break;
                    case "ping":
                        Ping.ping(eventListener);
                        break;
                    case "hello":
                        Hello.hello(eventListener);
                        break;
                    case "sexy-red":
                        SexyRed.sexyRed(eventListener);
                        break;
                    case "gelbooru":
                        Gelbooru.searchGelbooru(eventListener);
                }
            }
        }
    }
}
