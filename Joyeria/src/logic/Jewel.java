package logic;

public class Jewel {

	String name;
	String des;
	String type;
	double price;
	public Jewel(String name, String des, String type, double price) {
		
		this.name = name;
		this.des = des;
		this.type = type;
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String toString()
	{
		return name+" "+ price;
	}
}
