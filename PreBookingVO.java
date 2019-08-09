package vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PreBookingVO implements Serializable {

	private long preBookingID;
	private PersonVO personID;
	private UserVO userID;
	private VehicleVO vehicleID;
	private ParkingVO parkingID;
	private SlotsVO slotID;
	private String bookingTime,parkingTime,extendedTime1,extendedTime2;
	private int status;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getPreBookingID() {
		return preBookingID;
	}
	public void setPreBookingID(long preBookingID) {
		this.preBookingID = preBookingID;
	}

	public VehicleVO getVehicleID() {
		return vehicleID;
	}
	public void setVehicleID(VehicleVO vehicleID) {
		this.vehicleID = vehicleID;
	}
	public String getBookingTime() {
		return bookingTime;
	}
	public void setBookingTime(String bookingTime) {
		this.bookingTime = bookingTime;
	}
	public String getParkingTime() {
		return parkingTime;
	}
	public void setParkingTime(String parkingTime) {
		this.parkingTime = parkingTime;
	}
	public String getExtendedTime1() {
		return extendedTime1;
	}
	public void setExtendedTime1(String extendedTime1) {
		this.extendedTime1 = extendedTime1;
	}
	public String getExtendedTime2() {
		return extendedTime2;
	}
	public void setExtendedTime2(String extendedTime2) {
		this.extendedTime2 = extendedTime2;
	}
	public PersonVO getPersonID() {
		return personID;
	}
	public void setPersonID(PersonVO personID) {
		this.personID = personID;
	}
	public UserVO getUserID() {
		return userID;
	}
	public void setUserID(UserVO userID) {
		this.userID = userID;
	}
	public ParkingVO getParkingID() {
		return parkingID;
	}
	public void setParkingID(ParkingVO parkingID) {
		this.parkingID = parkingID;
	}
	public SlotsVO getSlotID() {
		return slotID;
	}
	public void setSlotID(SlotsVO slotID) {
		this.slotID = slotID;
	}
}
