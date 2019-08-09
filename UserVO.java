package vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UserVO implements Serializable {

	private long userID;
	private String userName,password;
	private PersonVO personID;
	private int userType;
	private AttachmentVO attachmentID;
	private int status;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getUserType() 
	{
		return userType;
	}
	public String getUserTypeName() {
		
		return UserRole.fromInt(userType).toString();
	}
	
	public void setUserType(int userType) {
		if(userType>3 && userType<0){
			try {
				throw new UserRoleInvalidException();
			} catch (UserRoleInvalidException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
		this.userType = userType;
	}
	public void setUserType(UserRole userRole) {
		this.userType = userRole.toInt();
	}
	public long getUserID() {
		return userID;
	}
	public void setUserID(long userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public PersonVO getPersonID() {
		return personID;
	}
	public void setPersonID(PersonVO personID) {
		this.personID = personID;
	}
	public AttachmentVO getAttachmentID() {
		return attachmentID;
	}
	public void setAttachmentID(AttachmentVO attachmentID) {
		this.attachmentID = attachmentID;
	}
	
}
