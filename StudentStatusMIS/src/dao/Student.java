package dao;
/**
 * 学生类
 * 先继承父类人,得到人共有的信息,
 * 再定义学生特有的信息
 * 
 * @author
 *
 */

public class Student extends Person{
	String classNumber;
	String number;
	String major;
	Report report;
	public String getClassNumber() {
		return classNumber;
	}
	public void setClassNumber(String classNumber) {
		this.classNumber = classNumber;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public Report getReport() {
		return report;
	}
	public void setReport(Report report) {
		this.report = report;
	}
}
