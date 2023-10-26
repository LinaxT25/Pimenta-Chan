/* Head file for create a instance of all slash commands */
package commands

import (
	"fmt"

	"github.com/bwmarrin/discordgo"
)

func SlashCommandsCreate(dg *discordgo.Session) {

	cmd, err := dg.ApplicationCommandCreate(dg.State.User.ID, "", GetPingCommand())

	if err != nil {
		fmt.Print(cmd)
		panic(err)
	}

}
