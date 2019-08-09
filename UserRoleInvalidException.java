package vo;

public class UserRoleInvalidException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Invalid Input for User Role , Enter value from ADMIN-3, EMPLOYEE -2, REGISTERED_USER -1 DEFAULT VALUE ASSIGNED";
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		
		
		return "UserRoleInvalidException";
	}
	
}
