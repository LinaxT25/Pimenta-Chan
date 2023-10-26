/* Head file for create a instance of all slash commands and response their calls.*/
package commands

import (
	"fmt"

	"github.com/bwmarrin/discordgo"
)

var cl []*discordgo.ApplicationCommand // Command list

func SlashCommandsCreate(dg *discordgo.Session) {

	cmd, err := dg.ApplicationCommandCreate(dg.State.User.ID, "", GetPingCommand())
	cl = append(cl, cmd)

	if err != nil {
		fmt.Print(cmd)
		panic(err)
	}

}

func ListeningInteractions(dg *discordgo.Session, ic *discordgo.InteractionCreate) {
	for i := 0; i < len(cl); i++ {
		if cl[i].Name == "ping" {
			Ping(dg, ic)
		}
	}
}
