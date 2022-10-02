package Commands;

import Commands.List.Hello;
import Commands.List.Ping;
import Commands.List.PlayMusic;
import Commands.List.SexyRed;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SlashCommands extends ListenerAdapter {
    private static ArrayList<String> commandList;
    private Map<Guild, PlayMusic> playMusicGuilds;

    public SlashCommands(JDA botCommands) {
        CommandListUpdateAction commands = botCommands.updateCommands();
        playMusicGuilds = new HashMap<>();
        commandsList();

        commands.addCommands(
                Commands.slash("ping", "Returns the latency in ms with pong!")
                        .setGuildOnly(true),
                Commands.slash("hello", "Simple Greeting!")
                        .setGuildOnly(true),
                Commands.slash("play","Play music from provided URL.")
                        .setGuildOnly(true)
                        .addOption(OptionType.STRING, "url", "Music URL.", true),
                Commands.slash("stop", "Stop the track playing")
                        .setGuildOnly(true),
                Commands.slash("next-track","Next track from the queue.")
                        .setGuildOnly(true),
                Commands.slash("sexy-red", "If bot doesn't have red color, use this.")
                        .setGuildOnly(true)
                        .setDefaultPermissions(DefaultMemberPermissions.DISABLED));
        commands.queue();
    }

    private void commandsList() {
        commandList = new ArrayList<>() {
            {
                add("ping");
                add("hello");
                add("play");
                add("stop");
                add("next-track");
                add("sexy-red");
            }
        };
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
                }
            }
        }
    }
}


