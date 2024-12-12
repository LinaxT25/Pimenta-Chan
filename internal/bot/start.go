package bot

import (
	"fmt"
	"log"
	"os"
	"os/signal"
	"syscall"

	"github.com/LinaxT25/Pimenta-Chan/internal/commands"
	"github.com/LinaxT25/Pimenta-Chan/internal/handlers"
	"github.com/bwmarrin/discordgo"
)

func Start(session *discordgo.Session) {
	session.Identify.Compress = true
	//session.StateEnabled = true

	session.Identify.Intents = discordgo.IntentGuilds
	session.Identify.Intents = discordgo.IntentsGuildMessages

	session.Identify.Presence.Game.Name = "🌶  Pimenta-Chan  🌶"
	session.Identify.Presence.Game.Type = 0

	err := session.Open()
	if err != nil {
		log.Fatal("Error opening Discord session: ", err)
	}

	commands.SlashCommandsCreate(session)
	handlers.HeadHandlers(session)

	fmt.Println("Bot is now running.  Press CTRL-C to exit.")
	sc := make(chan os.Signal, 1)
	signal.Notify(sc, syscall.SIGINT, syscall.SIGTERM, os.Interrupt)
	<-sc

	session.Close()
}
