package main

import (
	"log"
	"os"

	"github.com/LinaxT25/Pimenta-Chan/internal/bot"
	"github.com/bwmarrin/discordgo"
	"github.com/joho/godotenv"
)

func init() {
	err := godotenv.Load(".env")
	if err != nil {
		log.Fatal("Error loading .env file")
	}
}

func main() {
	dotenv := os.Getenv("TOKEN")
	dg, err := discordgo.New("Bot " + dotenv)
	if err != nil {
		log.Fatal("Error creating Discord session: ", err)
	}
	bot.Start(dg)
}
