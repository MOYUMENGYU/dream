package mis.managerframe;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import bean.Student;
import factory.StudentTableFactory;
import factory.TableFactoryAbstract;
import factory.UserTable;
import mis.tool.SqlOperation;

public class StudentMessage implements Layout{
	CardLayout card=new CardLayout();
	JPanel pane=new JPanel(card);
	TableFactoryAbstract factory=new StudentTableFactory();
	UserTable user=factory.createUserTable();
	JTable table=user.createTable();
//	JTable table=CreateTable.studentTable();
	public JPanel studentMessagePane() {
		
		JScrollPane srollPane=new JScrollPane(table);
		JPanel panel=new JPanel(new BorderLayout());
		panel.add(srollPane,BorderLayout.CENTER);
		JPanel button_pane=new JPanel();
		JButton edit_button=new JButton("编辑");
		JButton revise_button=new JButton("修改");
		JButton remove_button=new JButton("删除");
		JButton lookup_button=new JButton("查找");
		JButton return_button=new JButton("返回");
		
		edit_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				table.setEnabled(true);
			}
		});
		revise_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectRow=table.getSelectedRow();
				if(selectRow>=0) {
					Student student=new Student();
					student.setNumber((String)table.getModel().getValueAt(selectRow,0));
					student.setName((String)(table.getModel().getValueAt(selectRow,1)));
					student.setSex((String)(table.getModel().getValueAt(selectRow,2)));
					student.setAccount((String)(table.getModel().getValueAt(selectRow,3)));
					student.setPassword((String)(table.getModel().getValueAt(selectRow,4)));
					student.setPhone((String)(table.getModel().getValueAt(selectRow,5)));
					student.setMail((String)(table.getModel().getValueAt(selectRow,6)));
					student.setAddress((String)(table.getModel().getValueAt(selectRow,7)));
					student.setCollage((String)(table.getModel().getValueAt(selectRow,8)));
					student.setMajor((String)(table.getModel().getValueAt(selectRow,9)));
					student.setClassNumber((String)(table.getModel().getValueAt(selectRow,10)));
					student.setRemark((String)(table.getModel().getValueAt(selectRow,11)));
					LookupResultStudent resultstudent=new LookupResultStudent();
					resultstudent.setTable(table);
					resultstudent.add(resultstudent.checkResultPanel(student));
					resultstudent.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(pane,"请先选择编辑行");
				}
			}
		});
		remove_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectRow=table.getSelectedRow();
				if(selectRow>=0) {
					int order=JOptionPane.showConfirmDialog(pane,"是否删除","提示",JOptionPane.OK_CANCEL_OPTION);
					if(order==JOptionPane.OK_OPTION) {
						SqlOperation.deleteStudent(((String)table.getModel().getValueAt(selectRow,0)));
						user.plushTable(table);
//						PlushTable.plushStudentTable(table);
					}
				}else {
					JOptionPane.showMessageDialog(pane,"请先选择数据行");
				}
			}
		});
		return_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				table.setEnabled(false);
				card.show(pane,"navigation");
			}
		});
		lookup_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LookupDialogStudent lookupstudent=new LookupDialogStudent();
				lookupstudent.setTable(table);
				lookupstudent.setVisible(true);
			}
		});
		button_pane.add(edit_button);
		button_pane.add(revise_button);
		button_pane.add(remove_button);
		button_pane.add(lookup_button);
		button_pane.add(return_button);
		panel.add(button_pane,BorderLayout.SOUTH);
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
