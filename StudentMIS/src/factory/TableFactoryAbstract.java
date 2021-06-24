package factory;
/**
 * 工厂接口
 * @author 魔宇
 *
 */
public interface TableFactoryAbstract {
	//返回一个操作表类型
	public UserTable createUserTable();
	
}
