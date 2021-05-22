
public class PositionNotAvailableException extends Exception {
	public PositionNotAvailableException() {
		super("Error: There is no more room for more prey for this predator.");
	}
}
