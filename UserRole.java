package vo;


public enum UserRole {
	ADMIN(3),
	EMPLOYEE(2),
	REGISTERED_USER(1);
	
	private int value;
	UserRole(int v){
		this.value=v;
		
	}
	
	public int getUserRoleValue(){
		return value;
	}
	
	// the identifierMethod
			public int toInt() {
				return value;
			}

			// the valueOfMethod
			public static UserRole fromInt(int value) {
				switch (value) {
				case 1:
					return REGISTERED_USER;
				case 2:
					return EMPLOYEE;
				case 3:
					return ADMIN;
				default:
					return REGISTERED_USER;
				}
			}

			public String toString() {
				switch (this) {
				case ADMIN:
					return "ADMIN";
				case EMPLOYEE:
					return "EMPLOYEE";
				case REGISTERED_USER:
					return "REGISTERED_USER";
				}

				return "REGISTERED_USER";
			}

			public int getValue() {
				return value;
			}

			public void setValue(int value) {
				this.value = value;
			}
			
}
