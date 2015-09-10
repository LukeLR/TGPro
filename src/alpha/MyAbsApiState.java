package alpha;

import org.telegram.api.TLConfig;
import org.telegram.api.engine.storage.AbsApiState;
import org.telegram.mtproto.state.AbsMTProtoState;
import org.telegram.mtproto.state.ConnectionInfo;

public class MyAbsApiState implements AbsApiState {

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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isAuthenticated(int arg0) {
		// TODO Auto-generated method stub
		return false;
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
	public void setAuthenticated(int arg0, boolean arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPrimaryDc(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateSettings(TLConfig arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
