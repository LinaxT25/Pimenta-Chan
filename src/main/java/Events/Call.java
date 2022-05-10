package Events;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Call extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if(event.getMessage().getMentionedUsers().toString().contains("Pimenta-Chan")) {
            event.getChannel().sendMessage("Senpai!!!").queue();
        }

        //Creating a file with received private messages
        if(event.isFromType(ChannelType.PRIVATE)) {
            try {
                File LogMessages = new File("MessagesReceived.txt");
                FileWriter WritingContents =  new FileWriter("MessagesReceived.txt",true);

                if(LogMessages.createNewFile())
                    System.out.println("PM received, created a new file with the contents.");
                else
                    System.out.println("PM received, adding to file the contents.");

                String MessageAuthor = event.getAuthor().getName();
                String MessageContent = event.getMessage().getContentDisplay();
                String PrivateMessage = "Private message received from: " + MessageAuthor + "\n" + MessageContent + "\n";

                WritingContents.write(PrivateMessage);
                WritingContents.close();
            } catch (IOException error) {
                System.out.println("A error occurred.");
                error.printStackTrace();
            }

        }
    }
}
