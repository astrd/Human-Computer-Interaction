package logic;

public class Ropa {
	private int code;
	private String name;
	private String description;
	private String brand;
	private double price;
	public Ropa(int code, String name, String description, String brand, double price) {
		this.code = code;
		this.name = name;
		this.description = description;
		this.brand = brand;
		this.price = price;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String toString()
	{
		return name + " by "+brand;
	}
}
