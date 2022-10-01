package Music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class PlayerManager extends ListenerAdapter {
    private AudioPlayerManager playerManager;

    public PlayerManager() {
        this.playerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerRemoteSources(playerManager);
        this.playerManager.enableGcMonitoring();
        //When the player is not queried in the specified amount of time, it is stopped.
        this.playerManager.setPlayerCleanupThreshold(120);
        //When no data from a playing track comes in the specified time, an event is sent.
        this.playerManager.setTrackStuckThreshold(60);
    }

    public AudioPlayerManager getAudioPlayerManager() {
        return this.playerManager;
    }

}
