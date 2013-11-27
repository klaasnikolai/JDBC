package at.atanasoski.jdbcmvn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAO {

	private ResultSet rs;
	private PreparedStatement pstmtSelectByID;
	private PreparedStatement pstmtSelectAll;
	private PreparedStatement pstmtDeleteByName;
	private PreparedStatement pstmtSaveOne;
	private PreparedStatement pstmtUpdate;

	private final String SELECT_BY_ID = "SELECT ID,Vorname, Nachname, Subject "
			+ "FROM Teachers " + "WHERE ID=?";
	private final String SELECT_ALL = "SELECT ID,Vorname, Nachname, Subject "
			+ "FROM Teachers";
	private final String DELETE_BY_NAME = "DELETE FROM Teachers WHERE Vorname=? AND Nachname=?";
	private final String SAVE_ONE = "INSERT INTO Teachers(ID,Vorname, Nachname, Subject)"
			+ " VALUES(?, ?, ?, ?)";
	private final String UPDATE = "UPDATE Teachers SET ID=?, Vorname=?, Nachname=?, Subject=? WHERE Vorname=? AND Nachname=?";

	

	public int saveOneTeacher(Connection con, int ID, String vorname,
			String nachname, String subject) {
		Ensure.ensureInRange(ID, "ID of Teacher");
		Ensure.ensureConnectionNotNull(con, "connection to database");
		try {
			if (pstmtSaveOne == null) {
				pstmtSaveOne = con.prepareStatement(SAVE_ONE);
			}
			pstmtSaveOne.setInt(1, ID);
			pstmtSaveOne.setString(2, vorname);
			pstmtSaveOne.setString(3, nachname);
			pstmtSaveOne.setString(4, subject);
			return pstmtSaveOne.executeUpdate();
		} catch (SQLException sqE) {
			System.out.println(sqE.toString());
			throw new PersistenceException(sqE);
		}
	}
	
	public int deleteTeacherByName(Connection con, String vorname, String nachname) {
		Ensure.ensureConnectionNotNull(con, "connection to database");
		try {
			if (pstmtDeleteByName == null) {
				pstmtDeleteByName = con.prepareStatement(DELETE_BY_NAME);
			}

			pstmtDeleteByName.setString(1, vorname);
			pstmtDeleteByName.setString(2, nachname);
			return pstmtDeleteByName.executeUpdate();

		} catch (SQLException sqE) {
			throw new PersistenceException(sqE);
		}
	}


	public List<Teacher> findAll(Connection con) {
		List<Teacher> list = new ArrayList<Teacher>();
		Ensure.ensureConnectionNotNull(con, "connection to database");
		try {
			if (pstmtSelectAll == null) {
				pstmtSelectAll = con.prepareStatement(SELECT_ALL);
			}
			rs = pstmtSelectAll.executeQuery();
			while (rs.next()) {
				Teacher t = new Teacher();
				t.setID(Integer.valueOf(rs.getString("ID")));
				t.setNachname(rs.getString("Nachname"));
				t.setVorname(rs.getString("Vorname"));
				t.setSubject(rs.getString("Subject"));
				list.add(t);
			}
			rs.close();
		} catch (SQLException sqE) {
			throw new PersistenceException(sqE);
		}

		return list;
	}

}
