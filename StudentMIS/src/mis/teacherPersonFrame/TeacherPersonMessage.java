package mis.teacherPersonFrame;

import java.awt.BorderLayout;
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

import bean.Teacher;
import factory.TableFactoryAbstract;
import factory.TeacherTableFactory;
import factory.UserTable;
import mis.managerframe.Layout;
import mis.tool.SqlOperation;

public class TeacherPersonMessage implements Layout{
	CardLayout card=new CardLayout();
	JPanel pane=new JPanel(card);
	JTable table=new JTable();
	
	public JPanel checkResultPanel(Teacher teacher) {	
		
		JLabel number_label=new JLabel("教师编号:");
		JTextField number_text=new JTextField(10);
		number_text.setText(teacher.getNumber());
		number_text.setEnabled(false);
		JPanel number_pane=new JPanel();
		number_pane.add(number_label);
		number_pane.add(number_text);
		
		JLabel name_label=new JLabel("姓    名:");
		JTextField name_text=new JTextField(10);
		name_text.setText(teacher.getName());
		name_text.setEnabled(false);
		JPanel name_pane=new JPanel();
		name_pane.add(name_label);
		name_pane.add(name_text);
		
		JLabel sex_label=new JLabel("性    别:");
		JTextField sex_text=new JTextField(10);
		sex_text.setText(teacher.getSex());
		sex_text.setEnabled(false);
		JPanel sex_pane=new JPanel();
		sex_pane.add(sex_label);
		sex_pane.add(sex_text);
		
		
		JLabel account_label=new JLabel("账    号:");
		JTextField account_text=new JTextField(10);
		account_text.setText(teacher.getAccount());
		account_text.setEnabled(false);
		JPanel account_pane=new JPanel();
		account_pane.add(account_label);
		account_pane.add(account_text);
		
		JLabel password_label=new JLabel("密    码:");
		JTextField password_text=new JTextField(10);
		password_text.setText(teacher.getPassword());
		password_text.setEnabled(false);
		JPanel password_pane=new JPanel();
		password_pane.add(password_label);
		password_pane.add(password_text);
		
		JLabel phone_label=new JLabel("电话号码:");
		JTextField phone_text=new JTextField(10);
		phone_text.setText(teacher.getPhone());
		phone_text.setEnabled(false);
		JPanel phone_pane=new JPanel();
		phone_pane.add(phone_label);
		phone_pane.add(phone_text);
		
		JLabel mail_label=new JLabel("电子邮箱:");
		JTextField mail_text=new JTextField(10);
		mail_text.setText(teacher.getMail());
		mail_text.setEnabled(false);
		JPanel mail_pane=new JPanel();
		mail_pane.add(mail_label);
		mail_pane.add(mail_text);
		
		JLabel address_label=new JLabel("地    址:");
		JTextField address_text=new JTextField(10);
		address_text.setText(teacher.getAddress());
		address_text.setEnabled(false);
		JPanel address_pane=new JPanel();
		address_pane.add(address_label);
		address_pane.add(address_text);
		
		JLabel remark_label=new JLabel("备    注:");
		JTextField remark_text=new JTextField(10);
		remark_text.setText(teacher.getRemark());
		remark_text.setEnabled(false);
		JPanel remark_pane=new JPanel();
		remark_pane.add(remark_label);
		remark_pane.add(remark_text);
		
		JButton revise_button=new JButton("修改");
		revise_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				name_text.setEnabled(true);
				sex_text.setEnabled(true);
				password_text.setEnabled(true);
				phone_text.setEnabled(true);
				mail_text.setEnabled(true);
				address_text.setEnabled(true);
				remark_text.setEnabled(true);
			}
		});
		JButton keep_button=new JButton("保存");
		keep_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Teacher teacher=new Teacher();
				teacher.setNumber(number_text.getText());
				teacher.setName(name_text.getText());
				teacher.setSex(sex_text.getText());
				teacher.setAccount(account_text.getText());
				teacher.setPassword(password_text.getText());
				teacher.setPhone(phone_text.getText());
				teacher.setMail(mail_text.getText());
				teacher.setAddress(address_text.getText());
				teacher.setRemark(remark_text.getText());
				int i=SqlOperation.updateTeacher(teacher);
				if(i>0) {
					JOptionPane.showMessageDialog(pane,"保存成功");
					TableFactoryAbstract factory=new TeacherTableFactory();
					UserTable user=factory.createUserTable();
					user.plushTable(table);
				}
			}
			
	});
		JButton return_button=new JButton("返回");
		return_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				card.show(pane,"teacherNavigation");
			}
		});
		JPanel button_pane=new JPanel();
		button_pane.add(revise_button);
		button_pane.add(keep_button);
		button_pane.add(return_button);
				
		
		JPanel panel=new JPanel(new GridLayout(5,2));
		panel.add(number_pane);
		panel.add(name_pane);
		panel.add(sex_pane);
		panel.add(account_pane);
		panel.add(password_pane);
		panel.add(phone_pane);
		panel.add(mail_pane);
		panel.add(address_pane);
		panel.add(remark_pane);
		
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
