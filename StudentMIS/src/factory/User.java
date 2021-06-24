package factory;
/**
 * 登录用户
 */
import java.awt.CardLayout;

import javax.swing.JPanel;

/**
 * 登录用户
 * @author 魔宇
 *
 */
public class User {
	//用户类型
	String user;
	//账号密码
	String account;
	String password;
	
	CardLayout card=new CardLayout();
	JPanel pane=new JPanel(card);
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public JPanel getPane() {
		return pane;
	}
	public void setPane(JPanel pane) {
		this.pane = pane;
	}
	public CardLayout getCard() {
		return card;
	}
	public void setCard(CardLayout card) {
		this.card = card;
	}
}
