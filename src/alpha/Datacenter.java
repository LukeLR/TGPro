package alpha;

import logging.Logger;

public class Datacenter {
	private int id = 0;
	private byte[] authKey = null;
	
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
	
	public boolean equals(Object anObject){
		try{
			return ((Datacenter)anObject).getID() == getID();
		} catch (Exception ex){
			Logger.logMessage('E', this, "Couldn't compare Datacenters. Is comparison object a Datacenter?");
			return false;
		}
	}
}
