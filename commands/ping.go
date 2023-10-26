package commands

import (
	"fmt"
	"time"

	"github.com/bwmarrin/discordgo"
)

func Ping(dg *discordgo.Session, ic *discordgo.InteractionCreate) {
	if ic.ApplicationCommandData().Name == "ping" {
		var t = time.Now()
		dg.InteractionRespond(ic.Interaction, &discordgo.InteractionResponse{Type: discordgo.InteractionResponsePong})
		var te = time.Since(t).Milliseconds()
		rp := fmt.Sprint("Your latency is: ", te, " ms.")
		dg.InteractionResponseEdit(ic.Interaction, &discordgo.WebhookEdit{
			Content: &rp,
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
