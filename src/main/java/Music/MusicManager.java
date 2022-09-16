package Music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;

/* Holder for both the player and a track scheduler for one guild. */
public class MusicManager {
    public AudioPlayer player;
    public TrackScheduler scheduler;

    //Crates a player and a track scheduler
    public MusicManager(AudioPlayerManager manager) {
        this.player = manager.createPlayer();
        scheduler = new TrackScheduler(player);
        player.addListener(scheduler);
    }
    //Return wrapper around AudioPlayer to use it as an AudioSendHandler.
    public AudioPlayerSendHandler getSendHandler() {
        return new AudioPlayerSendHandler(player);
    }

}
