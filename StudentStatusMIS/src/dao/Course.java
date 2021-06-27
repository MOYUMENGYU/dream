package dao;
/**
 * 课程类
 * 
 * 封装考试科目和分数
 * @author 
 *
 */
public class Course {
	//考试科目
	private String course;
	//分数
	private String score;
	
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
}
