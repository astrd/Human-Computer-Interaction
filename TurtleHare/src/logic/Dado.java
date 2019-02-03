package logic;

public class Dado {
	
	private static int valor;
	
	public static void lanzar (int desplazamiento)
	{
		valor = ((int) (Math.random() * desplazamiento) + 1);
	}

	public static int getValor() {
		return valor;
	}

}
