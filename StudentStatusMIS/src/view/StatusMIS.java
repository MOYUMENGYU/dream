package view;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import dao.Student;
import sql.Sql;
import sql.SqlConnection;

/**
 * 学籍管理类
 * 对学籍进行管理，增、删、减、查
 * @author 魔宇
 *
 */

public class StatusMIS implements SqlConnection{
	//模拟数据库
	Sql sql;
	//学生类，用于封装学生信息
	Student student;
	//学籍数据表，存放学生学籍
	HashMap<String,Student> studentStatus;
	
	//学籍导航指示
	public void navigationStatus() {
		System.out.println();
		System.out.printf("%25s1.学籍录入 2.学籍信息 3.学籍查询 4.学籍删除 5.返回导航\n","");
		System.out.printf("%45s请输入服务序号:","");
		Scanner scanner=new Scanner(System.in);
		//对输入序号进行验证，防止非法输入导致程序终止
		String order="";
		boolean flag=true;
		while(flag) {
			order=scanner.nextLine();
			//限定输入只能再1-5之间
			if(order.matches("[12345]")) {
				flag=false;
			}else {
				System.out.printf("%40s请输入正确的服务学号(1-5):","");
				flag=true;
			}
		}
		//将序号转为数值型
		switch(Integer.parseInt(order)) {
		case 1:
			enteringStatus();
			navigationStatus();
			break;
		case 2:
			showStatusMessage();
			navigationStatus();
			break;
		case 3:
			checkStatus();
			navigationStatus();
			break;
		case 4:
			removeStatus();
			navigationStatus();
			break;
		case 5:
			System.out.println();
			break;
		}
	}
	//学籍录入
	public void enteringStatus() {
		//用一个学生类封装信息
		student=new Student();
		Scanner scanner=new Scanner(System.in);
		System.out.println("========================================进行学生学籍录入！===================================================================================");
		
		System.out.print("姓名:");
		String name=scanner.nextLine();
		student.setName(name);
		
		System.out.print("性别:");
		String sex=scanner.nextLine();
		student.setSex(sex);
		
		System.out.print("出生日期(yyyy-mm-dd):");
		String birthday="";
		//对输入出生日期格式进行检测
		boolean bf=true;
		while(bf) {
			birthday=scanner.nextLine();
			if(birthday.matches("\\d{4}-\\d{2}-\\d{2}")) {
				bf=false;
			}else {
				System.out.print("请输入正确的日期:");
				bf=true;
			}
		}
		student.setBirthday(birthday);
		
		System.out.print("年龄:");
		String age=scanner.nextLine();
		student.setAge(age);
		
		System.out.print("身份证号码:");
		String idNumber=scanner.nextLine();
		student.setIdNumber(idNumber);
		
		System.out.print("籍贯:");
		String address=scanner.nextLine();
		student.setAddress(address);
		
		System.out.print("民族:");
		String nation=scanner.nextLine();
		student.setNation(nation);
		
		System.out.print("班级:");
		String classNumber=scanner.nextLine();
		student.setClassNumber(classNumber);
		
		System.out.print("学号:");
		String number=scanner.nextLine();
		student.setNumber(number);
		
		System.out.print("专业:");
		String major=scanner.nextLine();
		student.setMajor(major);
		
		//将信息存进学籍信息数据表中
		sql.getStudentStatus().put(number, student);
		
		System.out.println("========================================学籍录入完成==================================================================");
	}
	
	//学籍信息:输出学籍信息表
	public void showStatusMessage() {
		System.out.println("========================================显示学生学籍信息！==================================================================");
		//获取到数据库中的学籍数据表
		studentStatus=sql.getStudentStatus();
		if(!studentStatus.isEmpty()) {
			//迭代器遍历
			Iterator<String> iterator=studentStatus.keySet().iterator();
			while(iterator.hasNext()) {
				String number=iterator.next();
				showStudentStatus(studentStatus.get(number));
			}
			System.out.println("共有"+studentStatus.size()+"名学生");
		}else {
			System.out.println("========================================学生学籍还没有录入！==================================================================");
		}
		System.out.println("========================================显示完成！==================================================================");
	}
	
	//学籍查询
	public void checkStatus() {
		System.out.println("========================================查询学生学籍信息！==================================================================");
		studentStatus=sql.getStudentStatus();
		System.out.print("请输入查询学生的学号:");
		Scanner scanner=new Scanner(System.in);
		String number=scanner.nextLine();
		if(!studentStatus.isEmpty()) {
			if(studentStatus.containsKey(number)) {
				System.out.println("========================================查找结果！==================================================================");
				showStudentStatus(studentStatus.get(number));
			}else {
				System.out.println("========================================未查找到该学生学籍信息！==================================================================");
			}
		}else {
			System.out.println("========================================学生学籍还没有录入！==================================================================");
		}
		
	}
	
	//学籍删除
	public void removeStatus() {
		System.out.println("========================================删除学生学籍信息！==================================================================");
		studentStatus=sql.getStudentStatus();
		System.out.print("请输入需要删除学籍的学生学号:");
		Scanner scanner=new Scanner(System.in);
		String number=scanner.nextLine();
		if(!studentStatus.isEmpty()) {
			if(studentStatus.containsKey(number)) {
				studentStatus.remove(number);
				System.out.println("========================================删除完成！==================================================================");
			}else {
				System.out.println("未查找到该学生学籍!");
			}
		}else {
			System.out.println("========================================学生学籍还没有录入！==================================================================");
		}
	}
	//打印学籍信息
	public void showStudentStatus(Student student) {
		System.out.println("=====================================================================================================================================");
		System.out.printf("姓名:%-8s\t",student.getName());
		
		System.out.printf("性别:%-8s\t",student.getSex());
		
		System.out.printf("出生日期:%-8s\t\n",student.getBirthday());
		
		
		System.out.printf("年龄:%-8s\t",student.getAge());
		
		System.out.printf("身份证号码:%-8s\t",student.getIdNumber());
		
		System.out.printf("籍贯:%-8s\t\n",student.getAddress());
		
		
		System.out.printf("民族:%-8s\t",student.getNation());
		
		
		System.out.printf("班级:%-8s\t",student.getClassNumber());
		
		
		System.out.printf("学号:%-8s\t",student.getNumber());
	
		System.out.printf("专业:%-8s\t\n",student.getMajor());
		System.out.println("=====================================================================================================================================");
	}
	//连接数据库
	@Override
	public void SqlConnection(Sql sql) {
		this.sql=sql;
	}
}
