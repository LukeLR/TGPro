package alpha;

import java.util.LinkedList;
import java.util.List;

import org.telegram.mtproto.state.AbsMTProtoState;
import org.telegram.mtproto.state.ConnectionInfo;

import logging.Logger;

public class Datacenter {
	private int id = 0;
	private byte[] authKey = null;
	private List<ConnectionInfo> connections = new LinkedList<ConnectionInfo>();
	private AbsMTProtoState state = null;
	
	public Datacenter(int dcId){
		this.id = dcId;
	}
	
	public void setID(int dcId){
		this.id = dcId;
	}
	
	public int getID(){
		return id;
	}
	
	public void setAuthKey(byte[] authKey){
		this.authKey = authKey;
	}
	
	public byte[] getAuthKey(){
		return authKey;
	}
	
	public void addConnection(ConnectionInfo connection){
		connections.add(connection);
	}
	
	public ConnectionInfo[] getConnections(){
		ConnectionInfo[] result = new ConnectionInfo[connections.size()];
		for (int i = 0; i < connections.size(); i++){
			result[i] = connections.get(i);
		}
		return result;
	}
	
	public void setMtProtoState(AbsMTProtoState state){
		this.state = state;
	}
	
	public AbsMTProtoState getMtProtoState(){
		return state;
	}
	
	public void resetAuth(){
		authKey = null; //TODO: Does that really do the trick?
	}
	
	public boolean equals(Object anObject){
		try{
			return ((Datacenter)anObject).getID() == getID();
		} catch (Exception ex){
			Logger.logMessage('E', this, "Couldn't compare Datacenters. Is comparison object a Datacenter?");
			return false;
		}
	}
}
