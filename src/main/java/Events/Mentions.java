package Events;

import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/* Trigger events to respond at any mention to bot */
public class Mentions extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(event.getMessage().getMentions().toString().contains("Pimenta-Chan")) {
            event.getChannel().sendMessage("Senpai!!!").queue();
        }
        //Creating a file with received private messages
        if(event.isFromType(ChannelType.PRIVATE)) {
            // Removed to prevent any security breach
            /*
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
            */
        }
    }
}
