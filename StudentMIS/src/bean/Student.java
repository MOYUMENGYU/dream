package bean;
/**
 * Ñ§ÉúÀà
 * @author Ä§Óî
 *
 */
public class Student extends People{
	String collage;
	String major;
	String classNumber;
	Course[]course;
	public String getCollage() {
		return collage;
	}
	public void setCollage(String collage) {
		this.collage = collage;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getClassNumber() {
		return classNumber;
	}
	public void setClassNumber(String classNumber) {
		this.classNumber = classNumber;
	}
	public Course[] getCourse() {
		return course;
	}
	public void setCourse(Course[] course) {
		this.course = course;
	}
	
}
