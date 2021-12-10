import Commands.SimpleSlashCommands;
import Events.Call;
import Events.Ready;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.Compression;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;

public class Main {
    public static void main(String[] args) throws LoginException, InterruptedException {
        JDABuilder token = JDABuilder.createDefault(args[0], GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES);

        // Disable parts of the cache
        token.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
        // Enable the bulk delete event
        token.setBulkDeleteSplittingEnabled(false);
        // Disable compression (not recommended)
        token.setCompression(Compression.NONE);
        // Set activity (like "playing Something")
        token.setActivity(Activity.playing("Pimenta-Chan"));

        token.addEventListeners(new Ready());
        token.addEventListeners(new Call());

        JDA bot = token.build();

        bot.awaitReady();

        bot.addEventListener(new SimpleSlashCommands(bot));

    }
}