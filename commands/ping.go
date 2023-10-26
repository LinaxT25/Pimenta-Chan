package commands

import (
	"fmt"
	"time"

	"github.com/bwmarrin/discordgo"
)

func Ping(dg *discordgo.Session, i *discordgo.InteractionCreate) {
	if i.ApplicationCommandData().Name == "ping" {
		var t = time.Now()
		dg.ChannelMessageSend(i.ChannelID, "Your latency is:")
		te := time.Since(t).Microseconds()
		dg.InteractionRespond(i.Interaction, &discordgo.InteractionResponse{
			Type: discordgo.InteractionResponseChannelMessageWithSource,
			Data: &discordgo.InteractionResponseData{
				Content: fmt.Sprint(te),
			},
		})
	}
}

func GetPingCommand() *discordgo.ApplicationCommand {
	var p = discordgo.ApplicationCommand{
		Name:        "ping",
		Description: "Return latency in ms.",
	}
	return &p
}
