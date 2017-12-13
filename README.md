[![Build Status](https://travis-ci.org/Timmeey/neXtc.svg?branch=master)](https://travis-ci.org/Timmeey/neXtc)
# neXtc
Little CoinMarketCap percent change notifier

## What does it do
neXtc informs you about sudden changes in Bitcoin value percent changes according to CoinMarketCap
### WHen does it do that
When the Hour/Day/Week percent change indicator of the bitcoin price in US$
changes over 20%. ONE Subsequent notification will be sent when the change increases over 30%

## How to use
Build the project (maven package) and then start it with the mattermost
[incoming webhook URL](https://docs.mattermost.com/developer/webhooks-incoming.html#simple-incoming-webhook) to send the messages to
as the parameter
```java -jar neXtc.jar "https://your.mattermost.xxx/hooks/xxxxxxx"```


## WHat this is not (yet)
It cannot inform you about anything other than coinmarketcap bitcoin ticket
it cannot use any other notification as mattermost (should be easy to implement though)
