package mis.managerframe;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import mis.studentPersonFrame.StudentPersonNavigation;
import mis.teacherPersonFrame.TeacherPersonCourse;
import mis.teacherPersonFrame.TeacherPersonNavigation;
import mis.teacherPersonFrame.TeacherPersonScore;
import mis.teacherPersonFrame.TeacherScore;

/**
 * 主窗口：使用卡片布局
 * @author 魔宇
 *
 */
@SuppressWarnings("serial")
public class StartFrame extends MyFrame{

	//主窗口面板使用卡片布局管理，通过卡片的切换实现窗口内容的转换，每个内容面板都添加到主窗口的面板上
	private static CardLayout card=new CardLayout();
	private static JPanel pane=new JPanel(card);
	//窗口初始化
	public StartFrame() {
		setTitle("信息管理系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	//各个内容面板
	static {
		//登录面板
		Login login=new Login();
		login.setPane(pane);
		login.setcard(card);
		//管理员导航面板
		Navigation navigation=new Navigation();
		navigation.setPane(pane);
		navigation.setcard(card);
		
		//对话框
		LookupDialogManager lookup=new LookupDialogManager();
		lookup.setPane(pane);
		lookup.setcard(card);
		
		ManagerMessage managerMessage=new ManagerMessage();
		managerMessage.setPane(pane);
		managerMessage.setcard(card);
		
		ManagerAdd managerAdd=new ManagerAdd();
		managerAdd.setPane(pane);
		managerAdd.setcard(card);
		managerAdd.setTable(managerMessage.table);
		
		TeacherMessage teacherMessage=new TeacherMessage();
		teacherMessage.setPane(pane);
		teacherMessage.setcard(card);
		
		TeacherAdd teacherAdd=new TeacherAdd();
		teacherAdd.setPane(pane);
		teacherAdd.setcard(card);
		teacherAdd.setTable(teacherMessage.table);
		
		StudentMessage studentMessage=new StudentMessage();
		studentMessage.setPane(pane);
		studentMessage.setcard(card);
		
		StudentAdd studentAdd=new StudentAdd();
		studentAdd.setPane(pane);
		studentAdd.setcard(card);
		studentAdd.setTable(studentMessage.table);
		
		ScoreMessage score=new ScoreMessage();
		score.setPane(pane);
		score.setcard(card);
		
		DataScoreMessage dataScore=new DataScoreMessage();
		dataScore.setPane(pane);
		dataScore.setcard(card);
		
		DataScoreAdd dataAdd=new DataScoreAdd();
		dataAdd.setPane(pane);
		dataAdd.setcard(card);
		dataAdd.setTable(dataScore.table);
		
		CourseMessage course=new CourseMessage();
		course.setPane(pane);
		course.setcard(card);
		
		CourseAdd courseAdd=new CourseAdd();
		courseAdd.setPane(pane);
		courseAdd.setcard(card);
		courseAdd.setTable(course.table);
		
		StudentPersonNavigation studentNavigation=new StudentPersonNavigation();
		studentNavigation.setPane(pane);
		studentNavigation.setcard(card);

		TeacherPersonNavigation teacherNavigation=new TeacherPersonNavigation();
		teacherNavigation.setPane(pane);
		teacherNavigation.setcard(card);
		
		TeacherPersonCourse teacherCourse=new TeacherPersonCourse();
		teacherCourse.setPane(pane);
		teacherCourse.setcard(card);
		
		TeacherPersonScore teacherScore=new TeacherPersonScore();
		teacherScore.setPane(pane);
		teacherScore.setcard(card);
		
		TeacherScore teacherScoreMessage=new TeacherScore();
		teacherScoreMessage.setPane(pane);
		teacherScoreMessage.setcard(card);
		
		//将各个内容面板添加到主窗口面板上，并指定名称，使其可以通过名称进行跳转
		pane.add(login.loginPane(),"login");
		pane.add(navigation.navigationPane(),"navigation");
		pane.add(managerMessage.managerMessagePane(),"managerMessage");
		pane.add(managerAdd.managerAdd(),"managerAdd");
		pane.add(teacherMessage.teacherMessagePane(),"teacherMessage");
		pane.add(teacherAdd.teacherAdd(),"teacherAdd");
		pane.add(studentMessage.studentMessagePane(),"studentMessage");
		pane.add(studentAdd.studentAdd(),"studentAdd");
		pane.add(course.courseMessagePane(),"courseMessage");
		pane.add(courseAdd.courseAdd(),"courseAdd");
		pane.add(score.scoreMessagePane(),"scoreMessage");
		pane.add(dataScore.scoreMessagePane(),"dataScoreMessage");
		pane.add(dataAdd.scoreAdd(),"dataScoreAdd");
		pane.add(studentNavigation.navigationPane(),"studentNavigation");
		pane.add(teacherNavigation.navigationPane(),"teacherNavigation");
		pane.add(teacherCourse.courseMessagePane(),"teacherPersonCourse");
		pane.add(teacherScore.scoreMessagePane(),"teacherPersonScore");
		pane.add(teacherScoreMessage.scoreMessagePane(),"teacherScore");
		//默认先显示登录面板
		card.show(pane,"login");
	}
	public static void main(String[] args) {
		StartFrame start=new StartFrame();
		start.add(pane);
		start.setVisible(true);
	}
}
