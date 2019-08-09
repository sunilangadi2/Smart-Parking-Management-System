package vo;

import java.io.Serializable;

public class AttachmentVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long attachmentID;
	private String attachmentType;
	private String path;

	
	
	public long getAttachmentID() {
		return attachmentID;
	}

	public void setAttachmentID(long attachmentID) {
		this.attachmentID = attachmentID;
	}

	public String getAttachmentType() {
		return attachmentType;
	}

	public void setAttachmentType(String attachmentType) {
		this.attachmentType = attachmentType;
	}
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	
	

}
