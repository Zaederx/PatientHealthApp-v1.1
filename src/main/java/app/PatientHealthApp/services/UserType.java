package app.PatientHealthApp.services;

public enum UserType {
	
	doctor(0),
	patient(1),
	admin(2);
	
	public int num;
	UserType (int num) {
		this.num = num;
	}

}
