package io;

/**
 * This class is part of the "World of Zuul" application. "World of Zuul" is a
 * very simple, text based adventure game.
 *
 * This class holds information about a command that was issued by the user. A
 * command currently consists of two parts: a CommandWord and a string (for
 * example, if the command was "take map", then the two parts are TAKE and
 * "map").
 * 
 * The way this is used is: Commands are already checked for being valid command
 * words. If the user entered an invalid command (a word that is not known) then
 * the CommandWord is UNKNOWN.
 *
 * If the command had only one word, then the second word is <null>.
 * 
 * @author Nicolas Sarroche, Dorian Blanc
 *
 */

public class Command {
	private String commandWord;
	private String secondWord;

	/**
	 * Create a command object. First and second words must be supplied, but the
	 * second may be null.
	 * 
	 * @param commandWord
	 *            The CommandWord. UNKNOWN if the command word was not
	 *            recognised.
	 * @param secondWord
	 *            The second word of the command. May be null.
	 */
	public Command(String commandWord, String secondWord) {
		if (commandWord == null)
			commandWord = "unknown";
		this.commandWord = commandWord.toLowerCase();
		this.secondWord = secondWord;
	}

	/**
	 * Return the command word (the first word) of this command.
	 * 
	 * @return The command word.
	 */
	public String getCommandWord() {
		return commandWord;
	}

	/**
	 * @return The second word of this command. Returns null if there was no
	 *         second word.
	 */
	public String getSecondWord() {
		return secondWord;
	}

	/**
	 * @return true if the command has a second word.
	 */
	public boolean hasSecondWord() {
		return (secondWord != null);
	}
}
