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

import java.util.HashMap;
import java.util.Map;

public class SlashCommands extends ListenerAdapter {
    private Map<String, Runnable> commandsMap;

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
                Commands.slash("sexyred", "If bot doesn't have red color, use this.")
                        .setGuildOnly(true)
                        .setDefaultPermissions(DefaultMemberPermissions.DISABLED));
        commands.queue();
    }

    private void commandsList(SlashCommandInteractionEvent event) {
        commandsMap = new HashMap<>();

        commandsMap.put("ping", () -> Ping.ping(event));
        commandsMap.put("hello", () -> Hello.hello(event));
        commandsMap.put("sexyred", () -> SexyRed.sexyRed(event));
        commandsMap.put("play", () -> PlayMusic.playMusic(event));
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
        if(commandsMap.containsKey(eventListener.getName()))
            commandsMap.get(eventListener.getName()).run();
        else
            eventListener.getChannel()
                    .sendMessage("Bad news SENPAI!!! No method call for this command found.")
                    .queue();
    }
}


