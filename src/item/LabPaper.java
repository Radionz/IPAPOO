package item;

import study.Lab;

public class LabPaper extends Item{
	
	private Lab lab;
	
	public LabPaper(Lab lab, int energy){
		super(lab.getCourse(), energy);
		this.lab = lab;
	}

}
