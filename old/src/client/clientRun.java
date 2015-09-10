package client;

import org.telegram.api.engine.TelegramApi;

import main.java.org.telegram.api.engine.AppInfo;

public class clientRun {
	AppInfo ai;
	Callback cb;
	TelegramApi api;
	public clientRun(){
		ai = new AppInfo(14929,"Java","1.8","alpha","EN");
		cb = new Callback();
		api = new TelegramApi(new MyApiStorage(), ai, cb);
	}
}
