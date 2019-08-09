package vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class VehicleVO implements Serializable{
	
	@Override
	public String toString() {
		return "VehicleVO [vehicleID=" + vehicleID + ", model=" + model
				+ ", numberOfVehicle=" + numberOfVehicle + ", userID=" + userID
				+ "]";
	}
	private long vehicleID;
	private String model,numberOfVehicle;
	private UserVO userID;
	public long getVehicleID() {
		return vehicleID;
	}
	public void setVehicleID(long vehicleID) {
		this.vehicleID = vehicleID;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getNumberOfVehicle() {
		return numberOfVehicle;
	}
	public void setNumberOfVehicle(String numberOfVehicle) {
		this.numberOfVehicle = numberOfVehicle;
	}
	
	public UserVO getUserID() {
		return userID;
	}
	public void setUserID(UserVO userID) {
		this.userID = userID;
	}
	
	

}
