package Colors;

import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.exceptions.ErrorResponseException;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.ErrorResponse;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class AutoColorRole extends ListenerAdapter {

    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {
        super.onGuildJoin(event);

        /* Add a red color to bot when join to the guild */
        try {
            event.getGuild().createRole().setName("Sexy Red").setColor(Color.RED).setMentionable(false).queue();
        } catch (ErrorResponseException error) {
            if(error.getErrorResponse().equals(ErrorResponse.MISSING_PERMISSIONS))
                event.getGuild().getDefaultChannel()
                        .sendMessage("I don't have menage roles permissions, senpai!").queue();
                event.getGuild().getDefaultChannel()
                        .sendMessage("Without permissions i can't change my color to a sexy red, senpai!!!").queue();
                event.getGuild().getDefaultChannel()
                        .sendMessage("Bad Senpai!!! Now you have to change my color personally.").queue();
                event.getGuild().getDefaultChannel()
                        .sendMessage("Give menage roles permissions then use the sexy red command.").queue();
                event.getGuild().getDefaultChannel().sendMessage("BAD SENPAI!");
        }

    }
}
