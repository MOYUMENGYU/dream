package sql;

import java.util.HashMap;
import java.util.TreeMap;

import dao.Report;
import dao.Student;

/**
 * 数据库
 * @author 魔宇
 *
 */

public class Sql {
	//学生学籍存储表
	HashMap<String,Student> studentStatus=new HashMap<>();
	//成绩储存表
	TreeMap<String,Report> studentReport=new TreeMap<>();
	
	public HashMap<String, Student> getStudentStatus() {
		return studentStatus;
	}
	public void setStudentStatus(HashMap<String, Student> studentStatus) {
		this.studentStatus = studentStatus;
	}
	public TreeMap<String, Report> getStudentReport() {
		return studentReport;
	}
	public void setStudentReport(TreeMap<String, Report> studentReport) {
		this.studentReport = studentReport;
	}
}
