package dao;
/**
 * 教材类
 * @author 魔宇
 *
 */

public class Textbook {
	private String number;
	private String name;
	private String type;
	private String author;
	private String company;
	private String prive;
	private long stock;
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getPrive() {
		return prive;
	}
	public void setPrive(String prive) {
		this.prive = prive;
	}
	public long getStock() {
		return stock;
	}
	public void setStock(long stock) {
		this.stock = stock;
	}
}
