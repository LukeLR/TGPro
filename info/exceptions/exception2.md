#Exception 2
##About
This exception occured when starting the telegram application in its early stages.

##Research
I found out, that the NullPointerException was caused in a line where the ApiState was asked for a DC's MTProtoState (for checking Server salts). These were all null for every DC by default, if not set manually

##Fix
I rewrote the getMTProtoState(int dcId)-Method in alpha.MyApiState to follow the scheme of the getMTProtoState-Method from the org.telegram.bot.engine.MemoryApiState from the telegram bot (I used this as an example for learning, found [here](1) or (my fork here)[2]). 

```
TelegramApi#1001:Phase 0 in 1 ms
TelegramApi#1001:Phase 1 in 96 ms
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
TelegramApi#1001:Timeout Iteration
TelegramApi#1001:Sender iteration
TransportRate:Transport: #1 149.154.167.40:443 #1.0
TransportRate:tryConnection #1
TransportRate:onConnectionSuccess #1
TransportRate:Transport: #1 149.154.167.40:443 #1.0
Authorizer:Solved PQ in 76 ms
Exception in thread "Connection#1420482809" java.lang.NullPointerException
	at org.telegram.api.engine.TelegramApi$ConnectionThread.waitForDc(TelegramApi.java:887)
	at org.telegram.api.engine.TelegramApi$ConnectionThread.run(TelegramApi.java:946)
TelegramApi#1001:Timeout Iteration
org.telegram.api.engine.TimeoutException
TelegramApi#1001:RPC #0: Timeout (15001 ms)
TelegramApi#1001:Timeout Iteration
0 [W] - [Logger][logMessage]: Log for channel default does not exist. Trying to create a new one!
	at org.telegram.api.engine.TelegramApi.doRpcCall(TelegramApi.java:369)
	at org.telegram.api.engine.TelegramApi.doRpcCall(TelegramApi.java:309)
	at org.telegram.api.engine.TelegramApi.doRpcCall(TelegramApi.java:400)
	at org.telegram.api.engine.TelegramApi.doRpcCall(TelegramApi.java:396)
	at alpha.ProTGTest.main(ProTGTest.java:52)
0 [I] - [alpha.ProTGTest][logMessage]: Here I am!
TelegramApi#1001:>> #1: auth.checkPhone#6fe51dfb
TelegramApi#1001:Timeout Iteration
TelegramApi#1001:Sender iteration
```

[1] https://github.com/ex3ndr/telegram-bot
[2] https://github.com/LukeLR/telegram-bot