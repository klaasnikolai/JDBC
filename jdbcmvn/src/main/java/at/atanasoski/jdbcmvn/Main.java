package at.atanasoski.jdbcmvn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		Statement s;
		TeacherDAO dao = new TeacherDAO();
		ResultSet rs;
		String printLine = "  __________________________________________________";

		String driver = "org.apache.derby.jdbc.EmbeddedDriver";
		// the database name
		String dbName = "firstDB";
		// define the Derby connection URL to use
		String connectionURL = "jdbc:derby:" + dbName + ";create=true";

		Connection conn = null;
		try {
			/*
			 * * Load the Derby driver.* When the embedded Driver is used this
			 * action start the Derby engine.* Catch an error and suggest a
			 * CLASSPATH problem
			 */
			Class.forName(driver);
			System.out.println(driver + " loaded. ");
		} catch (java.lang.ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: ");
			System.err.println(e.getMessage());
			System.out
					.println("\n    >>> Please check your CLASSPATH variable   <<<\n");
		}
		try {
			conn = DriverManager.getConnection(connectionURL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Connected to database " + dbName);

		System.out.println("Get all Teachers");
		List<Teacher> list;
		list = dao.findAll(conn);

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
		System.out.println(printLine);

		System.out.println("Save new Teacher");
		dao.saveOneTeacher(conn, 3, "Thomas", "Tschernko", "Programmieren");

		list = dao.findAll(conn);

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
		System.out.println(printLine);

		System.out.println("Delete the new Teacher");
		dao.deleteTeacherByName(conn, "Thomas", "Tschernko");

		list = dao.findAll(conn);

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
		System.out.println(printLine);

	}
}
