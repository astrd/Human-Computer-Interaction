package logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Catalog 
{
	List<Cruise> cruises ;
	public Catalog()
	{
		cruises = new ArrayList<Cruise>();
		leerFichero();
	}
	
	public List<Cruise> getCruises() {
		return cruises;
	}

	public void setCruises(List<Cruise> cruises) {
		this.cruises = cruises;
	}

	public void leerFichero() {
	    String nombreFichero = "files/cruceros.dat";
	    String linea="";
	    try {
	      BufferedReader fichero = new BufferedReader(new FileReader(nombreFichero));

	      //Mientras quede información
	      while (fichero.ready()) {
	        linea = fichero.readLine();
	        String[] trozos = linea.split(";");	        
	        String code = trozos[0];
	        String cont = trozos[1];
	        String name = trozos[2];
	        String it = trozos[3];
	        String des = trozos[4];
	        int days = Integer.parseInt(trozos[5]);
	        int pp = Integer.parseInt(trozos[6]);
	        int bpp = Integer.parseInt(trozos[7]);
	        
	        Cruise c = new Cruise(code,cont,name,it,des,days,pp,bpp);
	        cruises.add(c);
	        
	      }
	      fichero.close();
	    }
	    catch (FileNotFoundException fnfe){
	        System.out.println("El archivo no se ha encontrado.");
	    }
	    catch(IOException ioe){
	        new RuntimeException("Error de entrada/salida.");
	    }
	 }
	
}
