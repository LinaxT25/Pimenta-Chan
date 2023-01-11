package Commands.List;

import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;

import java.awt.*;

public class SexyRed {

    public static void sexyRed(SlashCommandInteractionEvent event) {
        Role sexyRed;

        try {
            event.getGuild().createRole()
                    .setName("Sexy Red")
                    .setColor(Color.RED)
                    .setMentionable(false)
                    .setHoisted(true)
                    .complete();

            sexyRed = event.getGuild()
                    .getRolesByName("Sexy Red", true)
                    .stream()
                    .findFirst()
                    .get();

            event.getGuild()
                    .addRoleToMember(event.getGuild().getSelfMember(), sexyRed)
                    .complete();

            event.reply("I've changed my color to **Sexy Red**, senpai!").queue();

        } catch (InsufficientPermissionException error) {
            event.getGuild().getDefaultChannel().asStandardGuildMessageChannel()
                    .sendMessage("I don't have menage roles permissions, senpai!")
                    .queue();
            event.getGuild().getDefaultChannel().asStandardGuildMessageChannel()
                    .sendMessage("Without permissions i can't change my color to a sexy red, senpai!!!")
                    .queue();
            event.getGuild().getDefaultChannel().asStandardGuildMessageChannel()
                    .sendMessage("Bad Senpai!!! Now you have to menage my permissions and call the command again!.")
                    .queue();
            event.getGuild().getDefaultChannel().asStandardGuildMessageChannel()
                    .sendMessage("**BAD SENPAI!**")
                    .queue();
        }
    }
}
