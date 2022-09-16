import Colors.AutoColorRole;
import Commands.SlashCommandResponse;
import Commands.SlashCommands;
import Events.Mentions;
import Events.Ready;
import Music.MusicPlayer;
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
                GatewayIntent.DIRECT_MESSAGES,
                GatewayIntent.GUILD_VOICE_STATES
        );
        token.disableCache(CacheFlag.MEMBER_OVERRIDES,
                CacheFlag.EMOJI,
                CacheFlag.STICKER
        );
        token.setBulkDeleteSplittingEnabled(false);
        token.setCompression(Compression.ZLIB);
        token.setAutoReconnect(true);
        token.setActivity(Activity.playing("\uD83C\uDF36 Pimenta-Chan \uD83C\uDF36"));
        token.setStatus(OnlineStatus.ONLINE);
        JDA bot = token.build();
        bot.addEventListener(new Ready(),
                             new Mentions(),
                             new SlashCommands(bot),
                             new SlashCommandResponse(),
                             new AutoColorRole(),
                             new MusicPlayer()
        );
        bot.awaitReady();
    }
}