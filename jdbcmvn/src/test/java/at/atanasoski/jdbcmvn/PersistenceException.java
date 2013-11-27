package at.atanasoski.jdbcmvn;

@SuppressWarnings("serial")
public class PersistenceException extends RuntimeException {

	public PersistenceException() {
		super();
	}

	public PersistenceException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public PersistenceException(String message) {
		super(message);
	}

	public PersistenceException(Throwable throwable) {
		super(throwable);
	}
}