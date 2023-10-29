package commands

import (
	"fmt"
	"time"

	"github.com/bwmarrin/discordgo"
)

func PingCommand(dg *discordgo.Session, ic *discordgo.InteractionCreate) {
	t := time.Now()
	err := dg.InteractionRespond(ic.Interaction, &discordgo.InteractionResponse{Type: discordgo.InteractionResponsePong})

	if err != nil {
		fmt.Println("Caught error: ", err)
		return
	}

	te := time.Since(t).Milliseconds()
	rp := fmt.Sprint("Your latency is: ", te, " ms.")

	msg, err := dg.InteractionResponseEdit(ic.Interaction, &discordgo.WebhookEdit{
		Content: &rp,
	})

	if err != nil {
		fmt.Println("Caught error: ", err, " sending:", &msg)
		return
	}
}

func Ping() *discordgo.ApplicationCommand {
	var p = discordgo.ApplicationCommand{
		Name:        "ping",
		Description: "Return latency in ms.",
	}
	return &p
}
