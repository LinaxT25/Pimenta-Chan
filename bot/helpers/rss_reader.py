import feedparser


class RSSReader:
    def __init__(self, feed_url):
        self.feed_url = feed_url

    def fetch_feed(self):
        return feedparser.parse(self.feed_url)

    def get_latest_entries(self, count=5):
        feed = self.fetch_feed()
        return feed.entries[:count]
