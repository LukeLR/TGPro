package alpha;

import java.util.LinkedList;
import java.util.List;

import org.telegram.api.TLConfig;
import org.telegram.api.engine.storage.AbsApiState;
import org.telegram.mtproto.state.AbsMTProtoState;
import org.telegram.mtproto.state.ConnectionInfo;

public class MyApiState implements AbsApiState {
	
	private int primaryDc = 2; //Test configuration "DC 2" (149.154.167.40:443) from https://my.telegram.org/apps for my application, production configuration DC 2: 149.154.167.50:443
	
	private List<Datacenter> authenticatedDCs = new LinkedList<Datacenter>();
	
	private TLConfig config = new TLConfig(); //TODO: Better be null?

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

	@Override
	public AbsMTProtoState getMtProtoState(int dcId) {
		int index = authenticatedDCs.indexOf(dcId);
		if (index != -1){ //Check if a Datacenter with the given ID exists, index is -1 if not
			return authenticatedDCs.get(index).getMtProtoState();
		} else {
			return null; //TODO: Returning null might be bad?
		}
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
