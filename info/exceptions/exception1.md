#Exception 1
##About
This exception occured when starting the telegram application in its early stages, right at the point where it should download a configuration from the servers.

##Research
After a little research in the code, I found out that the NullPointerException came from an ConnectionInfo-Array that was set to null. The ConnectionInfo-Object was returned by the ApiState, which was passed to the Telegram Api on it's initialisation. I also found out, that the Telegram Api questions the Api State for the default DC when a RPC-call is made. I thought, that this might occur, because my Api State class returns a null pointer if it is asked for the Connection Information on a DC with an ID that it doesn't know, or that the DC is known, but it has no Connections saved.

##Fix
I added a line to the Constructor of Datacenter.class, in which to every Datacenter a Connection to a standard DC is saved. I took the DC from my developer information at https://my.telegram.org/apps, which was DC 2 for me. The test-DC 2 has the IP 149.154.167.40:443, the production equivalent for DC 2 has the IP 149.154.167.50:443. I took the test DC 2 as a default connection for every instance of the Datacenter class. Furthermore, I added a constructor to the MyApiState class, in which a default Datacenter is added, with the ID of the primary Datacenter. Because the Datacenter with the ID saved as the primary Datacenter gets questioned, and each Datacenter now saves a default connection, this should fix it probably.

##Exception output
```
TelegramApi#1001:Phase 0 in 1 ms
TelegramApi#1001:Phase 1 in 100 ms
TelegramApi#1001:Timeout Iteration
TelegramApi#1001:Connection iteration
TelegramApi#1001:#0: waitForDc
TelegramApi#1001:#0: Creating proto for dc
TelegramApi#1001:Phase 2 in 3 ms
TelegramApi#1001:Sender iteration
Exception in thread "Connection#1926725945" java.lang.NullPointerException
	at org.telegram.api.engine.TelegramApi$ConnectionThread.waitForDc(TelegramApi.java:848)
	at org.telegram.api.engine.TelegramApi$ConnectionThread.run(TelegramApi.java:946)
api#1001#Downloader:DownloadFileThread iteration
api#1001#Downloader:DownloadFileThread iteration
api#1001#Downloader:DownloadFileThread iteration
api#1001#Downloader:DownloadFileThread iteration
api#1001#Uploader:UploadFileThread iteration
api#1001#Uploader:UploadFileThread iteration
TelegramApi#1001:Phase 3 in 4 ms
api#1001#Uploader:UploadFileThread iteration
api#1001#Uploader:UploadFileThread iteration
TelegramApi#1001:>> #0: help.getConfig#c4f9186b
TelegramApi#1001:Sender iteration
TelegramApi#1001:Timeout Iteration
```