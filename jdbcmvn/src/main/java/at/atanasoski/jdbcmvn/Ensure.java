package at.atanasoski.jdbcmvn;

import java.sql.Connection;
import java.util.Date;
import java.util.List;



//TODO miteinbinden des RegularExpressionMatcher
/**
 * Die Klasse Ensure enthaelt methoden, welche pruefen, ob das Objekt bzw. der
 * Datentyp null oder empty ist und returned das Objekt bzw. den Datentyp wenn
 * er nicht null oder empty ist. Andernfalls wird eine Exception geworfen.
 */
public class Ensure {

	// for not null or empty
	public static char[] ensureNotNullOrEmpty(char[] charArray, String paramName) {
		if (charArray == null || charArray.length == 0) {
			throw new IllegalArgumentException(String.format(
					"%s must not be null or empty", paramName));
		}
		return charArray;
	}

	public static String ensureNotNullOrEmpty(String var, String paramName) {
		if (var == null || var.length() == 0) {
			throw new IllegalArgumentException(String.format(
					"%s must not be null or empty", paramName));
		}
		return var;
	}

	public static Date ensureNotNullOrEmpty(Date var, String paramName) {
		if (var == null) {
			throw new IllegalArgumentException(String.format(
					"%s must not be null or empty", paramName));
		}
		return var;
	}

	/**
	 * checks wheter the date is not null and the date is after the current date
	 */
	public static Date ensureNotBeforeActualDate(Date var, String paramName) {
		Date today = new Date();
		if (var.before(today)) {
			throw new IllegalArgumentException(String.format(
					"%s must not be before the current date",
					paramName));
		}
		return var;
	}

	public static int ensureInRange(int var, String paramName) {
		if (var <= 0 || var > 36) {
			throw new IllegalArgumentException(String.format(
					"%s must be in range (1-36)", paramName));
		}
		return var;
	}

	public static Teacher ensureNotNullOrEmpty(Teacher var, String paramName) {
		if (var == null) {
			throw new IllegalArgumentException(String.format(
					"%s must not be null or empty", paramName));
		}
		return var;
	}

	

	public static Teacher ensureListNotContainsItem(Teacher object,
			List<Teacher> list, String paramName) {
		if (list.contains(object))
			throw new IllegalArgumentException(String.format(
					"%s must not be in list to be added", paramName));

		return object;
	}

	public static Teacher ensureListContainsItem(Teacher object,
			List<Teacher> list, String paramName) {
		if (!list.contains(object))
			throw new IllegalArgumentException(String.format(
					"%s must be in list to be removed", paramName));

		return object;
	}

	

	public static List<Teacher> ensureItemToBeAddedIsNotNull(Teacher teacher, List<Teacher> list, String paramName) {
		if (teacher == null)
			throw new AssertionError(String.format("%s must not be null or empty"));
		return list;
	}

	// for connection
	public static Connection ensureConnectionNotNull(Connection con, String paramName) {
		if (con == null)
			throw new AssertionError(String.format(
					"%s must not be null or empty", paramName));
		return con;
	}

	/**
	 * 
	 * �berpr�ft einen String auf einen g�ltigen Namen. Zeichen wie ' - sowie
	 * Sonderzeichen werden ber�cksichtig. Weiters sind Doppelnamen auch g�ltig.
	 * 
	 * Namen mit 3 Namen werden nicht genommen + Gro�buchstaben im Wort zb.
	 * McDrive
	 */
	public static String ensureRegExMatcherName(String var, String paramName) {
		if (var.matches("[a-zA-z�������]+([ '-][a-zA-Z�������]+)*") == false) {
			throw new IllegalArgumentException(String.format("%s must match a real name", paramName));
		}
		return var;
	};

	public static String ensureRegExMatcherEmail(String var, String paramName) {
		if (var.matches("[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})") == false) {
			throw new IllegalArgumentException(String.format("%s must match a proper email", paramName));
		}
		return var;
	}
}
