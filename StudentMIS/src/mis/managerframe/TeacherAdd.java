package mis.managerframe;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import bean.Teacher;
import factory.TableFactoryAbstract;
import factory.TeacherTableFactory;
import factory.UserTable;
import mis.tool.SqlOperation;

public class TeacherAdd implements Layout{
	CardLayout card=new CardLayout();
	JPanel pane=new JPanel(card);
	JTable table=new JTable();
	public JPanel teacherAdd() {
		JLabel number_label=new JLabel("教师编号:");
		JTextField number_text=new JTextField(20);
		JPanel number_pane=new JPanel();
		number_pane.add(number_label);
		number_pane.add(number_text);
		
		JLabel name_label=new JLabel("姓    名:");
		JTextField name_text=new JTextField(20);
		JPanel name_pane=new JPanel();
		name_pane.add(name_label);
		name_pane.add(name_text);
		
		JLabel sex_label=new JLabel("性    别:");
		String[]sexs= {"男","女"};
		JComboBox<String> combo=new JComboBox<>(sexs);
		JPanel sex_pane=new JPanel();
		sex_pane.add(sex_label);
		sex_pane.add(combo);
		
		
		JLabel account_label=new JLabel("账     号:");
		JTextField account_text=new JTextField(20);
		JPanel account_pane=new JPanel();
		account_pane.add(account_label);
		account_pane.add(account_text);
		
		JLabel password_label=new JLabel("密     码:");
		JTextField password_text=new JTextField(20);
		JPanel password_pane=new JPanel();
		password_pane.add(password_label);
		password_pane.add(password_text);
		
		JLabel phone_label=new JLabel("电话号码:");
		JTextField phone_text=new JTextField(20);
		JPanel phone_pane=new JPanel();
		phone_pane.add(phone_label);
		phone_pane.add(phone_text);
		
		JLabel mail_label=new JLabel("电子邮箱:");
		JTextField mail_text=new JTextField(20);
		JPanel mail_pane=new JPanel();
		mail_pane.add(mail_label);
		mail_pane.add(mail_text);
		
		JLabel address_label=new JLabel("地     址:");
		JTextField address_text=new JTextField(20);
		JPanel address_pane=new JPanel();
		address_pane.add(address_label);
		address_pane.add(address_text);
		
		JLabel remark_label=new JLabel("备     注:");
		JTextField remark_text=new JTextField(20);
		JPanel remark_pane=new JPanel();
		remark_pane.add(remark_label);
		remark_pane.add(remark_text);
		
		JButton keep_button=new JButton("保存");
		JButton empty_button=new JButton("清空");
		JButton return_button=new JButton("返回");
		keep_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Teacher teacher=new Teacher();
				teacher.setNumber(number_text.getText());
				teacher.setName(name_text.getText());
				teacher.setSex((String)combo.getSelectedItem());
				teacher.setAccount(account_text.getText());
				teacher.setPassword(password_text.getText());
				teacher.setPhone(phone_text.getText());
				teacher.setMail(mail_text.getText());
				teacher.setAddress(address_text.getText());
				teacher.setRemark(remark_text.getText());
				if(SqlOperation.saveTeacher(teacher)>=0) {
					JOptionPane.showMessageDialog(pane,"保存成功");
//					PlushTable.plushTeacherTable(table);
					TableFactoryAbstract factory=new TeacherTableFactory();
					UserTable user=factory.createUserTable();
					user.plushTable(table);
				}
			}
		});
		empty_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				number_text.setText("");
				name_text.setText("");
				account_text.setText("");
				password_text.setText("");
				phone_text.setText("");
				mail_text.setText("");
				address_text.setText("");
				remark_text.setText("");
			}
		});
		return_button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				card.show(pane, "navigation");
				number_text.setText("");
				name_text.setText("");
				account_text.setText("");
				password_text.setText("");
				phone_text.setText("");
				mail_text.setText("");
				address_text.setText("");
				remark_text.setText("");
			}
		});
		JPanel button_pane=new JPanel();
		button_pane.add(keep_button);
		button_pane.add(empty_button);
		button_pane.add(return_button);
		
		JPanel panel=new JPanel(new GridLayout(10,1));
		panel.add(number_pane);
		panel.add(name_pane);
		panel.add(sex_pane);
		panel.add(account_pane);
		panel.add(password_pane);
		panel.add(phone_pane);
		panel.add(mail_pane);
		panel.add(address_pane);
		panel.add(remark_pane);
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
