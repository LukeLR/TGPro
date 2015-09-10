package alpha;

import java.io.IOException;

import logging.Logger;

import org.telegram.api.TLAbsUpdates;
import org.telegram.api.TLConfig;
import org.telegram.api.auth.TLCheckedPhone;
import org.telegram.api.engine.ApiCallback;
import org.telegram.api.engine.AppInfo;
import org.telegram.api.engine.TelegramApi;
import org.telegram.api.requests.TLRequestAuthCheckPhone;
import org.telegram.api.requests.TLRequestHelpGetConfig;
import org.telegram.mtproto.pq.Authorizer;

public class ProTGTest {
	
	public static void main(String[] args) {
		MyApiState state = new MyApiState();
		TelegramApi api = new TelegramApi(state,
				new AppInfo(14929, "myDeviceModel", "mySystemVersion", "myAppVersion", "myLangCode"),
				new ApiCallback(){ //Error: Cannot instantiate ApiCallback, opening { seems to fix. [1]
			
					//@Override //Proposed by [1], but doesn't work here (?)
					public void onApiDies(TelegramApi api){
						// When auth key or user authorization dies
						System.out.println("Api died");
					}
					
					@Override //Proposed by [1], but seemed to work without (?)
					public void onUpdatesInvalidated(TelegramApi api){
						// When api engine expects that update sequence might be broken
						System.out.println("Update sequence broken? Updates Invalidated.");
					}
		
					@Override
					public void onAuthCancelled(TelegramApi api) { //These methods are not present in [1], but proposed by eclipse (implement missing methods)
						System.out.println("Auth cancelled");				
					}
		
					@Override
					public void onUpdate(TLAbsUpdates updates) { //These methods are not present in [1], but proposed by eclipse (implement missing methods)
						System.out.println("Auth cancelled");
					}
				}
		);
		
//		api.switchToDc(1);
		
		try {
			state.updateSettings(api.doRpcCallNonAuth(new TLRequestHelpGetConfig())); //As proposed by [1]. Eclipse wants a try-catch-clause for this. Updating ApiState with new settings now, analogue to [2].
			//TODO: Maybe sending this TLConfig to ApiState?
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//Doing stuff on my own now, since [1] doesn't reach any further.
		Logger.logMessage('I', new ProTGTest(), "Here I am!");
		try {
			TLCheckedPhone myPhone = api.doRpcCall(new TLRequestAuthCheckPhone("+4915781664674"));
			Logger.logMessage('I', new ProTGTest(), "Info for phone: Invited: " + String.valueOf(myPhone.getPhoneInvited()) + ", registered: " + String.valueOf(myPhone.getPhoneRegistered()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

/*
 * [1] https://github.com/ex3ndr/telegram-api#rpc-calls
 *     offline to be found at ProTG/lib/telegram-api/Readme.md#RPC-calls
 * [2] https://github.com/LukeLR/telegram-bot/blob/master/app/src/main/java/org/telegram/bot/Application.java login()-method
 *     https://github.com/ex3ndr/telegram-bot/blob/master/app/src/main/java/org/telegram/bot/Application.java login()-method
 */