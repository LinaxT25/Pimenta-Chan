package Commands.List;

import Music.AudioLoaderHandler;
import Music.AudioPlayerSendHandler;
import Music.PlayerManager;
import Music.TrackScheduler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class PlayMusic {

    public static void playMusic(SlashCommandInteractionEvent event) {
        //Creating a new player manager for all AudioPlayers
        PlayerManager playerManager = new PlayerManager();
        //Main instance from AudioPlayer
        AudioPlayer musicPlayer = playerManager.getAudioPlayerManager().createPlayer();
        //Creating a scheduler for music queues
        TrackScheduler trackScheduler = new TrackScheduler(musicPlayer, event.getChannel());
        //Generating the AudioLoaderHandler for the URLs with will receive.
        AudioLoaderHandler audioLoaderHandler = new AudioLoaderHandler(trackScheduler);

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

                playerManager.getAudioPlayerManager()
                        .loadItemOrdered(musicPlayer, event.getOption("url").getAsString(), audioLoaderHandler);

                event.reply("Adding to queue: " +  event.getOption("url").getAsString()).queue();
            } else {
                event.reply("You're not in a audio channel!").closeResources().complete();
            }
        } else {
            playerManager.getAudioPlayerManager()
                    .loadItemOrdered(musicPlayer, event.getOption("url").getAsString(), audioLoaderHandler);

            event.reply("Adding to queue: " + event.getOption("url").getAsString()).queue();
        }
    }
}