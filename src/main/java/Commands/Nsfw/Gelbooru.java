package Commands.Nsfw;

import Colors.RandomColorPick;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.kodehawa.lib.imageboards.DefaultImageBoards;
import net.kodehawa.lib.imageboards.ImageBoard;
import net.kodehawa.lib.imageboards.entities.BoardImage;
import net.kodehawa.lib.imageboards.entities.impl.GelbooruImage;

import java.awt.*;
import java.util.List;
import java.util.Random;


public class Gelbooru {
    //TODO Emojis to get new images

    /* Searching in gelbooru for images, due the fact the API is not random then we need generate random
        numbers to access page and catch some variety of images */
    public static void searchGelbooru(SlashCommandInteractionEvent event) {
        ImageBoard.setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:108.0) Gecko/20100101 Firefox/108.0");

        //Looking for a random number for pages, 1 and 20 should be average
        int random = new Random().ints(0, 14).findFirst().getAsInt();

        MessageChannelUnion messageChannel= event.getChannel();
        String tag = event.getOption("tag").getAsString().toLowerCase();
        InteractionHook hook = event.getHook();
        event.deferReply().queue();

        List<GelbooruImage> imageBoards = DefaultImageBoards.GELBOORU.search(
                        random, event.getOption("number").getAsInt(), event.getOption("tag").getAsString())
                        .blocking();

        hook.deleteOriginal().queue();

        if (imageBoards != null) {
            for (BoardImage boardImage : imageBoards) {
                MessageEmbed messageEmbed = new EmbedBuilder()
                        .setColor(RandomColorPick.getColor(random))
                        .setTitle("View in Gelbooru", boardImage.getURL())
                        .setImage(boardImage.getURL())
                        .build();
                messageChannel.sendMessageEmbeds(messageEmbed).queue();
            }
        } else {
            MessageEmbed messageEmbed = new EmbedBuilder()
                .setColor(Color.red)
                .setDescription("None found for: " + tag)
                .build();
            messageChannel.sendMessageEmbeds(messageEmbed).queue();
        }
    }
}




