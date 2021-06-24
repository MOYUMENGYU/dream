package mis.managerframe;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import bean.Course;
import mis.tool.SqlOperation;

@SuppressWarnings("serial")
public class LookupDialogCourse extends JDialog implements Layout{
	CardLayout card=new CardLayout();
	JPanel pane=new JPanel(card);
	JTable table=new JTable();
	String item="课程编号";
	String sql;
	public LookupDialogCourse() {
		setTitle("查找");
		setSize(300,300);
		setLocationRelativeTo(pane);
		setModal(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		add(checkDialog());
	}
	public JPanel checkDialog() {
		String[] course= {"课程编号","课程名称"};
		JLabel check_label=new JLabel("查询信息:");
		JComboBox<String> combo=new JComboBox<>(course);
		combo.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
						item=(String)combo.getSelectedItem();
				}
				
			}
		});
		JTextField check_text=new JTextField(10);
		JPanel check_pane=new JPanel();
		check_pane.add(check_label);
		check_pane.add(combo);
		check_pane.add(check_text);
		
		JButton check_button=new JButton("查询");
		check_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(item.equals("课程编号")) {
					 sql="select*from tb_course where number="+"'"+check_text.getText()+"'";
				}else if(item.equals("课程名称")) {
					sql="select*from tb_course where name="+"'"+check_text.getText()+"'";
				}
				ArrayList<Course> courseList=SqlOperation.queryCourse(sql);
				if(!courseList.isEmpty()) {
					setVisible(false);
					LookupResultCourse lookupResultcourse=new LookupResultCourse();
					lookupResultcourse.setTable(table);
					try {
						lookupResultcourse.add(lookupResultcourse.checkResultPanel(courseList.get(0)));
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
					}
					lookupResultcourse.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(pane,"查无此人");
				}
			}
		});
		JButton cancel_button=new JButton("取消");
		
		cancel_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JPanel button_pane=new JPanel();
		button_pane.add(check_button);
		button_pane.add(cancel_button);
		
		JPanel panel=new JPanel(new GridLayout(3,1));
		panel.add(check_pane);
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
