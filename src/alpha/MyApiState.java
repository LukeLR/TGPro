package alpha;

import java.util.LinkedList;
import java.util.List;

import org.telegram.api.TLConfig;
import org.telegram.api.engine.storage.AbsApiState;
import org.telegram.mtproto.state.AbsMTProtoState;
import org.telegram.mtproto.state.ConnectionInfo;
import org.telegram.mtproto.state.KnownSalt;

public class MyApiState implements AbsApiState {
	
	private int primaryDc = 0; //Test configuration "DC 2" (149.154.167.40:443) from https://my.telegram.org/apps for my application, production configuration DC 2: 149.154.167.50:443
	
	private List<Datacenter> authenticatedDCs = new LinkedList<Datacenter>();
	
	private TLConfig config = new TLConfig(); //TODO: Better be null?
	
	public MyApiState(){
		super();
		authenticatedDCs.add(new Datacenter(0));
		/*
		 * Adding a default Datacenter with ID 0, ID 0 is the primaryDC for now. Telegram will
		 * start by looking for the primaryDC, and therefore this default Datacenter will be
		 * asked. This happens at org.telegram.api.engine.TelegramApi.java:887 and :946.
		 * Every Datacenter is given a default connection to DC 2 (149.154.167.40:443) by default,
		 * which is proposed for my developer account at https://my.telegram.org/apps. This is
		 * probably done for testing purposes, since I guess, that telegram should find it's
		 * datacenters on it's own, but for now it makes sure, that the application knows at least
		 * one connection to a telegram server it can use, and should use by default. Before this
		 * was set, the application always threw a NullPointerException, as documented in
		 * ../info/exceptions/exception1.md
		 */
	}

	@Override
	public byte[] getAuthKey(int dcId) {
		int index = authenticatedDCs.indexOf(new Datacenter(dcId));
		if (index != -1){ //Check if a Datacenter with the given ID exists, index is -1 if not
			return authenticatedDCs.get(index).getAuthKey();
		} else {
			return null; //TODO: Returning null might be bad?
		}
	}

	@Override
	public ConnectionInfo[] getAvailableConnections(int dcId) {
		int index = authenticatedDCs.indexOf(new Datacenter(dcId));
		if (index != -1){ //Check if a Datacenter with the given ID exists, index is -1 if not
			return authenticatedDCs.get(index).getConnections();
		} else {
			return null; //TODO: Returning null might be bad?
		}
	}

//	@Override
//	public AbsMTProtoState getMtProtoState(int dcId) {
//		int index = authenticatedDCs.indexOf(dcId);
//		if (index != -1){ //Check if a Datacenter with the given ID exists, index is -1 if not
//			return authenticatedDCs.get(index).getMtProtoState();
//		} else {
//			return null; //TODO: Returning null might be bad?
//		}
//	}

	public synchronized AbsMTProtoState getMtProtoState(final int dcId){
		return new AbsMTProtoState(){
			private KnownSalt[] knownSalts = new KnownSalt[0];
			
			public byte[] getAuthKey(){
				return MyApiState.this.getAuthKey(dcId);
			}
			
			public ConnectionInfo[] getAvailableConnections(){
				return MyApiState.this.getAvailableConnections(dcId);
			}
			
			public KnownSalt[] readKnownSalts(){
				return knownSalts;
			}
			
			protected void writeKnownSalts(KnownSalt[] salts){
				knownSalts = salts;
			}
		};
	}
	
	@Override
	public int getPrimaryDc() {
		return primaryDc;
	}

	@Override
	public boolean isAuthenticated(int dcId) {
		int index = authenticatedDCs.indexOf(new Datacenter(dcId));
		return index != -1;
	}

	@Override
	public void putAuthKey(int dcId, byte[] key) {
		int index = authenticatedDCs.indexOf(new Datacenter(dcId));
		if (index != -1){ //Check if a Datacenter with the given ID exists, index is -1 if not
			authenticatedDCs.get(index).setAuthKey(key);
		}
	}

	@Override
	public void reset() {
		authenticatedDCs.clear(); //TODO: Does that really do the trick?
	}

	@Override
	public void resetAuth() {
		for (int i = 0; i < authenticatedDCs.size(); i++){
			authenticatedDCs.get(i).resetAuth();
		}
		//TODO: Does that really do the trick?
	}

	@Override
	public void setAuthenticated(int dcId, boolean auth) {
		if (auth){
			authenticatedDCs.add(new Datacenter(dcId));
		} else {
			int index = authenticatedDCs.indexOf(new Datacenter(dcId));
			if (index != -1){ //Check if a Datacenter with the given ID exists, index is -1 if not
				authenticatedDCs.remove(index);
			}
		}
	}

	@Override
	public void setPrimaryDc(int ip) {
		primaryDc = ip;
	}

	@Override
	public void updateSettings(TLConfig config) {
		this.config = config;
	}
	
}
