package mis.managerframe;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Navigation implements Layout{
	CardLayout card=new CardLayout();
	JPanel pane=new JPanel(card);
	
	JPanel panel=new JPanel();
	public JPanel navigationPane() {
		JMenuBar menuBar=new JMenuBar();
		
		JMenu system=new JMenu("系统管理");
		JMenu teacher=new JMenu("教师管理");
		JMenu student=new JMenu("学生管理");
		JMenu course=new JMenu("课程管理");
		
		JMenuItem manager_message=new JMenuItem("管理员信息");
		JMenuItem manager_add=new JMenuItem("添加管理员");
		system.add(manager_message);
		system.add(manager_add);
		
		manager_message.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				card.show(pane,"managerMessage");
			}
			
		});
		manager_add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				card.show(pane,"managerAdd");
			}
			
		});
		
		JMenuItem teacher_message=new JMenuItem("教师信息");
		JMenuItem teacher_browse=new JMenuItem("教师录入");
		teacher.add(teacher_message);
		teacher.add(teacher_browse);
		
		teacher_message.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				card.show(pane,"teacherMessage");
			}
			
		});
		
		teacher_browse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				card.show(pane,"teacherAdd");
			}
		});
		
		
		JMenuItem student_message=new JMenuItem("学生录入");
		JMenuItem student_browse=new JMenuItem("学生信息");
		JMenuItem student_score=new JMenuItem("学生成绩");
		student.add(student_message);
		student.add(student_browse);
		student.add(student_score);
		
		student_message.addActionListener(e->{
			card.show(pane,"studentAdd");
		});
		student_browse.addActionListener(e->{
			card.show(pane,"studentMessage");
		});
		student_score.addActionListener(e->{
			card.show(pane,"scoreMessage");
		});
		
		JMenuItem course_message=new JMenuItem("课程信息");
		JMenuItem course_add=new JMenuItem("开设课程");
		
		course.add(course_message);
		course.add(course_add);
		
		course_message.addActionListener(e->{
			card.show(pane,"courseMessage");
		});
		course_add.addActionListener(e->{
			card.show(pane,"courseAdd");
		});
		
		menuBar.add(system);
		menuBar.add(teacher);
		menuBar.add(student);
		menuBar.add(course);
		
		panel.add(menuBar);
		return panel;
	}
	@Override
	public void setPane(JPanel pane) {
		this.pane=pane;
	}
	@Override
	public void setcard(CardLayout card) {
		this.card=card;
	}
}
