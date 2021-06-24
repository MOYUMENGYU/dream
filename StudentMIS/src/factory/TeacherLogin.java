package factory;
/**
 * 教师实现类
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import bean.Teacher;
import mis.teacherPersonFrame.TeacherPersonMessage;
import mis.tool.SqlConnection;
import mis.tool.SqlOperation;

public class TeacherLogin extends User implements UserLogin{
	@Override
	public void LoginCheck() {
		String sql="select*from tb_teacher where account="+"'"+account+"'";
		if(!account.equals("")&&!password.equals("")) {
			try {
				Statement state=SqlConnection.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				ResultSet result=state.executeQuery(sql);
				if(result.next()) {
					if(result.getString("password").equals(password)) {
						TeacherPersonMessage teacherMessage=new TeacherPersonMessage();
						teacherMessage.setPane(pane);
						teacherMessage.setcard(card);
						Teacher teacher=SqlOperation.queryTeacher(sql).get(0);
						pane.add(teacherMessage.checkResultPanel(teacher),"teacherMessage");
						card.show(pane,"teacherNavigation");
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
