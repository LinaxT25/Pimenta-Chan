import os, nextcord

from dotenv import load_dotenv
from nextcord.client import Client

load_dotenv()
bot = Client()

@bot.event
async def on_ready():
    print(f'Bot online como {bot.user}')

bot.run(os.getenv("TOKEN"))