package vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ParkingEmployeeVO implements Serializable {
	
	private ParkingVO parkingID;
	private UserVO userID;
	private long parkingEmployeeID;
	
	public ParkingVO getParkingID() {
		return parkingID;
	}
	public void setParkingID(ParkingVO parkingID) {
		this.parkingID = parkingID;
	}
	public UserVO getUserID() {
		return userID;
	}
	public void setUserID(UserVO userID) {
		this.userID = userID;
	}
	public long getParkingEmployeeID() {
		return parkingEmployeeID;
	}
	public void setParkingEmployeeID(long parkingEmployeeID) {
		this.parkingEmployeeID = parkingEmployeeID;
	}

}
