package vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BookingVO implements Serializable {
	private long bookingID;
	private UserVO userID;
	private VehicleVO vehicleID;
	private String inTime;
	private String outTime;
	private ParkingVO parkingID;
	private SlotsVO slotID;
	private PreBookingVO preBookingID;
	private double paymentInitial,paymentFinal;
	public long getBookingID() {
		return bookingID;
	}
	public void setBookingID(long bookingID) {
		this.bookingID = bookingID;
	}
	public UserVO getUserID() {
		return userID;
	}
	public void setUserID(UserVO userID) {
		this.userID = userID;
	}
	public VehicleVO getVehicleID() {
		return vehicleID;
	}
	public void setVehicleID(VehicleVO vehicleID) {
		this.vehicleID = vehicleID;
	}
	public String getInTime() {
		return inTime;
	}
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	public String getOutTime() {
		return outTime;
	}
	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}
	public ParkingVO getParkingID() {
		return parkingID;
	}
	public void setParkingID(ParkingVO parkingID) {
		this.parkingID = parkingID;
	}
	public PreBookingVO getPreBookingID() {
		return preBookingID;
	}
	public void setPreBookingID(PreBookingVO preBookingID) {
		this.preBookingID = preBookingID;
	}
	public double getPaymentInitial() {
		return paymentInitial;
	}
	public void setPaymentInitial(double paymentInitial) {
		this.paymentInitial = paymentInitial;
	}
	public double getPaymentFinal() {
		return paymentFinal;
	}
	public void setPaymentFinal(double paymentFinal) {
		this.paymentFinal = paymentFinal;
	}
	public SlotsVO getSlotID() {
		return slotID;
	}
	public void setSlotID(SlotsVO slotID) {
		this.slotID = slotID;
	}
	
	

}
