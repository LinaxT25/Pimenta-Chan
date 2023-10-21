package com.linaxt25.pimentachan.Events;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GuildVoiceDisconnect extends ListenerAdapter {
    private final String botId;

    public GuildVoiceDisconnect(JDA bot) {
       this.botId = bot.getSelfUser().getId();
    }

    @Override
    public void onGuildVoiceUpdate(GuildVoiceUpdateEvent event) {
        super.onGuildVoiceUpdate(event);

        //Method to not invoke null pointer exception
        try {
            if (event.getChannelLeft().getMembers().stream().count() == 1 &&
                    event.getChannelLeft().getMembers().stream().anyMatch(member -> member.getId().equals(botId))) {
                event.getGuild().getAudioManager().closeAudioConnection();
            }
        } catch (NullPointerException exception) {
            return;
        }
    }
}
