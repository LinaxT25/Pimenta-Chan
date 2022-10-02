package Commands.List;

import Music.AudioLoaderHandler;
import Music.AudioPlayerSendHandler;
import Music.PlayerManager;
import Music.TrackScheduler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class PlayMusic {
    /* Main instance from AudioPlayer. */
    private final AudioPlayer musicPlayer;
    /* Creating a scheduler for music queues. */
    private final TrackScheduler trackScheduler;
    /* Generating the AudioLoaderHandler for the URLs with will receive. */
    private final AudioLoaderHandler audioLoaderHandler;

    public PlayMusic() {
        this.musicPlayer = PlayerManager.getAudioPlayerManager().createPlayer();
        this.trackScheduler = new TrackScheduler(musicPlayer);
        this.musicPlayer.addListener(trackScheduler);
        this.audioLoaderHandler = new AudioLoaderHandler(trackScheduler);
    }

    public void playTrack(SlashCommandInteractionEvent event) {
       /* If bot is not connected to audio channel then open a connection in channel which the user
       has made the request if the user not belong a voice channel than close a request  */
        if (!event.getGuild().getAudioManager().isConnected()) {
            if(event.getMember().getVoiceState().inAudioChannel()) {
                event.getGuild()
                        .getAudioManager()
                        .openAudioConnection(event.getMember().getVoiceState().getChannel());

                event.getGuild()
                        .getAudioManager()
                        .setSendingHandler(new AudioPlayerSendHandler(musicPlayer));

                trackScheduler.catchEvent(event);

                PlayerManager.getAudioPlayerManager()
                        .loadItemOrdered(musicPlayer, event.getOption("url").getAsString(), audioLoaderHandler);

                event.reply("Adding to queue: " +  event.getOption("url").getAsString()).queue();
            } else {
                event.reply("You're not in a audio channel!").closeResources().queue();
            }
        } else {
            trackScheduler.catchEvent(event);

            PlayerManager.getAudioPlayerManager()
                    .loadItemOrdered(musicPlayer, event.getOption("url").getAsString(), audioLoaderHandler);

            event.reply("Adding to queue: " + event.getOption("url").getAsString()).queue();
        }
    }

    public void nextTrack(SlashCommandInteractionEvent event) {
        trackScheduler.playNext();
        event.reply("Ok, SENPAI!!!").queue();
    }

    public void stopTrack(SlashCommandInteractionEvent event) {
        trackScheduler.stopTrack();
        event.reply("I've stopped the track and emptied the queue too, senpai!!").queue();
    }
}