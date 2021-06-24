package mis.tool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.Course;
import bean.DataScore;
import bean.Manager;
import bean.Student;
import bean.Teacher;
/**
 * 数据库操作类
 * @author 魔宇
 *
 */
public class SqlOperation {
	/**
	 * 数据库管理员表中插入数据
	 * @param manager
	 * @return
	 */
	public static int saveManager(Manager manager) {
		PreparedStatement prepare=null;
		//用于标识操作是否成功
		int i=0;
		try {
			String sql="insert into tb_manager values(?,?,?,?,?,?,?,?,?)";
			 prepare=SqlConnection.getConnection().prepareStatement(sql);
			 prepare.setObject(1,null);
			 prepare.setString(2,manager.getNumber());
			 prepare.setString(3,manager.getName());
			 prepare.setString(4,manager.getAccount());
			 prepare.setString(5,manager.getPassword());
			 prepare.setString(6,manager.getPhone());
			 prepare.setString(7,manager.getMail());
			 prepare.setString(8,manager.getAddress());
			 prepare.setString(9,manager.getRemark());
			 i=prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//关闭
			if(prepare!=null) {
				try {
					prepare.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return i;
	}
	/**
	 * 数据库管理员表中查询信息
	 * 将查询结果放入ArrayList中，方便操作
	 * 返回一个ArrayList
	 * @param sql
	 * @return
	 */
	public static ArrayList<Manager> queryManager(String sql){
		Statement state=null;
		ResultSet result=null;
		Manager manager=null;
		ArrayList<Manager> managerList=new ArrayList<Manager>();
		try {
			//连接数据库，创建查询
			 state=SqlConnection.getConnection().createStatement();
			 result=state.executeQuery(sql);
			 //遍历查询结果，放入ArrayList中
			 while(result.next()) {
				 manager=new Manager();
				 manager.setNumber(result.getString("number"));
				 manager.setName(result.getString("name"));
				 manager.setAccount(result.getString("account"));
				 manager.setPassword(result.getString("password"));
				 manager.setPhone(result.getString("phone"));
				 manager.setMail(result.getString("mail"));
				 manager.setAddress(result.getString("address"));
				 manager.setRemark(result.getString("remark"));
				 managerList.add(manager);
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(state!=null) {
				try {
					state.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return managerList;
	}
	/**
	 * 数据库管理员表更新
	 * @param manager
	 * @return
	 */
	public static int updateManager(Manager manager) {
		PreparedStatement prepare=null;
		int i=0;
		String sql="update tb_manager set number=?,name=?,account=?,password=?,phone=?,mail=?,address=?,"
				+ "remark=? where number=?";
		try {
			prepare=SqlConnection.getConnection().prepareStatement(sql);
			 prepare.setString(1,manager.getNumber());
			 prepare.setString(2,manager.getName());
			 prepare.setString(3,manager.getAccount());
			 prepare.setString(4,manager.getPassword());
			 prepare.setString(5,manager.getPhone());
			 prepare.setString(6,manager.getMail());
			 prepare.setString(7,manager.getAddress());
			 prepare.setString(8,manager.getRemark());
			 prepare.setString(9,manager.getNumber());
			 //执行操作
			 i=prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(prepare!=null) {
				try {
					prepare.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return i;
	}
	/**
	 * 数据库管理员表删除记录
	 * @param number
	 * @return
	 */
	public static int deleteManager(String number) {
		String sql="delete from tb_manager where number="+"'"+number+"'";
		Statement state=null;
		int i=0;
		try {
			state=SqlConnection.getConnection().createStatement();
			//执行操作
			i=state.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(state!=null) {
				try {
					state.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return i;
	}
	public static ArrayList<Teacher> queryTeacher(String sql){
		Statement state=null;
		ResultSet result=null;
		Teacher teacher=null;
		ArrayList<Teacher>teacherList=new ArrayList<>();
		try {
			 state=SqlConnection.getConnection().createStatement();
			 result=state.executeQuery(sql);
			 while(result.next()) {
				 teacher=new Teacher();
				 teacher.setNumber(result.getString("number"));
				 teacher.setName(result.getString("name"));
				 teacher.setSex(result.getString("sex"));
				 teacher.setAccount(result.getString("account"));
				 teacher.setPassword(result.getString("password"));
				 teacher.setPhone(result.getString("phone"));
				 teacher.setMail(result.getString("mail"));
				 teacher.setAddress(result.getString("address"));
				 teacher.setRemark(result.getString("remark"));
				 teacherList.add(teacher);
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(state!=null) {
				try {
					state.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return teacherList;
	}
	
	public static int saveTeacher(Teacher teacher) {
		PreparedStatement prepare=null;
		int i=0;
		try {
			String sql="insert into tb_teacher values(?,?,?,?,?,?,?,?,?,?)";
			 prepare=SqlConnection.getConnection().prepareStatement(sql);
			 prepare.setObject(1,null);
			 prepare.setString(2,teacher.getNumber());
			 prepare.setString(3,teacher.getName());
			 prepare.setString(4,teacher.getSex());
			 prepare.setString(5,teacher.getAccount());
			 prepare.setString(6,teacher.getPassword());
			 prepare.setString(7,teacher.getPhone());
			 prepare.setString(8,teacher.getMail());
			 prepare.setString(9,teacher.getAddress());
			 prepare.setString(10,teacher.getRemark());
			 i=prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public static int updateTeacher(Teacher teacher) {
		PreparedStatement prepare=null;
		int i=0;
		String sql="update tb_teacher set number=?,name=?,sex=?,account=?,password=?,phone=?,mail=?,address=?,"
				+ "remark=? where number=?";
		try {
			prepare=SqlConnection.getConnection().prepareStatement(sql);
			 prepare.setString(1,teacher.getNumber());
			 prepare.setString(2,teacher.getName());
			 prepare.setString(3,teacher.getSex());
			 prepare.setString(4,teacher.getAccount());
			 prepare.setString(5,teacher.getPassword());
			 prepare.setString(6,teacher.getPhone());
			 prepare.setString(7,teacher.getMail());
			 prepare.setString(8,teacher.getAddress());
			 prepare.setString(9,teacher.getRemark());
			 prepare.setString(10,teacher.getNumber());
			 i=prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}	
	
	public static int deleteTeacher(String number) {
		String sql="delete from tb_teacher where number="+"'"+number+"'";
		Statement state;
		int result=0;
		try {
			state=SqlConnection.getConnection().createStatement();
			result=state.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static ArrayList<Student> queryStudent(String sql){
		Statement state=null;
		ResultSet result=null;
		Student student=null;
		ArrayList<Student>studentList=new ArrayList<>();
		try {
			 state=SqlConnection.getConnection().createStatement();
			 result=state.executeQuery(sql);
			 while(result.next()) {
				 student=new Student();
				 student.setNumber(result.getString("number"));
				 student.setName(result.getString("name"));
				 student.setSex(result.getString("sex"));
				 student.setAccount(result.getString("account"));
				 student.setPassword(result.getString("password"));
				 student.setPhone(result.getString("phone"));
				 student.setMail(result.getString("mail"));
				 student.setAddress(result.getString("address"));
				 student.setCollage(result.getString("collage"));
				 student.setMajor(result.getString("major"));
				 student.setClassNumber(result.getString("classNumber"));
				 student.setRemark(result.getString("remark"));
				 studentList.add(student);
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(state!=null) {
				try {
					state.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return studentList;
	}
	
	public static int saveStudent(Student student) {
		PreparedStatement prepare=null;
		String sql="insert into tb_student values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int i=0;
		try {
			 prepare=SqlConnection.getConnection().prepareStatement(sql);
			 prepare.setObject(1,null);
			 prepare.setString(2,student.getNumber());
			 prepare.setString(3,student.getName());
			 prepare.setString(4,student.getSex());
			 prepare.setString(5,student.getAccount());
			 prepare.setString(6,student.getPassword());
			 prepare.setString(7,student.getPhone());
			 prepare.setString(8,student.getMail());
			 prepare.setString(9,student.getAddress());
			 prepare.setString(10,student.getCollage());
			 prepare.setString(11,student.getMajor());
			 prepare.setString(12,student.getClassNumber());
			 prepare.setString(13,student.getRemark());
			 i=prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	
	public static int updateStudent(Student student) {
		PreparedStatement prepare=null;
		int i=0;
		String sql="update tb_student set number=?,name=?,sex=?,account=?,password=?,phone=?,mail=?,address=?,"
				+ "collage=?,major=?,classNumber=?,remark=? where number=?";
		try {
			prepare=SqlConnection.getConnection().prepareStatement(sql);
			 prepare.setString(1,student.getNumber());
			 prepare.setString(2,student.getName());
			 prepare.setString(3,student.getSex());
			 prepare.setString(4,student.getAccount());
			 prepare.setString(5,student.getPassword());
			 prepare.setString(6,student.getPhone());
			 prepare.setString(7,student.getMail());
			 prepare.setString(8,student.getAddress());
			 prepare.setString(9,student.getCollage());
			 prepare.setString(10,student.getMajor());
			 prepare.setString(11,student.getClassNumber());
			 prepare.setString(12,student.getRemark());
			 prepare.setString(13,student.getNumber());
			 i=prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}	
	
	public static int deleteStudent(String number) {
		String sql="delete from tb_student where number="+"'"+number+"'";
		Statement state;
		int result=0;
		try {
			state=SqlConnection.getConnection().createStatement();
			result=state.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static ArrayList<Course> queryCourse(String sql){
		Statement state=null;
		ResultSet result=null;
		Course course=null;
		ArrayList<Course>courseList=new ArrayList<>();
		try {
			 state=SqlConnection.getConnection().createStatement();
			 result=state.executeQuery(sql);
			 while(result.next()) {
				 course=new Course();
				 course.setNumber(result.getString("number"));
				 course.setName(result.getString("name"));
				 course.setTeacher(result.getString("teacher"));
				 course.setCredit(result.getDouble("credit"));
				 course.setHour(result.getString("hour"));
				 courseList.add(course);
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(state!=null) {
				try {
					state.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return courseList;
	}
	
	public static int saveCourse(Course course) {
		PreparedStatement prepare=null;
		String sql="insert into tb_course values(?,?,?,?,?,?)";
		int i=0;
		try {
			 prepare=SqlConnection.getConnection().prepareStatement(sql);
			 prepare.setObject(1,null);
			 prepare.setString(2,course.getNumber());
			 prepare.setString(3,course.getName());
			 prepare.setString(4,course.getTeacher());
			 prepare.setDouble(5,course.getCredit());
			 prepare.setString(6,course.getHour());
			 i=prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	
	public static int updateCourse(Course course) {
		PreparedStatement prepare=null;
		int i=0;
		String sql="update tb_course set number=?,name=?,teacher=?,credit=?,hour=? where number=?";
		try {
			 prepare=SqlConnection.getConnection().prepareStatement(sql);
			 prepare.setString(1,course.getNumber());
			 prepare.setString(2,course.getName());
			 prepare.setString(3,course.getTeacher());
			 prepare.setDouble(4,course.getCredit());
			 prepare.setString(5,course.getHour());
			 prepare.setString(6,course.getNumber());
			 i=prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}	
	
	public static int deleteCourse(String number) {
		String sql="delete from tb_course where number="+"'"+number+"'";
		Statement state;
		int result=0;
		try {
			state=SqlConnection.getConnection().createStatement();
			result=state.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static ArrayList<DataScore> queryDataScore(String sql){
		Statement state=null;
		ResultSet result=null;
		DataScore score=null;
		ArrayList<DataScore>scoreList=new ArrayList<>();
		try {
			 state=SqlConnection.getConnection().createStatement();
			 result=state.executeQuery(sql);
			 while(result.next()) {
				 score=new DataScore();
				 score.setNumber(result.getString("number"));
				 score.setName(result.getString("name"));
				 score.setMajor(result.getString("major"));
				 score.setJava(Double.parseDouble(result.getString("java")));
				 score.setEnglish(Double.parseDouble(result.getString("English")));
				 score.setMath(Double.parseDouble(result.getString("math")));
				 score.setPhysical(Double.parseDouble(result.getString("physical")));
				 score.setRemark(result.getString("remark"));
				 scoreList.add(score);
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(state!=null) {
				try {
					state.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return scoreList;
	}
	
	public static int saveDataScore(DataScore score) {
		PreparedStatement prepare=null;
		String sql="insert into tb_datascore values(?,?,?,?,?,?,?,?,?)";
		int i=0;
		try {
			 prepare=SqlConnection.getConnection().prepareStatement(sql);
			 prepare.setObject(1,null);
			 prepare.setString(2,score.getNumber());
			 prepare.setString(3,score.getName());
			 prepare.setString(4,score.getMajor());
			 prepare.setString(5,String.valueOf(score.getJava()));
			 prepare.setString(6,String.valueOf(score.getEnglish()));
			 prepare.setString(7,String.valueOf(score.getMath()));
			 prepare.setString(8,String.valueOf(score.getPhysical()));
			 prepare.setString(9,score.getRemark());
			 i=prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	
	public static int updateDataScore(DataScore score) {
		PreparedStatement prepare=null;
		int i=0;
		String sql="update tb_datascore set number=?,name=?,major=?,java=?,English=?,math=?,physical=?,remark=? "
				+ "where number=?";
		try {
			prepare=SqlConnection.getConnection().prepareStatement(sql);
			 prepare.setString(1,score.getNumber());
			 prepare.setString(2,score.getName());
			 prepare.setString(3,score.getMajor());
			 prepare.setDouble(4,score.getJava());
			 prepare.setDouble(5,score.getEnglish());
			 prepare.setDouble(6,score.getMath());
			 prepare.setDouble(7,score.getPhysical());
			 prepare.setString(8,score.getRemark());
			 prepare.setString(9,score.getNumber());
			 i=prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}	
	
	public static int deleteDataScore(String number) {
		String sql="delete from tb_datascore where number="+"'"+number+"'";
		Statement state;
		int result=0;
		try {
			state=SqlConnection.getConnection().createStatement();
			result=state.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
