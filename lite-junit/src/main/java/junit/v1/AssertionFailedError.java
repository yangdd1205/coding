package junit.v1;

public class AssertionFailedError extends Error {

	public AssertionFailedError () {
    }
	public AssertionFailedError (String message) {
		super (message);
	}
}