package zuul;


import java.util.ArrayList;

import study.Exam;
import study.Lab;
import study.Lesson;
import io.IO;

public class Game {

	private ArrayList<Lesson> lessons;
	private ArrayList<Lab> labs;
	private ArrayList<Exam> exams;

	public Game() {
		this.lessons = new ArrayList<Lesson>();
		this.labs = new ArrayList<Lab>();
		this.exams = new ArrayList<Exam>();
	}

	public void play() {

		for (String path : IO.getPossibleLessonsPaths()) {
			lessons.add(new Lesson(path));
		}
		for (Lesson lesson : lessons) {
			labs.add(new Lab(lesson, 3));
		}
		for (Lab lab : labs) {
			exams.add(new Exam(lab));
		}
		lessons.get(0).printLesson();
		labs.get(0).takeALab();
		//exams.get(0).takeAnExam();
		
		
	}
}