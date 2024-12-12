package handlers

import (
	"github.com/LinaxT25/Pimenta-Chan/internal/commands"
	"github.com/bwmarrin/discordgo"
)

func HeadHandlers(dg *discordgo.Session) {

	dg.AddHandler(commands.ListeningInteractions)

}
