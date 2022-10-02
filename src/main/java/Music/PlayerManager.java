package Music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PlayerManager extends ListenerAdapter {
    private static AudioPlayerManager playerManager;
    private Logger logger = Logger.getLogger(PlayerManager.class.getName());

    public PlayerManager() {
        playerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerRemoteSources(playerManager);
        playerManager.enableGcMonitoring();
        //When the player is not queried in the specified amount of time, it is stopped.
        playerManager.setPlayerCleanupThreshold(60000);
        //When no data from a playing track comes in the specified time, an event is sent.
        playerManager.setTrackStuckThreshold(30000);
        playerManager.setFrameBufferDuration(400);
        logger.log(Level.INFO, String.valueOf(playerManager.getFrameBufferDuration()));
    }

    public static AudioPlayerManager getAudioPlayerManager() {
        return playerManager;
    }

}
