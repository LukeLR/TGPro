#Exception 3
##About

##Research

##Fix
Re-implement MyApiState: remove Datacenter-Structure, add Hashmaps for connections, keys and authorization states, analogue to org.telegram.bot.engine.MemoryApiState [here][1] and [here][2]. Apply config sent by main class to saved config in MyApiState.

##Exception output

```
TelegramApi#1001:Phase 0 in 1 ms
TelegramApi#1001:Phase 1 in 100 ms
TelegramApi#1001:Timeout Iteration
TelegramApi#1001:Connection iteration
TelegramApi#1001:#0: waitForDc
TelegramApi#1001:#0: Creating proto for dc
TelegramApi#1001:#0: Creating key
TelegramApi#1001:Phase 2 in 4 ms
TelegramApi#1001:Sender iteration
api#1001#Downloader:DownloadFileThread iteration
api#1001#Downloader:DownloadFileThread iteration
api#1001#Downloader:DownloadFileThread iteration
api#1001#Downloader:DownloadFileThread iteration
api#1001#Uploader:UploadFileThread iteration
api#1001#Uploader:UploadFileThread iteration
api#1001#Uploader:UploadFileThread iteration
TelegramApi#1001:Phase 3 in 3 ms
api#1001#Uploader:UploadFileThread iteration
TelegramApi#1001:>> #0: help.getConfig#c4f9186b
TelegramApi#1001:Sender iteration
TelegramApi#1001:Timeout Iteration
TransportRate:Transport: #1 149.154.167.40:443 #1.0
TransportRate:tryConnection #1
TransportRate:onConnectionSuccess #1
TransportRate:Transport: #1 149.154.167.40:443 #1.0
Authorizer:Solved PQ in 35 ms
TelegramApi#1001:#0: auth key == null
org.telegram.api.engine.TimeoutException
	at org.telegram.api.engine.TelegramApi$ConnectionThread.waitForDc(TelegramApi.java:892)
	at org.telegram.api.engine.TelegramApi$ConnectionThread.run(TelegramApi.java:946)
TelegramApi#1001:Connection iteration
Exception in thread "Connection#1439972853" TelegramApi#1001:#0: waitForDc
TelegramApi#1001:#0: Creating proto for dc
java.lang.NullPointerException
	at org.telegram.api.engine.TelegramApi$ConnectionThread.waitForDc(TelegramApi.java:848)
	at org.telegram.api.engine.TelegramApi$ConnectionThread.run(TelegramApi.java:946)
```

[1]: https://github.com/LukeLR/telegram-bot/blob/master/app/src/main/java/org/telegram/bot/engine/MemoryApiState.java
[2]: https://github.com/ex3ndr/telegram-bot/blob/master/app/src/main/java/org/telegram/bot/engine/MemoryApiState.java