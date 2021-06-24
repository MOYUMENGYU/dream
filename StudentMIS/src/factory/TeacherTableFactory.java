package factory;

public class TeacherTableFactory implements TableFactoryAbstract{
	@Override
	public UserTable createUserTable() {
		return new TeacherTable();
	}
	

}
