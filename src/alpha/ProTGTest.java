package alpha;

import java.io.IOException;

import org.telegram.api.TLAbsUpdates;
import org.telegram.api.TLConfig;
import org.telegram.api.engine.ApiCallback;
import org.telegram.api.engine.AppInfo;
import org.telegram.api.engine.TelegramApi;
import org.telegram.api.requests.TLRequestHelpGetConfig;
import org.telegram.mtproto.pq.Authorizer;

public class ProTGTest {
	
	
	public static void main(String[] args) {
		TelegramApi api = new TelegramApi(new MyApiState(),
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
		
		try {
			TLConfig config = api.doRpcCall(new TLRequestHelpGetConfig()); //As proposed by [1], Eclipse wants a try-catch-clause for this
			//TODO: Maybe sending this TLConfig to ApiState?
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

/*
 * [1] https://github.com/ex3ndr/telegram-api#rpc-calls
 *     offline to be found at ProTG/lib/telegram-api/Readme.md#RPC-calls
 */