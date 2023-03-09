package eecs4413.registration;


public class User {
	private static int totalUsers=0;
	private String firstName = "noNameSet";
	private String password = "noPassSet";
	private String email = "noEmailSet";
	
	//todo private String List<Bids> = ...
	
	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTotalUsers() {
		return totalUsers;
	}

	public void setTotalUsers(int totalUsers) {
		User.totalUsers = totalUsers;
	}



}
