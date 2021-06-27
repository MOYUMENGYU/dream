package view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeMap;

import dao.Course;
import dao.Report;
import sql.Sql;
import sql.SqlConnection;

/**
 * 成绩管理
 * @author 魔宇
 *
 */

public class ScoreMIS implements SqlConnection{
	Sql sql;
	//课程
	Course course;
	//成绩单
	Report report;
	//全部学生成绩表
	TreeMap<String,Report> studentReport;
	public void enteringReport() {
		Scanner scanner=new Scanner(System.in);
		report=new Report();
		System.out.println("========================================录入学生成绩！==================================================================================");
		System.out.print("姓名:");
		String name=scanner.nextLine();
		report.setName(name);
		
		System.out.print("学号:");
		String number="";
		boolean ff=true;
		while(ff) {
			number=scanner.nextLine();
			if(checkData(number)) {
				course.setScore(number);
				ff=false;
			}else {
				System.out.print("请正确输入分数:");
				ff=true;
			}
		}
		report.setNumber(number);
		
		ArrayList<Course> courseScore=new ArrayList<>();
		
		String math="高等数学";
		course=new Course();
		course.setCourse(math);
		System.out.print(math+":");
		String score1="";
		boolean f1=true;
		while(f1) {
			score1=scanner.nextLine();
			if(checkData(score1)) {
				course.setScore(score1);
				courseScore.add(course);
				f1=false;
			}else {
				System.out.print("请正确输入分数:");
				f1=true;
			}
		}
		
		String physics="大学物理";
		course=new Course();
		course.setCourse(physics);
		System.out.print(physics+":");
		String score2="";
		boolean f2=true;
		while(f2) {
			score2=scanner.nextLine();
			if(checkData(score2)) {
				course.setScore(score2);
				courseScore.add(course);
				f2=false;
			}else {
				System.out.print("请正确输入分数:");
				f2=true;
			}
		}
		
		String English="大学英语";
		course=new Course();
		course.setCourse(English);
		System.out.print(English+":");
		String score3="";
		boolean f3=true;
		while(f3) {
			score3=scanner.nextLine();
			if(checkData(score3)) {
				course.setScore(score3);
				courseScore.add(course);
				f3=false;
			}else {
				System.out.print("请正确输入分数:");
				f3=true;
			}
		}
		
		String java="Java";
		course=new Course();
		course.setCourse(java);
		System.out.print(java+":");
		String score4="";
		boolean f4=true;
		while(f4) {
			score4=scanner.nextLine();
			if(checkData(score4)) {
				course.setScore(score4);
				courseScore.add(course);
				f4=false;
			}else {
				System.out.print("请正确输入分数:");
				f4=true;
			}
		}
		
		String military="军事理论";
		course=new Course();
		course.setCourse(military);
		System.out.print(military+":");
		String score5="";
		boolean f5=true;
		while(f5) {
			score5=scanner.nextLine();
			if(checkData(score5)) {
				course.setScore(score5);
				courseScore.add(course);
				f5=false;
			}else {
				System.out.print("请正确输入分数:");
				f5=true;
			}
		}
		
		String sport="大学体育";
		course=new Course();
		course.setCourse(sport);
		System.out.print(sport+":");
		String score6="";
		boolean f6=true;
		while(f6) {
			score6=scanner.nextLine();
			if(checkData(score6)) {
				course.setScore(score6);
				courseScore.add(course);
				f6=false;
			}else {
				System.out.print("请正确输入分数:");
				f6=true;
			}
		}
		
		report.setCourseScore(courseScore);
		
		double sum=Double.parseDouble(score1)+Double.parseDouble(score2)
				   +Double.parseDouble(score3)+Double.parseDouble(score4)
				   +Double.parseDouble(score5)+Double.parseDouble(score6);
		System.out.printf("总分:%.2f\n",sum);
		report.setSum(String.valueOf(sum));
		
		System.out.print("备注:");
		String remark=scanner.nextLine();
		report.setRemark(remark);
		//存放到数据库
		sql.getStudentReport().put(number, report);
		System.out.println("========================================录入完成！===========================================================================================================");
	}
	public void showStudentReport() {
		System.out.println("========================================成绩信息！========================================================================================================");
		studentReport=sql.getStudentReport();
		ArrayList<Report> sortReport=new ArrayList<>();
		if(!studentReport.isEmpty()) {
			Iterator<String> iterator=studentReport.keySet().iterator();
			while(iterator.hasNext()) {
				String number=iterator.next();
				sortReport.add(studentReport.get(number));
			}
			Collections.sort(sortReport);
			Iterator<Report> ir=sortReport.iterator();
			double sum1=0,sum2=0,sum3=0,sum4=0,sum5=0,sum6=0;
			double pass1=0,pass2=0,pass3=0,pass4=0,pass5=0,pass6=0;
			double i=0;
			while(ir.hasNext()) {
				Report r=ir.next();
			    i=Double.parseDouble(r.getCourseScore().get(0).getScore());
			    sum1+=i;
				if(i>=60) {
					pass1++;
				}
				i=Double.parseDouble(r.getCourseScore().get(1).getScore());
			    sum2+=i;
				if(i>=60) {
					pass2++;
				}
				i=Double.parseDouble(r.getCourseScore().get(2).getScore());
			    sum3+=i;
				if(i>=60) {
					pass3++;
				}
				i=Double.parseDouble(r.getCourseScore().get(3).getScore());
			    sum4+=i;
				if(i>=60) {
					pass4++;
				}
				i=Double.parseDouble(r.getCourseScore().get(4).getScore());
			    sum5+=i;
				if(i>=60) {
					pass5++;
				}
				i=Double.parseDouble(r.getCourseScore().get(5).getScore());
			    sum6+=i;
				if(i>=60) {
					pass6++;
				}
				showReport(r);
				//Iterator的next()在一个循环中不能出现两次，会导致最后的游标指向空值
//				System.out.printf("排名:%-2d\n",(sortReport.indexOf(ir.next())+1));	
				System.out.printf("排名:%-2d\n",(sortReport.indexOf(r)+1));
			}
			int size=sortReport.size();
			System.out.println("========================================成绩分析==================================================================================");
			System.out.printf("高等数学 平均成绩:%.2f 及格率%.2f%%\n",sum1/size,pass1/size*100);
			System.out.printf("大学物理 平均成绩:%.2f 及格率%.2f%%\n",sum2/size,pass2/size*100);
			System.out.printf("大学英语 平均成绩:%.2f 及格率%.2f%%\n",sum3/size,pass3/size*100);
			System.out.printf("Java     平均成绩:%.2f 及格率%.2f%%\n",sum4/size,pass4/size*100);
			System.out.printf("军事理论 平均成绩:%.2f 及格率%.2f%%\n",sum5/size,pass5/size*100);
			System.out.printf("大学体育 平均成绩:%.2f 及格率%.2f%%\n",sum6/size,pass6/size*100);
			System.out.println("==========================================================================================================================");
		}else {
			System.out.println("========================================成绩还没有录入！=================================================================================");
		}
	}
	public void checkScore() {
		System.out.println("========================================成绩查询！==================================================================================");
		studentReport=sql.getStudentReport();
		System.out.print("请输入要查询学生的学号:");
		Scanner scanner=new Scanner(System.in);
		String order=scanner.nextLine();
		if(!studentReport.isEmpty()) {
			if(studentReport.containsKey(order)) {
				showReport(studentReport.get(order));
			}else {
				System.out.println("========================================未查找到该学生的成绩！==================================================================================");
			}
		}else {
			System.out.println("========================================成绩未录入！==================================================================================");
		}
	}
	public void removeReport() {System.out.println("========================================删除成绩！==================================================================================");
		studentReport=sql.getStudentReport();
		System.out.print("请输入要删除学生的学号:");
		Scanner scanner=new Scanner(System.in);
		String order=scanner.nextLine();
		if(!studentReport.isEmpty()) {
			if(studentReport.containsKey(order)) {
				studentReport.remove(order);
				System.out.println("========================================删除成功！==================================================================================");
			}else {
				System.out.println("========================================没有该学生的成绩！==================================================================================");
			}
		}else {
			System.out.println("========================================成绩未录入！==================================================================================");
		}
	}
	public void showReport(Report report) {
		
		System.out.printf("姓名:%-2s\t",report.getName());
		System.out.printf("学号:%-2s\t",report.getNumber());
		System.out.printf("高等数学:%-2s\t",report.getCourseScore().get(0).getScore());
		System.out.printf("大学物理:%-2s\t",report.getCourseScore().get(1).getScore());
		System.out.printf("大学英语:%-2s\t",report.getCourseScore().get(2).getScore());
		System.out.printf("Java:%-2s\t",report.getCourseScore().get(3).getScore());
		System.out.printf("军事理论:%-2s\t",report.getCourseScore().get(4).getScore());
		System.out.printf("大学体育:%-2s\t",report.getCourseScore().get(5).getScore());
		System.out.printf("总分:%-2s\t",report.getSum());
		System.out.printf("备注:%-2s\t",report.getRemark());

	}
	@Override
	public void SqlConnection(Sql sql) {		
		this.sql=sql;
	}
	public boolean checkData(String score) {
		if(score.matches("\\d+")) {
			return true;
		}else {
			return false;
		}
	}
}
