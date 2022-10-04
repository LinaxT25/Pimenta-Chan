package Events;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class GuildVoiceDisconnect extends ListenerAdapter {
    private String botId;

    public GuildVoiceDisconnect(JDA bot) {
       this.botId = bot.getSelfUser().getId();
    }

    @Override
    public void onGuildVoiceLeave(@NotNull GuildVoiceLeaveEvent event) {
        super.onGuildVoiceLeave(event);

        if(event.getChannelLeft().getMembers().stream().count() == 1 &&
        event.getChannelLeft().getMembers().stream().anyMatch(member -> member.getId().equals(botId))) {
            event.getGuild().getAudioManager().closeAudioConnection();
        }
    }
}