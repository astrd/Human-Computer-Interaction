package logic;

public class Book {
	 public String id;
	 public String title;
	 public String editorial;
	 public String author;
	 public String genre;
	 public String tagline;
	 public double price;
	public Book(String id, String title,String editorial, String author, String genre, String tagline, double price) {
		super();
		this.id = id;
		this.title = title;
		this.editorial= editorial;
		this.author = author;
		this.genre = genre;
		this.tagline = tagline;
		this.price = price;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getTagline() {
		return tagline;
	}
	public void setTagline(String tagline) {
		this.tagline = tagline;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String toString()
	{
		return title + " by "+ author;
	}
}
