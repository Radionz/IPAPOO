package item;

import study.Exam;

public class ExamPaper extends Item{
	
	private Exam exam;
	
	public ExamPaper(Exam exam, int energy){
		super(exam.getCourse(), energy);
		this.exam = exam;
	}

}
