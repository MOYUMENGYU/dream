package factory;
/**
 * 生产大数据成绩单的具体工厂类
 * @author 魔宇
 *
 */
public class DataScoreTableFactory implements TableFactoryAbstract{

	@Override
	public UserTable createUserTable() {
		return new DataScoreTable();
	}
	
}
