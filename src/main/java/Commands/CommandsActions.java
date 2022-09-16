package Commands;

import Music.MusicManager;
import Music.MusicPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

/* Store all methods for simple commands and add then to a HashMap for use */
public class CommandsActions {
    /* Creating a map to store commands and methods call related to them */
    private Map<String, Runnable> commandsMap;

    CommandsActions (SlashCommandInteractionEvent event) {
        commandsMap = new HashMap<>();

        commandsMap.put("ping", () -> ping(event));
        commandsMap.put("hello", () -> hello(event));
       //commandsMap.put("Sexy Red", () -> sexyRed(event));
        commandsMap.put("play", () -> playMusic(event));
    }

    public Map<String, Runnable> getCommandsMap() {
        return commandsMap;
    }

    public static void ping(SlashCommandInteractionEvent event) {
        long ping = event.getTimeCreated().until(
                event.getChannel().sendMessage("Senpai!!!").complete().getTimeCreated(), ChronoUnit.MILLIS);
        event.reply("Your latency is " + ping + " ms.").queue();
    }

    public static void hello(SlashCommandInteractionEvent event) {
        event.reply("Hello " + event.getMember().getNickname() + " senpai!!!").queue();
    }

    //TO DO
    public static void sexyRed(SlashCommandInteractionEvent event) {

    }

    public static void playMusic(SlashCommandInteractionEvent event) {
        MusicPlayer player = new MusicPlayer();
        MusicManager musicManager = player.getGuildAudioPlayer(event.getGuild());

        player.getPlayerManager()
                .loadItemOrdered(musicManager, event.getOption("url").getAsString(), new AudioLoadResultHandler() {

            @Override
            public void trackLoaded(AudioTrack audioTrack) {
                event.getChannel().sendMessage("Adding to queue " + audioTrack.getInfo().title).queue();
                if(!event.getGuild().getAudioManager().isConnected()) {
                    event.getGuild()
                            .getAudioManager()
                            .openAudioConnection(event.getInteraction().getMember().getVoiceState().getChannel());
                    musicManager.scheduler.setQueue(audioTrack);
                }
            }

            @Override
            public void playlistLoaded(AudioPlaylist audioPlaylist) {
                AudioTrack firstTrack = audioPlaylist.getSelectedTrack();

                if(firstTrack == null) {
                    firstTrack = audioPlaylist.getTracks().get(0);
                }

                event.getChannel().sendMessage("Adding to queue " + firstTrack.getInfo().title
                        + " (first track of playlist " + audioPlaylist.getName() + ")").queue();

                if(!event.getGuild().getAudioManager().isConnected()) {
                    event.getGuild()
                            .getAudioManager()
                            .openAudioConnection(event.getInteraction().getMember().getVoiceState().getChannel());
                    musicManager.scheduler.setQueue(firstTrack);
                }
            }

            @Override
            public void noMatches() {
                event.getChannel().sendMessage("Nothing found by " + event.getOption("url").getAsString()).queue();
            }

            @Override
            public void loadFailed(FriendlyException e) {
                event.getChannel().sendMessage("Could not play: " + e.getMessage()).queue();
            }
        });
    }
}
