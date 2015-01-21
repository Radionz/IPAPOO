package entity;

/**
 * @author Nicolas Sarroche, Dorian Blanc
 */
public class Player {

	private final String name;

	/* Basic getters / setters */
	public String getName() {
		return name;
	}

	public Player(String playerName) {
		this.name = playerName;
	}
}
