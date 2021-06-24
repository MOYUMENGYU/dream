package mis.studentPersonFrame;

import java.awt.CardLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import mis.managerframe.Layout;

public class StudentPersonNavigation implements Layout {
	CardLayout card=new CardLayout();
	JPanel pane=new JPanel(card);
	JPanel panel=new JPanel();
	
	public JPanel navigationPane() {
		
		JMenuBar menuBar=new JMenuBar();
		JMenuItem student_message=new JMenuItem("个人信息");
		JMenuItem student_course=new JMenuItem("课程查看");
		JMenuItem student_score=new JMenuItem("成绩查看");
		
		
		student_message.addActionListener(e->{
			card.show(pane,"studentMessage");
		});
		student_course.addActionListener(e->{
			card.show(pane,"studentCourse");
		});
		student_score.addActionListener(e->{
			card.show(pane,"studentScore");
		});
		
		
		menuBar.add(student_message);
		menuBar.add(student_course);
		menuBar.add(student_score);
		
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
