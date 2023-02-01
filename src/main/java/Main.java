import BD.DBConnection;
import Commands.CommandCall;
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
    //TODO if the bot scale up a rebuild is needed to implement sharding
    public static void main(String[] args) throws InterruptedException {
        DBConnection startDBConnection = new DBConnection();
        JDABuilder token = JDABuilder.createDefault(args[0],
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.GUILD_MESSAGE_REACTIONS,
                GatewayIntent.GUILD_VOICE_STATES,
                GatewayIntent.GUILD_BANS
        );
        token.disableCache(
                CacheFlag.STICKER,
                CacheFlag.ACTIVITY,
                CacheFlag.FORUM_TAGS,
                CacheFlag.SCHEDULED_EVENTS
        );
        token.setBulkDeleteSplittingEnabled(false);
        token.setCompression(Compression.ZLIB);
        token.setAutoReconnect(true);
        token.setEnableShutdownHook(true);
        token.setActivity(Activity.playing("\uD83C\uDF36 Pimenta-Chan \uD83C\uDF36"));
        token.setStatus(OnlineStatus.ONLINE);
        JDA bot = token.build();
        bot.addEventListener(new PlayerManager(),
                             new CommandCall(bot),
                             new GuildVoiceDisconnect(bot),
                             new Mentions(),
                             new Ready()
        );
        bot.awaitReady();
    }
}