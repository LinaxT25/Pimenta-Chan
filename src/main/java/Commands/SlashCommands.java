package Commands;

import Commands.List.Hello;
import Commands.List.Ping;
import Commands.List.PlayMusic;
import Commands.List.SexyRed;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;

import java.util.ArrayList;
import java.util.Objects;

public class SlashCommands extends ListenerAdapter {
    private static ArrayList<String> commandList;
    private PlayMusic playMusic = new PlayMusic(); //Music cannot be a static so an object is needed.

    public SlashCommands(JDA botCommands) {
        CommandListUpdateAction commands = botCommands.updateCommands();

        commands.addCommands(
                Commands.slash("ping", "Returns the latency in ms with pong!")
                        .setGuildOnly(true),
                Commands.slash("hello", "Simple Greeting!")
                        .setGuildOnly(true),
                Commands.slash("play","Play music from provided URL.")
                        .setGuildOnly(true)
                        .addOption(OptionType.STRING, "url", "Music URL.", true),
                Commands.slash("next-track","Next track from the queue.")
                        .setGuildOnly(true),
                Commands.slash("sexy-red", "If bot doesn't have red color, use this.")
                        .setGuildOnly(true)
                        .setDefaultPermissions(DefaultMemberPermissions.DISABLED));
        commands.queue();
    }

    private void commandsList(SlashCommandInteractionEvent event) {
        commandList = new ArrayList<String>() {
            {
                add("ping");
                add("hello");
                add("sexy-red");
                add("play");
                add("next-track");
            }
        };
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent eventListener) {
        /* On a call to invoke commands initialize a list of commands */
        commandsList(eventListener);

        /* Only guild can invoke slash commands */
        if(eventListener.getGuild() == null) {
            eventListener.getInteraction().reply("You're not a guild user! I don`t obey your commands!");
            return;
        }
        /* Calling the method in response to event */
        for(int i = 0; i < commandList.size(); i++) {
            if(Objects.equals(commandList.get(i), eventListener.getName())) {
                switch (commandList.get(i)) {
                    case "ping" :
                        Ping.ping(eventListener);
                        break;
                    case "hello" :
                        Hello.hello(eventListener);
                        break;
                    case "sexy-red":
                        SexyRed.sexyRed(eventListener);
                        break;
                    case "play":
                        playMusic.playTrack(eventListener);
                        break;
                    case "next-track":
                        playMusic.nextTrack(eventListener);
                        break;
                }
            }
        }
    }
}


