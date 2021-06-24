package factory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import mis.tool.SqlConnection;
/**
 * 管理员实现类
 * 继承接口，具体实现登录验证
 */
public class ManagerLogin extends User implements UserLogin{
	@Override
	public void LoginCheck() {
		//sql原先设置为实例变量，结果因为account也是实例变量且未初始化，所以sql为空。同是实例成员，且都没有初始化，实例成员不要相互调用
		String sql="select*from tb_manager where account="+"'"+account+"'";
		if(!account.equals("")&&!password.equals("")) {
			try {
				//连接数据库
				Statement state=SqlConnection.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				//查询结果
				ResultSet result=state.executeQuery(sql);
				if(result.next()) {
					//验证密码
					if(result.getString("password").equals(password)) {
						card.show(pane,"navigation");
					}else {
						JOptionPane.showMessageDialog(null,"密码不正确,请重新输入");
					}
				}else{
					JOptionPane.showMessageDialog(null,"请检查用户名是否正确");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}else {
			JOptionPane.showMessageDialog(null,"用户名或密码为空","错误",JOptionPane.ERROR_MESSAGE,null);
		}
	}
}
