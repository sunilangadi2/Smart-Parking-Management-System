package vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TransactionVO implements Serializable{

	private long transactionID;
	private UserVO userID;
	private int transactionType;
	private String sentTime;
	private String description;
	public long getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(long transactionID) {
		this.transactionID = transactionID;
	}
	public UserVO getUserID() {
		return userID;
	}
	public void setUserID(UserVO userID) {
		this.userID = userID;
	}
	public int getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(int transactionType) {
		this.transactionType = transactionType;
	}
	public String getSentTime() {
		return sentTime;
	}
	public void setSentTime(String sentTime) {
		this.sentTime = sentTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
