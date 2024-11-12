package Logica;

import Mario.*;

public class Jugador{
	
	protected String nombre;
	protected int puntaje;
	protected Mario personaje;
	
	public Jugador(String nombre,int puntaje) {
		this.nombre =nombre;
		this.puntaje=puntaje;
	}
	
	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
		if(this.puntaje < 0)
			this.puntaje = 0;
	}
	
	public void setPersonaje(Mario m) {
		personaje = m;
	}
	
	public void setNombre(String nombre) {
		this.nombre=nombre;
	}
	
	public int getPuntaje() {
		return puntaje;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public Mario getPersonaje() {
		return personaje;
	}
}
