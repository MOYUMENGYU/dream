package factory;
/**
 * 学生接口：用来区分不同专业
 */
import javax.swing.JPanel;

public interface StudentPerson {
	public JPanel studentScore(String name);
	public JPanel studenCourse();
}
