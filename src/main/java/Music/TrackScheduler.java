package Music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/* Holder for both the player and a track scheduler for one guild. */
public class TrackScheduler extends AudioEventAdapter {
    private Queue<AudioTrack> queue = new LinkedBlockingQueue<>();
    private final AudioPlayer audioPlayer;
    private SlashCommandInteractionEvent event;

    public TrackScheduler(AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
    }

    public void queue(AudioTrack track) {
        if(audioPlayer.getPlayingTrack() == null) {
            queue.offer(track);
            playTrack();
        } else {
            queue.offer(track);
        }
    }

    public void playTrack() {
        audioPlayer.playTrack(queue.poll());
    }

    public void playNext() {
        if(audioPlayer.getPlayingTrack() != null) {
            audioPlayer.stopTrack();
            playTrack();
        } else {
            playTrack();
        }
    }

    public void catchEvent(SlashCommandInteractionEvent event) {
        this.event = event;
    }

    public SlashCommandInteractionEvent getEvent() {
        return this.event;
    }

    @Override
    public void onPlayerPause(AudioPlayer player) {
        //TODO
        // Player was paused
    }

    @Override
    public void onPlayerResume(AudioPlayer player) {
        //TODO
        // Player was resumed
    }

    @Override
    public void onTrackStart(AudioPlayer player, AudioTrack track) {
        event.getChannel().sendMessage("Playing now: " + track.getInfo().title).queue();
    }

    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {

        /* endReason == FINISHED: A track finished or died by an exception (mayStartNext = true).
         * endReason == LOAD_FAILED: Loading of a track failed (mayStartNext = true).
         * endReason == STOPPED: The player was stopped.
         * endReason == REPLACED: Another track started playing while this had not finished
         * endReason == CLEANUP: Player hasn't been queried for a while, if you want you can put a
                                  clone of this back to your queue */

        if (endReason.mayStartNext) {
            audioPlayer.playTrack(queue.poll());
        }
    }

    @Override
    public void onTrackException(AudioPlayer player, AudioTrack track, FriendlyException exception) {
        //TODO
        // An already playing track threw an exception (track end event will still be received separately)
    }

    @Override
    public void onTrackStuck(AudioPlayer player, AudioTrack track, long thresholdMs) {
        //TODO
        // Audio track has been unable to provide us any audio, might want to just start a new track
    }
}