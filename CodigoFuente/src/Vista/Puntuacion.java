package Vista;

public class Puntuacion {
	private String nombre;
	private int puntaje;
	
	public Puntuacion(String nombre, int puntaje) {
		this.nombre=nombre;
		this.puntaje=puntaje;
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
}
