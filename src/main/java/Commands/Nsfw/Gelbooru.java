package Commands.Nsfw;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.kodehawa.lib.imageboards.DefaultImageBoards;
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
        //Looking for a random number for pages, 1 and 20 should be average
        int random = new Random().ints(0, 20).findFirst().getAsInt();

        MessageChannelUnion messageChannel= event.getChannel();
        String tag = event.getOption("tag").getAsString();

        MessageEmbed messageEmbed = new EmbedBuilder()
                .setColor(Color.red)
                .setDescription("Searching...")
                .build();
        event.replyEmbeds(messageEmbed).queue();

        List<GelbooruImage> imageBoards = DefaultImageBoards.GELBOORU.search(
                        random, event.getOption("number").getAsInt(), event.getOption("tag").getAsString())
                        .blocking();

        if (imageBoards != null) {
            for (BoardImage boardImage : imageBoards)
                messageChannel.sendMessage(boardImage.getURL()).queue();
        } else {
            messageEmbed = new EmbedBuilder()
                .setColor(Color.red)
                .setDescription("None found for: " + tag)
                .build();
            messageChannel.sendMessageEmbeds(messageEmbed).queue();
        }
    }
}




