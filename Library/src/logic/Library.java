package logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class Library {

	static List<Book> archive;
	
	public static List<Book> parseBooks()
	{
		archive = new ArrayList<Book>();
		
		String filename = "files/libros.dat";
		Book book;
		try {
		      BufferedReader fichero = new BufferedReader(new FileReader(filename));

		      //Mientras quede información
		      while (fichero.ready()) {
		    	String  user = fichero.readLine();
		        String[] trozos = user.split(":");
		        String id = trozos[0];
		        String title = trozos[1];
		        String editorial = trozos[2];
		        String author = trozos[3];
		        String genre = trozos[4];
		        String tagline = trozos[5];
		        Double price = Double.parseDouble(trozos[6]);
		        
		        
		        book = new Book(id, title,editorial, author, genre, tagline, price);
		        archive.add(book);
		        
		      }
		      fichero.close();
		      
		    }
		    catch (FileNotFoundException fnfe){
		        System.out.println("El archivo no se ha encontrado.");
		    }
		    catch(IOException ioe){
		        new RuntimeException("Error de entrada/salida");
		    }
		return archive;
	}

	public List<Book> getArchive() {
		return archive;
	}
	public static double getTotalPrice(List<Book> books)
	{
		double total =0.0;
		for(Book b:books)
		{
			total = total + b.getPrice();
		}
		return total;
	}

	public void setArchive(List<Book> archive) {
		this.archive = archive;
	}

	public static void generateTicket(User user, double d) {
		String filename = user.id;
		
		try ( 
				Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "utf-8"))) {			
				writer.write(filename+ " ");
				writer.write(user.getName()+" "+user.getSurname());
				writer.write(d+ " ");
				writer.close();
				
				
			}
			catch (IOException ex) {
			    // Report
			}
	}
}
