
public class IsPlantException extends Exception {
	public IsPlantException() {
		super("Error: The cursor is at a plant node. Plants cannot be predators.");
	}
}
