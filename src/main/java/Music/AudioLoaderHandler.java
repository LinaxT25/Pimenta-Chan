package Music;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class AudioLoaderHandler implements AudioLoadResultHandler {
    private final TrackScheduler trackScheduler;
    private SlashCommandInteractionEvent event;

    public AudioLoaderHandler(TrackScheduler trackScheduler) {
        this.trackScheduler = trackScheduler;
    }

    @Override
    public void trackLoaded(AudioTrack audioTrack) {
        trackScheduler.queue(audioTrack);
    }

    @Override
    public void playlistLoaded(AudioPlaylist audioPlaylist) {
       for(AudioTrack track : audioPlaylist.getTracks()) {
           trackScheduler.queue(track);
       }
    }

    @Override
    public void noMatches() {
        this.event = trackScheduler.getEvent();
        event.reply("None matches founded with source provided, Senpai!")
                .closeResources()
                .queue();
    }

    @Override
    public void loadFailed(FriendlyException e) {
        this.event = trackScheduler.getEvent();
        event.reply("Emergency Senpai!!!!, I've received errors closing all resources.")
                .closeResources()
                .queue();
        throw e;
    }
}
