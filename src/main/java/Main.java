import Commands.SimpleSlashCommands;
import Events.Call;
import Events.Ready;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.utils.Compression;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;

public class Main {
    public static void main(String[] args) throws LoginException, InterruptedException {
        JDABuilder token = JDABuilder.createDefault(args[0]);

        token.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
        token.setBulkDeleteSplittingEnabled(false);
        token.setCompression(Compression.NONE);
        token.setActivity(Activity.playing("\uD83C\uDF36 Pimenta-Chan \uD83C\uDF36"));

        JDA bot = token.build();

        bot.addEventListener(new Ready(),
                             new Call(),
                             new SimpleSlashCommands(bot)
        );

        bot.awaitReady();
    }
}