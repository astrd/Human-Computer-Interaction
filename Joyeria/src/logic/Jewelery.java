package logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Jewelery {
static List<Jewel> catalog ;

public static List<Jewel> parse()
{
	catalog = new ArrayList<Jewel>();
	String filename = "files/jewel.txt";
	try {
	      BufferedReader fichero = new BufferedReader(new FileReader(filename));

	      //Mientras quede información
	      while (fichero.ready()) {
	    	String  user = fichero.readLine();
	        String[] trozos = user.split(";");	        
	        String name = trozos[0];
	        String des = trozos[1];
	        String brand = trozos[2];
	        Double price = Double.parseDouble(trozos[3]);
	        Jewel ropa = new Jewel(name,des,brand,price);
	        catalog.add(ropa);
	        
	      }
	      fichero.close();
	    }
	    catch (FileNotFoundException fnfe){
	        System.out.println("El archivo no se ha encontrado.");
	    }
	    catch(IOException ioe){
	        new RuntimeException("Error de entrada/salida");
	    }
	return catalog;
}
public static double getTotalPrice(List<Jewel> jlist)
{
	double total =0.0;
	for(Jewel j : jlist)
	{
		total = total + j.getPrice();
	}
	return total;
}

}
