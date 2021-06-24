package factory;
/**
 * 课程信息表的具体工厂类
 * @author 魔宇
 *
 */
public class CourseTableFactory implements TableFactoryAbstract{

	@Override
	public UserTable createUserTable() {
		return new CourseTable();
	}
	
}
