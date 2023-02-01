package Commands;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public class SlashCommands  {
    public SlashCommands(JDA botCommands) {
        botCommands.updateCommands().addCommands(
                Commands.slash("ping", "Returns the latency in ms with pong!")
                        .setGuildOnly(true),
                Commands.slash("hello", "Simple Greeting!")
                        .setGuildOnly(true),
               /* Commands.slash("play","Play music from provided URL.")
                        .setGuildOnly(true)
                        .addOption(OptionType.STRING, "url", "Music URL.", true),
                Commands.slash("stop", "Stop the track playing")
                        .setGuildOnly(true),
                Commands.slash("next-track","Next track from the queue.")
                        .setGuildOnly(true), */
                Commands.slash("sexy-red", "If bot doesn't have red color, use this.")
                        .setGuildOnly(true)
                        .setDefaultPermissions(DefaultMemberPermissions.DISABLED),
                Commands.slash("gelbooru", "Search for images in gelbooru. Use underscore for names " +
                                "with last name, like this: name_surname.")
                        .setGuildOnly(true)
                        .setNSFW(true)
                        .addOption(OptionType.STRING, "tag", "Tag for the search.",true)
                        .addOption(OptionType.STRING, "number", "Number of images.",true))
                .queue();
    }
}


