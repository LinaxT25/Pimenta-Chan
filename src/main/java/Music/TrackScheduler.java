package Music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TrackScheduler extends AudioEventAdapter {
    private AudioPlayer player;
    private BlockingQueue<AudioTrack> queue;

    public TrackScheduler(AudioPlayer player) {
        this.player = player;
        this.queue = new LinkedBlockingQueue<>();
    }
    //Add the next track to queue or play right away if nothing is in the queue.
    public void setQueue(AudioTrack track) {
        if(!player.startTrack(track,false)) {
            queue.offer(track);
        }
    }
    //Start the next track, stopping the current one if it is playing.
    public void nextTrack() {
        player.startTrack(queue.poll(), false);
    }

    //Only start next track if recieves FINISHED or LOAD_FAILED
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
        if(endReason.mayStartNext) {
            nextTrack();
        }
    }
}
