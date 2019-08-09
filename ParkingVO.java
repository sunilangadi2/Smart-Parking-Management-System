package vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ParkingVO implements Serializable {

	private long parkingID;
	private String parkingName;
	private String addressLine1,addressLine2,landmark,pincode;
	private double mapLat,mapLon,commission;
	private AttachmentVO attachmentID;
	private int status;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getParkingID() {
		return parkingID;
	}
	public void setParkingID(long parkingID) {
		this.parkingID = parkingID;
	}
	public String getParkingName() {
		return parkingName;
	}
	public void setParkingName(String parkingName) {
		this.parkingName = parkingName;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public double getMapLat() {
		return mapLat;
	}
	public void setMapLat(double mapLat) {
		this.mapLat = mapLat;
	}
	public double getMapLon() {
		return mapLon;
	}
	public void setMapLon(double mapLon) {
		this.mapLon = mapLon;
	}
	public double getCommission() {
		return commission;
	}
	public void setCommission(double commission) {
		this.commission = commission;
	}
	public AttachmentVO getAttachmentID() {
		return attachmentID;
	}
	public void setAttachmentID(AttachmentVO attachmentID) {
		this.attachmentID = attachmentID;
	}
	
}
