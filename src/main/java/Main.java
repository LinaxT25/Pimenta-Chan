import Colors.AutoColorRole;
import Commands.SlashCommandCall;
import Commands.SlashCommands;
import Events.Call;
import Events.Ready;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.Compression;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;

public class Main {
    public static void main(String[] args) throws LoginException, InterruptedException {
        JDABuilder token = JDABuilder.createDefault(args[0],
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.DIRECT_MESSAGES
        );

        token.disableCache(CacheFlag.MEMBER_OVERRIDES,
                CacheFlag.VOICE_STATE,
                CacheFlag.EMOTE
        );

        token.setBulkDeleteSplittingEnabled(false);
        token.setCompression(Compression.ZLIB);
        token.setAutoReconnect(true);
        token.setActivity(Activity.playing("\uD83C\uDF36 Pimenta-Chan \uD83C\uDF36"));
        token.setStatus(OnlineStatus.ONLINE);

        JDA bot = token.build();

        bot.addEventListener(new Ready(),
                             new Call(),
                             new SlashCommands(bot),
                             new SlashCommandCall(),
                             new AutoColorRole()
        );

        bot.awaitReady();
    }
}