package Commands.List;

import Music.MusicManager;
import Music.MusicPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class PlayMusic {

    public static void playMusic(SlashCommandInteractionEvent event) {
        MusicPlayer player = new MusicPlayer();
        MusicManager musicManager = player.getGuildAudioPlayer(event.getGuild());

        player.getPlayerManager().loadItemOrdered(
                musicManager, event.getOption("url").getAsString(), new AudioLoadResultHandler() {

                    @Override
                    public void trackLoaded(AudioTrack audioTrack) {
                        event.getChannel().sendMessage("Adding to queue " + audioTrack.getInfo().title).queue();
                        if (!event.getGuild().getAudioManager().isConnected()) {
                            event.getGuild()
                                    .getAudioManager()
                                    .openAudioConnection(event.getInteraction().getMember().getVoiceState().getChannel());
                            musicManager.scheduler.setQueue(audioTrack);
                        }
                    }

                    @Override
                    public void playlistLoaded(AudioPlaylist audioPlaylist) {
                        AudioTrack firstTrack = audioPlaylist.getSelectedTrack();

                        if (firstTrack == null) {
                            firstTrack = audioPlaylist.getTracks().get(0);
                        }

                        event.getChannel().sendMessage("Adding to queue " + firstTrack.getInfo().title
                                + " (first track of playlist " + audioPlaylist.getName() + ")").queue();

                        if (!event.getGuild().getAudioManager().isConnected()) {
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