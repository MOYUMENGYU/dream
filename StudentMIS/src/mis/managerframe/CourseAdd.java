package mis.managerframe;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import bean.Course;
import factory.CourseTableFactory;
import factory.TableFactoryAbstract;
import factory.UserTable;
import mis.tool.SqlOperation;

public class CourseAdd implements Layout{
	CardLayout card=new CardLayout();
	JPanel pane=new JPanel(card);
	JTable table=new JTable();
	public JPanel courseAdd() {
		JLabel number_label=new JLabel("课程编号:");
		JTextField number_text=new JTextField(20);
		JPanel number_pane=new JPanel();
		number_pane.add(number_label);
		number_pane.add(number_text);
		
		JLabel name_label=new JLabel("课程名称:");
		JTextField name_text=new JTextField(20);
		JPanel name_pane=new JPanel();
		name_pane.add(name_label);
		name_pane.add(name_text);
		
		JLabel teacher_label=new JLabel("任课老师:");
		JTextField teacher_text=new JTextField(20);
		JPanel teacher_pane=new JPanel();
		teacher_pane.add(teacher_label);
		teacher_pane.add(teacher_text);
		
		JLabel credit_label=new JLabel("课程学分:");
		JTextField credit_text=new JTextField(20);
		JPanel credit_pane=new JPanel();
		credit_pane.add(credit_label);
		credit_pane.add(credit_text);
		
		JLabel hour_label=new JLabel("课程时长:");
		JTextField hour_text=new JTextField(20);
		JPanel hour_pane=new JPanel();
		hour_pane.add(hour_label);
		hour_pane.add(hour_text);
		
		JButton keep_button=new JButton("保存");
		JButton empty_button=new JButton("清空");
		JButton return_button=new JButton("返回");
		keep_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Course course=new Course();
				course.setNumber(number_text.getText());
				course.setName(name_text.getText());
				course.setTeacher(teacher_text.getText());
				course.setCredit(Double.parseDouble(credit_text.getText()));
				course.setHour(hour_text.getText());
				if(SqlOperation.saveCourse(course)>=0) {
					JOptionPane.showMessageDialog(pane,"保存成功");
					TableFactoryAbstract factory=new CourseTableFactory();
					UserTable user=factory.createUserTable();
					user.plushTable(table);
//					PlushTable.plushCourseTable(table);
				}
			}
		});
		empty_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				number_text.setText("");
				name_text.setText("");
				teacher_text.setText("");
				credit_text.setText("");
				hour_text.setText("");
			}
		});
		return_button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				card.show(pane, "navigation");
				number_text.setText("");
				name_text.setText("");
				teacher_text.setText("");
				credit_text.setText("");
				hour_text.setText("");
			}
			
		});
		JPanel button_pane=new JPanel();
		button_pane.add(keep_button);
		button_pane.add(empty_button);
		button_pane.add(return_button);
		
		JPanel panel=new JPanel(new GridLayout(10,1));
		panel.add(number_pane);
		panel.add(name_pane);
		panel.add(teacher_pane);
		panel.add(credit_pane);
		panel.add(hour_pane);
		panel.add(button_pane);
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
	public void setTable(JTable table) {
		this.table=table;
	}

}
