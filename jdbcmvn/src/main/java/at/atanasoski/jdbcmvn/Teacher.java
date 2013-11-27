package at.atanasoski.jdbcmvn;

public class Teacher {
	private int ID;
	private String Vorname;
	private String Nachname;
	private String Subject;
	
	public Teacher(int iD, String vorname, String nachname, String subject) {
		super();
		ID = iD;
		Vorname = vorname;
		Nachname = nachname;
		Subject = subject;
	}

	public Teacher() {
		super();
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getVorname() {
		return Vorname;
	}

	public void setVorname(String vorname) {
		Vorname = vorname;
	}

	public String getNachname() {
		return Nachname;
	}

	public void setNachname(String nachname) {
		Nachname = nachname;
	}

	public String getSubject() {
		return Subject;
	}

	public void setSubject(String subject) {
		Subject = subject;
	}

	@Override
	public String toString() {
		return "Teacher [ID=" + ID + ", Vorname=" + Vorname + ", Nachname="
				+ Nachname + ", Subject=" + Subject + "]";
	}
	
	
	

}
