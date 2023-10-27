package main

import (
	"flag"
	"fmt"
	"os"
	"os/signal"
	"syscall"

	"github.com/LinaxT25/Pimenta-Chan/commands"
	"github.com/LinaxT25/Pimenta-Chan/handlers"
	"github.com/bwmarrin/discordgo"
)

// Variables used for command line parameters
var Token string

func init() {
	flag.StringVar(&Token, "t", "", "Bot Token")
	flag.Parse()
}

func main() {
	// Create a new Discord session using the provided bot token.
	dg, err := discordgo.New("Bot " + Token)
	if err != nil {
		fmt.Println("Error creating Discord session! ", err)
		return
	}

	dg.Identify.Compress = true
	//dg.StateEnabled = true

	dg.Identify.Intents = discordgo.IntentGuilds
	dg.Identify.Intents = discordgo.IntentsGuildMessages
	dg.Identify.Intents = discordgo.IntentsGuildBans

	dg.Identify.Presence.Game.Name = "🌶  Pimenta-Chan  🌶"
	dg.Identify.Presence.Game.Type = 0

	// Open a websocket connection to Discord and begin listening.
	err = dg.Open()

	if err != nil {
		panic(err)
	}

	commands.SlashCommandsCreate(dg) // Update & creating all slashing commands
	handlers.HeadHandlers(dg)        // Calling all handlers to listening.

	// Wait here until CTRL-C or other term signal is received.
	fmt.Println("Bot is now running.  Press CTRL-C to exit.")
	sc := make(chan os.Signal, 1)
	signal.Notify(sc, syscall.SIGINT, syscall.SIGTERM, os.Interrupt)
	<-sc

	dg.Close()
}
