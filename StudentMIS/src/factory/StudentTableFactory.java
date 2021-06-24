package factory;

public class StudentTableFactory implements TableFactoryAbstract{

	@Override
	public UserTable createUserTable() {
		return new StudentTable();
	}
}
