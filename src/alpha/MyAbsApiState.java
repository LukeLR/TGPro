package alpha;

import java.util.LinkedList;
import java.util.List;

import org.telegram.api.TLConfig;
import org.telegram.api.engine.storage.AbsApiState;
import org.telegram.mtproto.state.AbsMTProtoState;
import org.telegram.mtproto.state.ConnectionInfo;

public class MyAbsApiState implements AbsApiState {
	
	private int primaryDc = 2; //Test configuration "DC 2" (149.154.167.40:443) from https://my.telegram.org/apps for my application, production configuration DC 2: 149.154.167.50:443
	
	private List<Integer> authenticatedDCs = new LinkedList<Integer>();

	@Override
	public byte[] getAuthKey(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConnectionInfo[] getAvailableConnections(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbsMTProtoState getMtProtoState(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPrimaryDc() {
		return primaryDc;
	}

	@Override
	public boolean isAuthenticated(int dcId) {
		int index = authenticatedDCs.indexOf(dcId);
		return index != -1;
	}

	@Override
	public void putAuthKey(int arg0, byte[] arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetAuth() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAuthenticated(int dcId, boolean auth) {
		if (auth){
			authenticatedDCs.add(dcId);
		} else {
			int index = authenticatedDCs.indexOf(dcId);
			if (index != -1){
				authenticatedDCs.remove(index);
			}
		}
	}

	@Override
	public void setPrimaryDc(int ip) {
		primaryDc = ip;
	}

	@Override
	public void updateSettings(TLConfig arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
