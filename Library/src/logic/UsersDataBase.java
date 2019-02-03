package logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UsersDataBase {
	static List<User> clients = new ArrayList<User>();
	public static User isValid(String name,String surname,String password)
	{
		parseUsers();
		for(User c : clients)
		{
			if(c.getName().equals(name) && c.getSurname().equals(surname) && c.getPassword().equals(password))
			{
				return c;
			}
		}
		return null;
	}
	public static void parseUsers()
	{
		String filename = "files/clientes.dat";
		User client;
		try {
		      BufferedReader fichero = new BufferedReader(new FileReader(filename));

		      //Mientras quede información
		      while (fichero.ready()) {
		    	String  user = fichero.readLine();
		        String[] trozos = user.split(":");
		        String id = trozos[0];
		        String name = trozos[1];
		        String surname = trozos[2];
		        String pass = trozos[3];
		        client = new User(id,name,surname,pass);
		        clients.add(client);
		        
		      }
		      fichero.close();
		    }
		    catch (FileNotFoundException fnfe){
		        System.out.println("El archivo no se ha encontrado.");
		    }
		    catch(IOException ioe){
		        new RuntimeException("Error de entrada/salida");
		    }
	}

}
