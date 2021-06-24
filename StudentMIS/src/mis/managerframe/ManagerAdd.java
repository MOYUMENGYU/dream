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

import bean.Manager;
import factory.ManagerTableFactory;
import factory.TableFactoryAbstract;
import factory.UserTable;
import mis.tool.SqlOperation;

public class ManagerAdd implements Layout{
	CardLayout card=new CardLayout();
	JPanel pane=new JPanel(card);
	JTable table=new JTable();
	public JPanel managerAdd() {
		JLabel number_label=new JLabel("管理编号:");
		JTextField number_text=new JTextField(20);
		JPanel number_pane=new JPanel();
		number_pane.add(number_label);
		number_pane.add(number_text);
		
		JLabel name_label=new JLabel("姓    名:");
		JTextField name_text=new JTextField(20);
		JPanel name_pane=new JPanel();
		name_pane.add(name_label);
		name_pane.add(name_text);
		
		JLabel account_label=new JLabel("账    号:");
		JTextField account_text=new JTextField(20);
		JPanel account_pane=new JPanel();
		account_pane.add(account_label);
		account_pane.add(account_text);
		
		JLabel password_label=new JLabel("密    码:");
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
		
		JLabel address_label=new JLabel("地    址:");
		JTextField address_text=new JTextField(20);
		JPanel address_pane=new JPanel();
		address_pane.add(address_label);
		address_pane.add(address_text);
		
		JLabel remark_label=new JLabel("备    注:");
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
				Manager manager=new Manager();
				manager.setNumber(number_text.getText());
				manager.setName(name_text.getText());
				manager.setAccount(account_text.getText());
				manager.setPassword(password_text.getText());
				manager.setPhone(phone_text.getText());
				manager.setMail(mail_text.getText());
				manager.setAddress(address_text.getText());
				manager.setRemark(remark_text.getText());
				if(SqlOperation.saveManager(manager)>=0) {
					JOptionPane.showMessageDialog(pane,"保存成功");
					TableFactoryAbstract factory=new ManagerTableFactory();
					UserTable userTable=factory.createUserTable();
					userTable.plushTable(table);
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
