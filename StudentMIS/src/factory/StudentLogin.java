package factory;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * 学生实现类
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import bean.Student;
import mis.studentPersonFrame.StudentPersonMessage;
import mis.tool.SqlConnection;
import mis.tool.SqlOperation;
/**
 * 学生登录实现类
 * @author 魔宇
 *
 */
public class StudentLogin extends User implements UserLogin{
	@Override
	public void LoginCheck() {
		String sql="select*from tb_student where account="+"'"+account+"'";
		if(!account.equals("")&&!password.equals("")) {
			try {
				Statement state=SqlConnection.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				ResultSet result=state.executeQuery(sql);
				if(result.next()) {
					if(result.getString("password").equals(password)) {
						StudentPersonMessage studentMessage=new StudentPersonMessage();
						studentMessage.setPane(pane);
						studentMessage.setcard(card);
						Student student=SqlOperation.queryStudent(sql).get(0);
						pane.add(studentMessage.checkResultPanel(student),"studentMessage");
						StudentPersonFactory factory=new StudentPersonFactory();
						factory.setPane(pane);
						factory.setCard(card);
						StudentPerson person=factory.createStudentPerson(student.getMajor(),student.getNumber());
						if(person!=null) {
							pane.add(person.studenCourse(),"studentCourse");
							pane.add(person.studentScore(student.getName()),"studentScore");
						}else {
							JPanel panel=new JPanel(new BorderLayout());
							JLabel tip_label=new JLabel("您的成绩没有找到！");
							tip_label.setFont(new Font(null,Font.BOLD,18));
							tip_label.setHorizontalAlignment(SwingConstants.CENTER);
							
							JPanel course_panel=new JPanel(new BorderLayout());
							JLabel course_tip_label=new JLabel("课程没有开始！");
							course_tip_label.setFont(new Font(null,Font.BOLD,18));
							course_tip_label.setHorizontalAlignment(SwingConstants.CENTER);
							
							JButton return_button=new JButton("返回");
							return_button.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									card.show(pane,"studentNavigation");
								}
							});
							
							JButton return_button2=new JButton("返回");
							return_button2.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
									card.show(pane,"studentNavigation");
								}
							});
							panel.add(tip_label,BorderLayout.NORTH);
							panel.add(return_button,BorderLayout.SOUTH);
							course_panel.add(course_tip_label,BorderLayout.NORTH);
							course_panel.add(return_button2,BorderLayout.SOUTH);
							pane.add(panel,"studentScore");
							pane.add(course_panel,"studentCourse");
						}
						card.show(pane,"studentNavigation");	
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
