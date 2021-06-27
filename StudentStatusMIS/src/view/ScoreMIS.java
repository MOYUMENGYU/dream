package view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

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
	//模拟数据库
	Sql sql;
	//课程
	Course course;
	//成绩单
	Report report;
	//全部学生成绩表
	HashMap<String,Report> studentReport;
	
	//成绩管理导航
	public void navigationScore() {
		System.out.println();
		System.out.printf("%25s1.成绩录入 2.成绩信息 3.成绩查询 4.成绩删除 5.返回导航\n","");
		System.out.printf("%45s请输入服务序号:","");
		Scanner scanner=new Scanner(System.in);
		//对输入序号进行验证，防止非法输入导致程序终止
		String order="";
		boolean flag=true;
		while(flag) {
			order=scanner.nextLine();
			if(order.matches("[12345]")) {
				flag=false;
			}else {
				System.out.printf("%40s请输入正确的服务学号(1-5):","");
				flag=true;
			}
		}
		switch(Integer.parseInt(order)) {
		case 1:
			enteringReport();
			navigationScore();
			break;
		case 2:
			showStudentReport();
			navigationScore();
			break;
		case 3:
			checkScore();
			navigationScore();
			break;
		case 4:
			removeReport();
			navigationScore();
			break;
		case 5:
			System.out.println();
			break;
		}
	}
	//成绩录入
	public void enteringReport() {
		System.out.println();
		System.out.println("========================================录入学生成绩！==================================================================================");
		Scanner scanner=new Scanner(System.in);
		//成绩单，封装成绩信息
		report=new Report();
		System.out.print("姓名:");
		String name=scanner.nextLine();
		report.setName(name);
		
		System.out.print("学号:");
		String number="";
		boolean ff=true;
		while(ff) {
			number=scanner.nextLine();
			if(checkData(number)) {
				report.setNumber(number);
				ff=false;
			}else {
				System.out.print("请正确输入学号:");
				ff=true;
			}
		}
		report.setNumber(number);
		
		//存放各个考试科目和分数
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
		/**
		 * 为防止非法输入导致程序运行停止，故将变量都设为字符型
		 * ，这里再将变量转换为数值型
		 */
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
		System.out.println();
	}
	//显示学生成绩信息
	public void showStudentReport() {
		System.out.println();
		System.out.println("========================================成绩信息！==================================================================================================================================");
		studentReport=sql.getStudentReport();
		//存放成绩单，在数组链表里将成绩单排好序
		ArrayList<Report> sortReport=new ArrayList<>();
		if(!studentReport.isEmpty()) {
			Iterator<String> iterator=studentReport.keySet().iterator();
			while(iterator.hasNext()) {
				String number=iterator.next();
				sortReport.add(studentReport.get(number));
			}
			//成绩单排序
			Collections.sort(sortReport);
			Iterator<Report> ir=sortReport.iterator();
			//科目总分
			double sum1=0,sum2=0,sum3=0,sum4=0,sum5=0,sum6=0;
			//及格人数
			double pass1=0,pass2=0,pass3=0,pass4=0,pass5=0,pass6=0;
			double i=0;
			//显示成绩
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
				//打印成绩信息
				showReport(r);
				System.out.printf("排名:%-2d\n",(sortReport.indexOf(r)+1));
			}
			int size=sortReport.size();
			System.out.println("========================================成绩分析============================================================================================================");
			System.out.printf("高等数学 平均成绩:%.2f 及格率%.2f%%\n",sum1/size,pass1/size*100);
			System.out.printf("大学物理 平均成绩:%.2f 及格率%.2f%%\n",sum2/size,pass2/size*100);
			System.out.printf("大学英语 平均成绩:%.2f 及格率%.2f%%\n",sum3/size,pass3/size*100);
			System.out.printf("Java     平均成绩:%.2f 及格率%.2f%%\n",sum4/size,pass4/size*100);
			System.out.printf("军事理论 平均成绩:%.2f 及格率%.2f%%\n",sum5/size,pass5/size*100);
			System.out.printf("大学体育 平均成绩:%.2f 及格率%.2f%%\n",sum6/size,pass6/size*100);
			System.out.println("====================================================================================================================================================");
		}else {
			System.out.println("========================================成绩还没有录入！=================================================================================");
		}
		System.out.println();
	}
	
	public void checkScore() {
		System.out.println();
		System.out.println("========================================成绩查询！==================================================================================");
		studentReport=sql.getStudentReport();
		System.out.print("请输入要查询学生的学号:");
		Scanner scanner=new Scanner(System.in);
		String order=scanner.nextLine();
		if(!studentReport.isEmpty()) {
			if(studentReport.containsKey(order)) {
				System.out.println("========================================查询结果！==================================================================================");
				showReport(studentReport.get(order));
				System.out.println("============================================================================================================================================");
			}else {
				System.out.println("========================================未查找到该学生的成绩！==================================================================================");
			}
		}else {
			System.out.println("========================================成绩未录入！==================================================================================");
		}
		System.out.println();
	}
	//删除成绩单
	public void removeReport() {
		System.out.println();
		System.out.println("========================================删除成绩！==================================================================================");
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
		System.out.println();
	}
	//打印成绩单
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
	//连接数据库
	@Override
	public void SqlConnection(Sql sql) {		
		this.sql=sql;
	}
	//对字符串进行检测，只接受数字
	public boolean checkData(String score) {
		if(score.matches("\\d+")) {
			return true;
		}else {
			return false;
		}
	}
}
