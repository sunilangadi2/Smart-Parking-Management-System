package vo;

public class UserLogVO {
	private long logID;
	private UserVO userID;
	private String loginTime;
	private int userType;
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
	public long getLogID() {
		return logID;
	}
	public void setLogID(long logID) {
		this.logID = logID;
	}
	public UserVO getUserID() {
		return userID;
	}
	public void setUserID(UserVO userID) {
		this.userID = userID;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
}
