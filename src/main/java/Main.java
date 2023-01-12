import BD.DBConnection;
import Colors.AutoColorRole;
import Commands.Core.CommandCall;
import Events.GuildVoiceDisconnect;
import Events.Mentions;
import Events.Ready;
import Music.PlayerManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.Compression;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        DBConnection startDBConnection = new DBConnection();
        JDABuilder token = JDABuilder.createDefault(args[0],
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.DIRECT_MESSAGES,
                GatewayIntent.GUILD_VOICE_STATES
        );
        token.disableCache(
                CacheFlag.MEMBER_OVERRIDES,
                CacheFlag.EMOJI,
                CacheFlag.STICKER,
                CacheFlag.ACTIVITY,
                CacheFlag.FORUM_TAGS,
                CacheFlag.SCHEDULED_EVENTS
        );
        token.setBulkDeleteSplittingEnabled(false);
        token.setCompression(Compression.ZLIB);
        token.setAutoReconnect(true);
        token.setActivity(Activity.playing("\uD83C\uDF36 Pimenta-Chan \uD83C\uDF36"));
        token.setStatus(OnlineStatus.ONLINE);
        JDA bot = token.build();
        bot.addEventListener(new PlayerManager(),
                             new CommandCall(bot),
                             new GuildVoiceDisconnect(bot),
                             new Mentions(),
                             new AutoColorRole(),
                             new Ready()
        );
        bot.awaitReady();
    }
}