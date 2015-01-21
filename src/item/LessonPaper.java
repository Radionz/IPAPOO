package item;

import study.Lesson;

public class LessonPaper extends Item{
	
	private Lesson lesson;
	
	public LessonPaper(Lesson lesson, int energy){
		super(lesson.getCourse(), energy);
		this.lesson = lesson;
	}

}
