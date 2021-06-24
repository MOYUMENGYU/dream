package bean;
/**
 * 大数据专业的成绩单
 * @author 魔宇
 *
 */
public class DataScore extends Score{
	private double java;
	private double English;
	private double math;
	private double physical;
	public double getJava() {
		return java;
	}
	public void setJava(double java) {
		this.java = java;
	}
	public double getEnglish() {
		return English;
	}
	public void setEnglish(double english) {
		English = english;
	}
	public double getMath() {
		return math;
	}
	public void setMath(double math) {
		this.math = math;
	}
	public double getPhysical() {
		return physical;
	}
	public void setPhysical(double physical) {
		this.physical = physical;
	}
}
