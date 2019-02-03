package logic;

import java.util.ArrayList;
import java.util.List;

public class Ticket {
	public List<Cruise>  cruises;
	private double price;	
	
	
	public Ticket(){	
		this.cruises = new ArrayList<Cruise>();
		price =0;
	}

	public void addCruise(Cruise c)
	{
		cruises.add(c);
	}
	public List<Cruise> getCruises() {
		return cruises;
	}

	public void setCruises(List<Cruise> cruises) {
		this.cruises = cruises;
	}
	public void setprice(double p)
	{
		double pp = price;
		price = p+pp;
	}
	public double gettprice()
	{
		return price;
	}
	public double getPriceP() {
		
		double price =0;
		for(Cruise c:cruises)
		{
			price= price+ c.getPprice();
		}
		return price;
	}
	public double getPriceB() {
		
		double price =0;
		for(Cruise c:cruises)
		{
			price= price+ c.getBpriceplus();
		}
		return price;
	}
	public String ToString()
	{
		StringBuilder sb = new StringBuilder();
		for(Cruise c:cruises)
		{
			sb.append(c.getName());
			sb.append("\n");
		}
		return sb.toString();
	}
	
	

}
