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

import bean.Manager;
import factory.ManagerTableFactory;
import factory.TableFactoryAbstract;
import factory.UserTable;
import mis.tool.SqlOperation;
/**
 * 对话框：查找管理员信息结果
 * @author 魔宇
 *
 */
@SuppressWarnings("serial")
public class LookupResultManager extends JDialog implements Layout{
	CardLayout card=new CardLayout();
	JPanel pane=new JPanel(card);
	JTable table=new JTable();
	public LookupResultManager() {
		setTitle("查询结果");
		setSize(400,400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(pane);
		setModal(true);
	}
	public JPanel checkResultPanel(Manager manager) {	
		//从对象参数中取出各个信息填入文本框中，文本框默认不可操作
		JLabel number_label=new JLabel("管理编号:");
		JTextField number_text=new JTextField(10);
		number_text.setText(manager.getNumber());
		number_text.setEnabled(false);
		JPanel number_pane=new JPanel();
		number_pane.add(number_label);
		number_pane.add(number_text);
		
		JLabel name_label=new JLabel("姓    名:");
		JTextField name_text=new JTextField(10);
		name_text.setText(manager.getName());
		name_text.setEnabled(false);
		JPanel name_pane=new JPanel();
		name_pane.add(name_label);
		name_pane.add(name_text);
		
		JLabel account_label=new JLabel("账    号:");
		JTextField account_text=new JTextField(10);
		account_text.setText(manager.getAccount());
		account_text.setEnabled(false);
		JPanel account_pane=new JPanel();
		account_pane.add(account_label);
		account_pane.add(account_text);
		
		JLabel password_label=new JLabel("密    码:");
		JTextField password_text=new JTextField(10);
		password_text.setText(manager.getPassword());
		password_text.setEnabled(false);
		JPanel password_pane=new JPanel();
		password_pane.add(password_label);
		password_pane.add(password_text);
		
		JLabel phone_label=new JLabel("电话号码:");
		JTextField phone_text=new JTextField(10);
		phone_text.setText(manager.getPhone());
		phone_text.setEnabled(false);
		JPanel phone_pane=new JPanel();
		phone_pane.add(phone_label);
		phone_pane.add(phone_text);
		
		JLabel mail_label=new JLabel("电子邮箱:");
		JTextField mail_text=new JTextField(10);
		mail_text.setText(manager.getMail());
		mail_text.setEnabled(false);
		JPanel mail_pane=new JPanel();
		mail_pane.add(mail_label);
		mail_pane.add(mail_text);
		
		JLabel address_label=new JLabel("地    址:");
		JTextField address_text=new JTextField(10);
		address_text.setText(manager.getAddress());
		address_text.setEnabled(false);
		JPanel address_pane=new JPanel();
		address_pane.add(address_label);
		address_pane.add(address_text);
		
		JLabel remark_label=new JLabel("备    注:");
		JTextField remark_text=new JTextField(10);
		remark_text.setText(manager.getRemark());
		remark_text.setEnabled(false);
		JPanel remark_pane=new JPanel();
		remark_pane.add(remark_label);
		remark_pane.add(remark_text);
		
		JButton revise_button=new JButton("修改");
		//将文本框改为可操作
		revise_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				name_text.setEnabled(true);
				account_text.setEnabled(true);
				password_text.setEnabled(true);
				phone_text.setEnabled(true);
				mail_text.setEnabled(true);
				address_text.setEnabled(true);
				remark_text.setEnabled(true);
			}
		});
		JButton keep_button=new JButton("保存");
		//将文本框的信息取出封装进管理员对象，再做为参数传入方法进行更新
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
				int i=SqlOperation.updateManager(manager);
				if(i>0) {
					JOptionPane.showMessageDialog(pane,"保存成功");
					//创建工厂
					TableFactoryAbstract factory=new ManagerTableFactory();
					//通过工厂创建表格对象
					UserTable user=factory.createUserTable();
					//刷新表格
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
