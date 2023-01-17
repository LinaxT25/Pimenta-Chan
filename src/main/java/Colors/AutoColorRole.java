package Colors;

import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class AutoColorRole extends ListenerAdapter {

    @Override
    public void onGuildJoin(GuildJoinEvent event) {
        super.onGuildJoin(event);
        /* Add a red color to bot when join to the guild */
        try {
            event.getGuild().createRole()
                    .setName("Sexy Red")
                    .setColor(Color.RED)
                    .setMentionable(false)
                    .setHoisted(true)
                    .complete();
            /* if exists a role then bot will self add the role */
            if(!(event.getGuild().getRolesByName("Sexy Red", true).isEmpty())) {
                event.getGuild().addRoleToMember(event.getGuild().getSelfMember(),
                        event.getGuild()
                                .getRoles()
                                .stream()
                                .filter(role -> role.getName().contains("Sexy Red"))
                                .findFirst()
                                .orElseThrow())
                        .complete();
            }
            if(event.getGuild().getSelfMember().getRoles().stream().anyMatch(role -> role.getName().contains("Sexy Red"))) {
                event.getGuild().getDefaultChannel().asStandardGuildMessageChannel()
                        .sendMessage("I've changed my color to **Sexy Red**, senpai!").queue();
            }
        }catch(InsufficientPermissionException error) {
                event.getGuild().getDefaultChannel().asStandardGuildMessageChannel()
                        .sendMessage("I don't have manage roles permissions, senpai!").queue();
                event.getGuild().getDefaultChannel().asStandardGuildMessageChannel()
                        .sendMessage("Without permissions i can't change my color to a sexy red, senpai!!!").queue();
                event.getGuild().getDefaultChannel().asStandardGuildMessageChannel()
                        .sendMessage("Bad Senpai!!! Now you have to change my color personally.").queue();
                event.getGuild().getDefaultChannel().asStandardGuildMessageChannel()
                        .sendMessage("Give menage roles permissions then use the sexy red command.").queue();
                event.getGuild().getDefaultChannel().asStandardGuildMessageChannel().sendMessage("**BAD SENPAI!**").queue();
        }
    }
}
