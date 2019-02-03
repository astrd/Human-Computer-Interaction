package logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class Tienda {
private static List<Ropa> ropas;

public static List<Ropa> getRopas() {
	parseRopa();
	return ropas;
}

public static void setRopas(List<Ropa> ropas) {
	
	Tienda.ropas = ropas;
}

public static List<Ropa> parseRopa()
{
	ropas = new ArrayList<Ropa>();
	String filename = "files/ropa.txt";
	try {
	      BufferedReader fichero = new BufferedReader(new FileReader(filename));

	      //Mientras quede información
	      while (fichero.ready()) {
	    	String  user = fichero.readLine();
	        String[] trozos = user.split(";");
	        int id = Integer.parseInt(trozos[0]);
	        String name = trozos[1];
	        String des = trozos[2];
	        String brand = trozos[3];
	        Double price = Double.parseDouble(trozos[4]);
	        Ropa ropa = new Ropa(id,name,des,brand,price);
	        ropas.add(ropa);
	        
	      }
	      fichero.close();
	    }
	    catch (FileNotFoundException fnfe){
	        System.out.println("El archivo no se ha encontrado.");
	    }
	    catch(IOException ioe){
	        new RuntimeException("Error de entrada/salida");
	    }
	return ropas;
}
public static double  getPrice(List<Ropa> listr)
{
	double price = 0;
	for(Ropa r: listr)
	{
		price = price + r.getPrice();
	}
	return price;
}

public static void writeticket(String filename, double price, String name, String surname) throws IOException {
	
		Writer writer;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "utf-8"));
			
			writer.write(price+"e " );
			writer.write(name+ " "+surname);
			writer.close();
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		
	
}
}
