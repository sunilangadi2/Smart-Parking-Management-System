package vo;

public class ParkingChargeVO {

	private long parkingChargeID;
	private float lowerLimit,upperLimit,charge;
	private ParkingVO parkingID;
	public long getParkingChargeID() {
		return parkingChargeID;
	}
	public void setParkingChargeID(long parkingChargeID) {
		this.parkingChargeID = parkingChargeID;
	}
	public float getLowerLimit() {
		return lowerLimit;
	}
	public void setLowerLimit(float lowerLimit) {
		this.lowerLimit = lowerLimit;
	}
	public float getUpperLimit() {
		return upperLimit;
	}
	public void setUpperLimit(float upperLimit) {
		this.upperLimit = upperLimit;
	}
	public float getCharge() {
		return charge;
	}
	public void setCharge(float charge) {
		this.charge = charge;
	}
	public ParkingVO getParkingID() {
		return parkingID;
	}
	public void setParkingID(ParkingVO parkingID) {
		this.parkingID = parkingID;
	}
	
	
}
