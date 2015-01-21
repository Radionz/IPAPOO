package zuul;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import entity.Player;
import room.Room;
import study.Exam;
import study.Lab;
import study.Lesson;
import io.Parser;
import io.Command;
import io.IO;

public class Game {

	private static boolean finished;
	private static Room currentRoom;
	private ArrayList<Lesson> lessons;
	private ArrayList<Lab> labs;
	private ArrayList<Exam> exams;
	private ArrayList<String> actions;
	private static HashMap<String, Object> constantes;
	private Player player;
	private Parser parser;

	/* Basic getters */
	public static HashMap<String, Object> getConstantes() {
		return constantes;
	}

	public Player getPlayer() {
		return player;
	}

	public ArrayList<Lab> getLabs() {
		return labs;
	}

	public ArrayList<Lesson> getLessons() {
		return lessons;
	}

	/**
	 * Create the game and initialise its internal map. all questions and
	 * lessons arrays and create a Player
	 */
	public Game() {
		finished = false;
		try {
			constantes = IO.getFromFile(IO.getPossibleLanguagesPaths()
					.get(0));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.lessons = new ArrayList<Lesson>();
		this.labs = new ArrayList<Lab>();
		this.exams = new ArrayList<Exam>();
		this.actions = new ArrayList<String>();
		this.parser = new Parser();
		createStudySupport();
		createRooms();
		for (Method method : Action.class.getDeclaredMethods()) {
			actions.add(method.getName());
		}
	}

	private void createStudySupport() {
		for (String path : IO.getPossibleLessonsPaths()) {
			lessons.add(new Lesson(path));
		}
		for (Lesson lesson : lessons) {
			labs.add(new Lab(lesson, 3));
		}
		for (Lab lab : labs) {
			exams.add(new Exam(lab));
		}
	}
	
	private void createRooms() {
		Room outside, test;
		outside = new Room((String) constantes.get("outside_description"));
		test = new Room("in the test room");
		
		outside.setExit(Room.Exits.EAST, test);
		
		currentRoom = outside;
	}

	public void play() {
		while (!finished) {
			Command command = parser.getCommand();
			processCommand(command);
		}
		System.out.println(constantes.get("close_game"));
	}

	private boolean processCommand(Command command) {
		String commandWord = command.getCommandWord();
		String paramWord = command.getSecondWord();
		
		if (actions.contains(commandWord) && !commandWord.equals("unknown")) {
			Method method = null;
			try {
				method = Action.class.getDeclaredMethod(commandWord, String.class);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				return (boolean) method.invoke(Action.class, paramWord);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else
			System.err.println("I don't know what you mean." + "\n"
					+ "Availbe actions : " + stringAvaibleActions());
		return false;
	}

	private String stringAvaibleActions() {
		String result = "";
		;
		for (String action : actions) {
			result += action + ", ";
		}
		return (result.length() > 2) ? result.substring(0, result.length() - 2)
				: result;
	}

	public static class Action {
		
		public static boolean go(String command) {
			if (command == null) {
				System.out.println(constantes.get("go_where"));
				System.out.println(currentRoom.getExitString());
				return false;
			}

			// Try to leave current rooms.
			Room nextRoom = currentRoom.getExit(command);		

			if (nextRoom == null) {
				System.out.println(constantes.get("no_door"));
			}else if(currentRoom.canLeave() && nextRoom.canEnter()){
				currentRoom = nextRoom;
				currentRoom.enter();
				return true;
			}
			return false;		
		}

		public static boolean action1(String command) {
			System.out.println("action1 " + command);
			return true;
		}

		public static boolean action2(String command) {
			System.out.println("action2");
			return true;
		}

		public static boolean quit(String command) {
			finished = true;
			return true;
		}
	}
}