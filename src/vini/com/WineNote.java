package vini.com;

import java.util.Calendar;

public class WineNote {

	private String name;
	private int year;
	private Calendar tasteDate;
	private int grade;
	private String comment;
	private int id;
	
	public WineNote(String name,
					int year,
					Calendar tasteDate,
					int grade,
					String comment,
					int id) {
		
		this.name = name;
		this.year = year;
		this.tasteDate = tasteDate;
		this.grade = grade;
		this.comment = comment;
		this.id = id;
	}
	
	public String getComment() {
		return comment;
	}

	public String getName() {
		return name;
	}

	public int getYear() {
		return year;
	}

	public Calendar getTasteDate() {
		return tasteDate;
	}

	public int getId() {
		return id;
	}	

	public int getGrade() {
		return grade;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public void setTasteDate(Calendar tasteDate) {
		this.tasteDate = tasteDate;
	}
}
