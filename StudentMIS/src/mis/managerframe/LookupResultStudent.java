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

import bean.Student;
import factory.StudentTableFactory;
import factory.TableFactoryAbstract;
import factory.UserTable;
import mis.tool.SqlOperation;

@SuppressWarnings("serial")
public class LookupResultStudent extends JDialog implements Layout{
	CardLayout card=new CardLayout();
	JPanel pane=new JPanel(card);
	JTable table=new JTable();
	public LookupResultStudent() {
		setTitle("信息");
		setSize(400,400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(pane);
		setModal(true);
	}
	public JPanel checkResultPanel(Student student) {	
		
		JLabel number_label=new JLabel("学号编号:");
		JTextField number_text=new JTextField(10);
		number_text.setText(student.getNumber());
		number_text.setEnabled(false);
		JPanel number_pane=new JPanel();
		number_pane.add(number_label);
		number_pane.add(number_text);
		
		JLabel name_label=new JLabel("姓    名:");
		JTextField name_text=new JTextField(10);
		name_text.setText(student.getName());
		name_text.setEnabled(false);
		JPanel name_pane=new JPanel();
		name_pane.add(name_label);
		name_pane.add(name_text);
		
		JLabel sex_label=new JLabel("性    别:");
		JTextField sex_text=new JTextField(10);
		sex_text.setText(student.getSex());
		sex_text.setEnabled(false);
		JPanel sex_pane=new JPanel();
		sex_pane.add(sex_label);
		sex_pane.add(sex_text);
		
		
		JLabel account_label=new JLabel("账    号:");
		JTextField account_text=new JTextField(10);
		account_text.setText(student.getAccount());
		account_text.setEnabled(false);
		JPanel account_pane=new JPanel();
		account_pane.add(account_label);
		account_pane.add(account_text);
		
		JLabel password_label=new JLabel("密    码:");
		JTextField password_text=new JTextField(10);
		password_text.setText(student.getPassword());
		password_text.setEnabled(false);
		JPanel password_pane=new JPanel();
		password_pane.add(password_label);
		password_pane.add(password_text);
		
		JLabel phone_label=new JLabel("电话号码:");
		JTextField phone_text=new JTextField(10);
		phone_text.setText(student.getPhone());
		phone_text.setEnabled(false);
		JPanel phone_pane=new JPanel();
		phone_pane.add(phone_label);
		phone_pane.add(phone_text);
		
		JLabel mail_label=new JLabel("电子邮箱:");
		JTextField mail_text=new JTextField(10);
		mail_text.setText(student.getMail());
		mail_text.setEnabled(false);
		JPanel mail_pane=new JPanel();
		mail_pane.add(mail_label);
		mail_pane.add(mail_text);
		
		JLabel address_label=new JLabel("地    址:");
		JTextField address_text=new JTextField(10);
		address_text.setText(student.getAddress());
		address_text.setEnabled(false);
		JPanel address_pane=new JPanel();
		address_pane.add(address_label);
		address_pane.add(address_text);
		
		JLabel collage_label=new JLabel("学     院:");
		JTextField collage_text=new JTextField(10);
		collage_text.setText(student.getCollage());
		collage_text.setEnabled(false);
		JPanel collage_pane=new JPanel();
		collage_pane.add(collage_label);
		collage_pane.add(collage_text);
		
		JLabel major_label=new JLabel("专    业:");
		JTextField major_text=new JTextField(10);
		major_text.setText(student.getMajor());
		major_text.setEnabled(false);
		JPanel major_pane=new JPanel();
		major_pane.add(major_label);
		major_pane.add(major_text);
		
		JLabel classNumber_label=new JLabel("班    级:");
		JTextField classNumber_text=new JTextField(10);
		classNumber_text.setText(student.getClassNumber());
		classNumber_text.setEnabled(false);
		JPanel classNumber_pane=new JPanel();
		classNumber_pane.add(classNumber_label);
		classNumber_pane.add(classNumber_text);
		
		JLabel remark_label=new JLabel("备    注:");
		JTextField remark_text=new JTextField(10);
		remark_text.setText(student.getRemark());
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
				account_text.setEnabled(true);
				password_text.setEnabled(true);
				phone_text.setEnabled(true);
				mail_text.setEnabled(true);
				address_text.setEnabled(true);
				collage_text.setEnabled(true);
				major_text.setEnabled(true);
				classNumber_text.setEnabled(true);
				remark_text.setEnabled(true);
			}
		});
		JButton keep_button=new JButton("保存");
		keep_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Student student=new Student();
				student.setNumber(number_text.getText());
				student.setName(name_text.getText());
				student.setSex(sex_text.getText());
				student.setAccount(account_text.getText());
				student.setPassword(password_text.getText());
				student.setPhone(phone_text.getText());
				student.setMail(mail_text.getText());
				student.setAddress(address_text.getText());
				student.setCollage(collage_text.getText());
				student.setMajor(major_text.getText());
				student.setClassNumber(classNumber_text.getText());
				student.setRemark(remark_text.getText());
				int i=SqlOperation.updateStudent(student);
				if(i>=0) {
					JOptionPane.showMessageDialog(pane,"保存成功");
					TableFactoryAbstract factory=new StudentTableFactory();
					UserTable user=factory.createUserTable();
					user.plushTable(table);
				}else {
					JOptionPane.showMessageDialog(pane,"保存失败");
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
				
		
		JPanel panel=new JPanel(new GridLayout(6,2));
		panel.add(number_pane);
		panel.add(name_pane);
		panel.add(sex_pane);
		panel.add(account_pane);
		panel.add(password_pane);
		panel.add(phone_pane);
		panel.add(mail_pane);
		panel.add(address_pane);
		panel.add(collage_pane);
		panel.add(major_pane);
		panel.add(classNumber_pane);
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
