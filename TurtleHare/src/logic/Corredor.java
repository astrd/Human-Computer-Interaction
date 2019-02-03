package logic;

public class Corredor {
	private int puntuacion;
	private int posicion;
	private boolean turno;
	private int desplazamiento;
	private String nombre;
	private String foto;
	private Calle calleAsignada;
	public final static int POSICION_SALIDA = 0; 
	
	public Corredor(Calle calleAsignada, String nombre, String foto, int desplazamiento, boolean turno) {
		this.calleAsignada = calleAsignada;
		this.nombre = nombre;
		this.foto = foto;
		this.desplazamiento = desplazamiento; 
		this.turno = turno;
		puntuacion = 0;
		posicion = POSICION_SALIDA;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public int getDesplazamiento() {
		return desplazamiento;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion (int posicion) {
		this.posicion = posicion;
	}
	
	public boolean getTurno() {
		return turno;
	}
	
	public void cambiaTurno() {
		turno = !turno;
	}
	
	public Calle getCalleAsignada() {
		return calleAsignada;
	}

	public String getFoto() {
		return foto;
	}
	
	public void incrementaPuntuacion (int puntos){
		puntuacion += puntos;
	}
}
