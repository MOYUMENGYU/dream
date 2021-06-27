package dao;

import java.util.ArrayList;

/**
 * 成绩单
 * @author 魔宇
 *
 */

public class Report implements Comparable<Report>{
	String name;
	String number;
	ArrayList<Course> courseScore;
	String sum;
	String remark;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public ArrayList<Course> getCourseScore() {
		return courseScore;
	}
	public void setCourseScore(ArrayList<Course> courseScore) {
		this.courseScore = courseScore;
	}
	public String getSum() {
		return sum;
	}
	public void setSum(String sum) {
		this.sum = sum;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public int compareTo(Report o) {
		int i=(int)(Double.parseDouble(sum)-Double.parseDouble(o.getSum()));
		if(i>0) {
			return -1;
		}else{
			return 1;
		}
	}
}
