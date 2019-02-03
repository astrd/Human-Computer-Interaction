package logic;

public class Cruise {

	private String code;
	private String continent;
	private String name;
	private String itinerary;
	private String description;
	private int days;
	private int pprice;
	private int bpriceplus;
	
	public Cruise(String code, String continent,String name,String itinerary,String description,int days,int pprice,int bpriceplus)
	{
		this.code=code;
		this.continent = continent;
		this.name=name;
		this.itinerary = itinerary;
		this.description = description;
		this.days = days;
		this.pprice=pprice;
		this.bpriceplus= bpriceplus;		
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public String getItinerary() {
		return itinerary;
	}

	public void setItinerary(String itinerary) {
		this.itinerary = itinerary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public int getPprice() {
		return pprice;
	}

	public void setPprice(int pprice) {
		this.pprice = pprice;
	}

	public int getBpriceplus() {
		return bpriceplus+pprice;
	}

	public void setBpriceplus(int bpriceplus) {
		this.bpriceplus = bpriceplus;
	}
	public String toString()
	{
		return getCode() +" "+getContinent()+" "+getName()+" "+getPprice()+"€";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
