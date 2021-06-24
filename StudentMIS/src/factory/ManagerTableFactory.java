package factory;
/**
 * 管理员表的具体工厂
 * @author 魔宇
 *
 */
public class ManagerTableFactory implements TableFactoryAbstract{
	@Override
	public UserTable createUserTable() {
		//创建一个管理员表的具体对象
		return new ManagerTable();
	}

}
