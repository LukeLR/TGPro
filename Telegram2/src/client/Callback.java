package client;

import org.telegram.api.TLAbsUpdates;

import main.java.org.telegram.api.engine.ApiCallback;
import main.java.org.telegram.api.engine.TelegramApi;

public class Callback implements ApiCallback {

	public void onAuthCancelled(TelegramApi api) {
		// When auth key or user authorization dies
		System.out.println("Auth died!");
	}

	public void onUpdatesInvalidated(TelegramApi api) {
		// When api engine expects that update sequence might be broken
		System.out.println("Updates Invalidated!")
	}

	public void onUpdate(TLAbsUpdates updates) {
		System.out.println("Update!");
	}

}
