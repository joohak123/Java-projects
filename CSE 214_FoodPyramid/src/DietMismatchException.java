
public class DietMismatchException extends Exception {
	public DietMismatchException() {
		super("Error: This prey cannot be added as it does not match the idet of the predator.");
	}
}
