package mis.managerframe;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import factory.UserLogin;
import factory.UserFactory;

public class Login implements Layout{
	CardLayout card=new CardLayout();
	JPanel pane=new JPanel(card);
	//用户登录类型
	private static String[] type= {"系统管理员","教师","学生"};
	private static String item_type="系统管理员";
	JPanel panel=new JPanel(new GridLayout(6,1));
	
	public JPanel loginPane() {
		
		JPanel hello_pane=new JPanel();
		JLabel hello_label=new JLabel("学生信息管理系统");
		hello_label.setFont(new Font(null,Font.BOLD,28));
		hello_pane.add(hello_label);
		
		JPanel type_pane=new JPanel();
		JLabel type_label=new JLabel("用户类型:");
		JComboBox<String> combo=new JComboBox<>(type);
		combo.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					item_type=(String)combo.getSelectedItem();
				}
			}
		});	
		type_pane.add(type_label);
		type_pane.add(combo);
		JPanel name_pane=new JPanel();
		JLabel name_label=new JLabel("账 号:");
		name_label.setHorizontalAlignment(SwingConstants.LEFT);
		JTextField name_text=new JTextField(20);
		name_pane.add(name_label);
		name_pane.add(name_text);
		
		JPanel password_pane=new JPanel();
		JLabel password_label=new JLabel("密    码:");
		password_label.setHorizontalAlignment(SwingConstants.LEFT);
		JPasswordField password_text=new JPasswordField(20);
		password_pane.add(password_label);
		password_pane.add(password_text);
		
		JPanel button_pane=new JPanel();
		JButton login_button=new JButton("登录");
		JButton cancel_button=new JButton("取消");
		button_pane.add(login_button);
		button_pane.add(cancel_button);
	
		panel.add(hello_pane);
		panel.add(type_pane);
		panel.add(name_pane);
		panel.add(password_pane);
		panel.add(button_pane);
		//登录
		login_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//获取输入的账号和密码
				String account=name_text.getText();
				String password=new String(password_text.getPassword());
				//创建登录工厂
				UserFactory factory=new UserFactory();
				factory.setPane(pane);
				factory.setcard(card);
				//获取登录用户的对象，并上转型为接口引用。item_type:登录用户类型
				UserLogin user=factory.createUser(item_type, account, password);
				/**
				 * 典型多态
				 * 工厂模式，使客户端，即使用功能的类中，代码变得简洁，不必考虑是那一种用户类型进行登录
				 * 而把繁杂的代码实现交给了其他类(具体实现类、工厂类)
				 * 
				 */
				user.LoginCheck();
				name_text.setText("");
				password_text.setText("");
			}
		});
		cancel_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int order=JOptionPane.showConfirmDialog(pane,"是否退出！","提示",JOptionPane.YES_OPTION);
				if(order==JOptionPane.YES_OPTION) {
					System.exit(0);	
				}
			}
		});
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
