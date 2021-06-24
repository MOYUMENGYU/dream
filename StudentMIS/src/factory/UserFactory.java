package factory;

import java.awt.CardLayout;

import javax.swing.JPanel;

import mis.managerframe.Layout;
/**
 * 工厂：因为已经知道有三种用户，所以使用简单工厂
 * 简单工厂(相关生产事务信息因在方法参数列表里)
 * 负责生产接口引用(方法)多态：上转型
 * 具体实现还是交由实现类
 */
public class UserFactory implements Layout{
	
	CardLayout card=new CardLayout();
	JPanel pane=new JPanel(card);
	//传入信息
	public UserLogin createUser(String user,String account,String password) {
		if(user.equals("系统管理员")) {
			//创建对象，具体类再方法里创建
			ManagerLogin manager=new ManagerLogin();
			manager.setPane(pane);
			manager.setCard(card);
			manager.setAccount(account);
			manager.setPassword(password);
			return manager;
		}else if(user.equals("教师")) {
			TeacherLogin teacher=new TeacherLogin();
			teacher.setPane(pane);
			teacher.setCard(card);
			teacher.setAccount(account);
			teacher.setPassword(password);
			return teacher;
		}else if(user.equals("学生")) {
			StudentLogin student=new StudentLogin();
			student.setPane(pane);
			student.setCard(card);
			student.setAccount(account);
			student.setPassword(password);
			return student;
		}
		return null;
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
