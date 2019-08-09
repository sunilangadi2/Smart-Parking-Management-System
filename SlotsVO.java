package vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SlotsVO implements Serializable {
	
	private long slotID;
	private ParkingVO parkingID;
	private String slotName,description;
	private AttachmentVO attachmentID;
	public long getSlotID() {
		return slotID;
	}
	public void setSlotID(long slotID) {
		this.slotID = slotID;
	}
	public ParkingVO getParkingID() {
		return parkingID;
	}
	public void setParkingID(ParkingVO parkingID) {
		this.parkingID = parkingID;
	}
	public String getSlotName() {
		return slotName;
	}
	public void setSlotName(String slotName) {
		this.slotName = slotName;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public AttachmentVO getAttachmentID() {
		return attachmentID;
	}
	public void setAttachmentID(AttachmentVO attachmentID) {
		this.attachmentID = attachmentID;
	}

}
