package Vista;

public class Puntuacion {
	private String nombre;
	private int puntaje;
	
	public Puntuacion() {
		puntaje=0;
	}
	public String getNombre() {
		return nombre;
	}
	public int getPuntaje() {
		return puntaje;
	}
	public void setPuntaje(int puntaje) {
		this.puntaje=puntaje;
	}
	public void setNombre(String nombre) {
		this.nombre=nombre;
	}
}
