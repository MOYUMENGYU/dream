package view;

import java.util.Scanner;

import sql.Sql;

/**
 * 学生管理系统：进行学生学籍和成绩的管理
 * 主程序
 * @author 魔宇
 *
 */

public class StudentMIS {
	//模拟数据库
	Sql sql=new Sql();
	//学籍管理类
	StatusMIS status=new StatusMIS();
	//成绩管理类
	ScoreMIS score=new ScoreMIS();	
	public StudentMIS() {
		//初始化
		status.SqlConnection(sql);
		score.SqlConnection(sql);
	}
	public static void main(String[] args) {
		System.out.println("==========================================学生学籍管理系统========================================================");
		StudentMIS mis=new StudentMIS();
		//系统导航
		mis.navigation();
	}
	//导航指示
	public void navigation() {
		System.out.printf("%35s1.学籍管理 2.学生成绩管理 3.退出系统\n","");
		System.out.printf("%45s请输入服务序号:","");
		Scanner scanner=new Scanner(System.in);
		//对输入序号进行验证，防止非法输入导致程序终止
		String order="";
		boolean flag=true;
		while(flag) {
			order=scanner.nextLine();
			if(order.matches("[123]")) {
				flag=false;
			}else {
				System.out.printf("%40s请输入正确的服务学号(1-6):","");
				flag=true;
			}
		}
		switch(Integer.parseInt(order)) {
		case 1:
			status.navigationStatus();
			navigation();
			break;
		case 2:
			score.navigationScore();
			navigation();
			break;
		case 3:
			System.out.println();
			System.out.println("========================================已退出学生学籍管理系统！===================================================");
			break;
		}
	}
}
