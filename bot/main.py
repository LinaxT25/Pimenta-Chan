import discord
import os
from discord.ext import commands
from dotenv import load_dotenv

load_dotenv()


class PimentaChan:
    def __init__(self):
        intents = discord.Intents.default()
        intents.message_content = True
        self.bot = commands.Bot(command_prefix=">", intents=intents)

    def run(self):
        self.bot.run(os.environ.get("DISCORD_BOT_TOKEN"))
