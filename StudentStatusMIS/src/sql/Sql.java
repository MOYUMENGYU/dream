package sql;

import java.util.HashMap;
import java.util.TreeMap;

import dao.Report;
import dao.Student;

/**
 * 模拟数据库
 * 用于保存学籍信息,成绩信息
 * @author
 *
 */

public class Sql {
	//学生学籍存储表
	HashMap<String,Student> studentStatus=new HashMap<>();
	//成绩储存表
	HashMap<String,Report> studentReport=new HashMap<>();
	public Sql(){
		
	}
	public HashMap<String, Student> getStudentStatus() {
		return studentStatus;
	}
	public void setStudentStatus(HashMap<String, Student> studentStatus) {
		this.studentStatus = studentStatus;
	}
	public HashMap<String, Report> getStudentReport() {
		return studentReport;
	}
	public void setStudentReport(HashMap<String, Report> studentReport) {
		this.studentReport = studentReport;
	}
}
