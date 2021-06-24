package mis.managerframe;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
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

@SuppressWarnings("serial")
public class LookupResultCourse extends JDialog implements Layout{
	CardLayout card=new CardLayout();
	JPanel pane=new JPanel(card);
	JTable table=new JTable();
	public LookupResultCourse() {
		setTitle("信息");
		setSize(400,400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(pane);
		setModal(true);
	}
	public JPanel checkResultPanel(Course course) {	
		
		JLabel number_label=new JLabel("课程编号:");
		JTextField number_text=new JTextField(10);
		number_text.setText(course.getNumber());
		number_text.setEnabled(false);
		JPanel number_pane=new JPanel();
		number_pane.add(number_label);
		number_pane.add(number_text);
		
		JLabel name_label=new JLabel("课程名称:");
		JTextField name_text=new JTextField(10);
		name_text.setText(course.getName());
		name_text.setEnabled(false);
		JPanel name_pane=new JPanel();
		name_pane.add(name_label);
		name_pane.add(name_text);
		
		JLabel teacher_label=new JLabel("任课老师:");
		JTextField teacher_text=new JTextField(10);
		teacher_text.setText(course.getTeacher());
		teacher_text.setEnabled(false);
		JPanel teacher_pane=new JPanel();
		teacher_pane.add(teacher_label);
		teacher_pane.add(teacher_text);
		
		
		JLabel credit_label=new JLabel("课程学分:");
		JTextField credit_text=new JTextField(10);
		credit_text.setText(String.valueOf(course.getCredit()));
		credit_text.setEnabled(false);
		JPanel credit_pane=new JPanel();
		credit_pane.add(credit_label);
		credit_pane.add(credit_text);
		
		JLabel hour_label=new JLabel("课程时长:");
		JTextField hour_text=new JTextField(10);
		hour_text.setText(course.getHour());
		hour_text.setEnabled(false);
		JPanel hour_pane=new JPanel();
		hour_pane.add(hour_label);
		hour_pane.add(hour_text);
		
		
		
		JButton revise_button=new JButton("修改");
		revise_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				name_text.setEnabled(true);
				teacher_text.setEnabled(true);
				credit_text.setEnabled(true);
				hour_text.setEnabled(true);
			}
		});
		JButton keep_button=new JButton("保存");
		keep_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Course course=new Course();
				course.setNumber(number_text.getText());
				course.setName(name_text.getText());
				course.setTeacher(teacher_text.getText());
				course.setCredit(Double.parseDouble(credit_text.getText()));
				course.setHour(hour_text.getText());
				int i=SqlOperation.updateCourse(course);
				if(i>0) {
					JOptionPane.showMessageDialog(pane,"保存成功");
//					PlushTable.plushCourseTable(table);
					TableFactoryAbstract factory=new CourseTableFactory();
					UserTable user=factory.createUserTable();
					user.plushTable(table);
				}
			}
			
	});
		JButton return_button=new JButton("返回");
		return_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		JPanel button_pane=new JPanel();
		button_pane.add(revise_button);
		button_pane.add(keep_button);
		button_pane.add(return_button);
				
		
		JPanel panel=new JPanel(new GridLayout(5,2));
		panel.add(number_pane);
		panel.add(name_pane);
		panel.add(teacher_pane);
		panel.add(credit_pane);
		panel.add(hour_pane);
		
		JPanel panell=new JPanel(new BorderLayout());
		panell.add(panel,BorderLayout.CENTER);
		panell.add(button_pane,BorderLayout.SOUTH);
		return panell;
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
