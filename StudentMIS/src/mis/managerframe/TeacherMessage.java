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

import bean.Teacher;
import factory.TableFactoryAbstract;
import factory.TeacherTableFactory;
import factory.UserTable;
import mis.tool.SqlOperation;

public class TeacherMessage implements Layout{
	
	CardLayout card=new CardLayout();
	JPanel pane=new JPanel(card);
//	JTable table=CreateTable.teacherTable();
	TableFactoryAbstract factory=new TeacherTableFactory();
	UserTable user=factory.createUserTable();
	JTable table=user.createTable();
	
	public JPanel teacherMessagePane() {
		
		JScrollPane srollPane=new JScrollPane(table);
		JPanel panel=new JPanel(new BorderLayout());
		panel.add(srollPane,BorderLayout.CENTER);
		JPanel button_pane=new JPanel();
		JButton revise_button=new JButton("修改");
		JButton keep_button=new JButton("编辑");
		JButton remove_button=new JButton("删除");
		JButton lookup_button=new JButton("查找");
		JButton return_button=new JButton("返回");
		
		revise_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				table.setEnabled(true);
			}
		});
		keep_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectRow=table.getSelectedRow();
				if(selectRow>=0) {
					Teacher teacher=new Teacher();
					teacher.setNumber((String)table.getModel().getValueAt(selectRow,0));
					teacher.setName((String)(table.getModel().getValueAt(selectRow,1)));
					teacher.setSex((String)(table.getModel().getValueAt(selectRow,2)));
					teacher.setAccount((String)(table.getModel().getValueAt(selectRow,3)));
					teacher.setPassword((String)(table.getModel().getValueAt(selectRow,4)));
					teacher.setPhone((String)(table.getModel().getValueAt(selectRow,5)));
					teacher.setMail((String)(table.getModel().getValueAt(selectRow,6)));
					teacher.setAddress((String)(table.getModel().getValueAt(selectRow,7)));
					teacher.setRemark((String)(table.getModel().getValueAt(selectRow,8)));
					LookupResultTeacher resultTeacher=new LookupResultTeacher();
					resultTeacher.setTable(table);
					resultTeacher.add(resultTeacher.checkResultPanel(teacher));
					resultTeacher.setVisible(true);
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
						SqlOperation.deleteTeacher(((String)table.getModel().getValueAt(selectRow,0)));
						user.plushTable(table);
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
				LookupDialogTeacher lookupTeacher=new LookupDialogTeacher();
				lookupTeacher.setTable(table);
				lookupTeacher.setVisible(true);
			}
		});
		button_pane.add(revise_button);
		button_pane.add(keep_button);
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
