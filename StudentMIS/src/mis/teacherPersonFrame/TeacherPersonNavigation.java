package mis.teacherPersonFrame;

import java.awt.CardLayout;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import mis.managerframe.Layout;

public class TeacherPersonNavigation implements Layout{
	CardLayout card=new CardLayout();
	JPanel pane=new JPanel(card);
	JPanel panel=new JPanel();
	public JPanel navigationPane() {
		JMenuBar menuBar=new JMenuBar();
		
		JMenuItem teacher_message=new JMenuItem("个人信息");
		JMenuItem teacher_course=new JMenuItem("课程查看");
		JMenuItem teacher_score=new JMenuItem("学生成绩");
		
		
		teacher_message.addActionListener(e->{
			card.show(pane,"teacherMessage");
		});
		teacher_course.addActionListener(e->{
			card.show(pane,"teacherPersonCourse");
		});
		teacher_score.addActionListener(e->{
			card.show(pane,"teacherPersonScore");
		});
		
		menuBar.add(teacher_message);
		menuBar.add(teacher_course);
		menuBar.add(teacher_score);
		
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
