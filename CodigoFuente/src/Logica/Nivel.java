package Logica;

import Enemigo.*;
import Plataforma.*;
import PowerUp.*;
import Mario.*;
import java.util.ArrayList;

public class Nivel{
	
	protected int nro;
	protected ArrayList<Enemigo> enemigos;
	protected ArrayList<PowerUp> powerUps;
	protected ArrayList<Plataforma> plataformas;
	protected ArrayList<BolaFuego> proyectiles;
	protected ArrayList<Spiny> spinyssLanzados;
	protected Mario mario;
	
	public Nivel(int nro) {
		this.nro = nro;
		enemigos = new ArrayList<Enemigo>();
		powerUps = new ArrayList<PowerUp>();
		plataformas = new ArrayList<Plataforma>();
		proyectiles = new ArrayList<BolaFuego>();
		spinyssLanzados = new ArrayList<Spiny>();
	}
	
	public void agregarEnemigo(Enemigo e) {
		enemigos.add(e);
	}
	
	public void agregarPlataforma(Plataforma p) {
		plataformas.add(p);
	}
	
	public void agregarPowerUp(PowerUp pu) {
		powerUps.add(pu);
	}
	
	public void agregarMario(Mario m) {
		this.mario = m;
	}
	
	public void agregarSpinyLanzado(Spiny spiny) {
		spinyssLanzados.add(spiny);
	}
	
	public void agregarProyectil(BolaFuego e) {
		proyectiles.add(e);
	}
	
	public ArrayList<Enemigo> getEnemigos(){
		return enemigos;
	}
	
	public ArrayList<Plataforma> getPlataformas(){
		return plataformas;
	}
	
	public ArrayList<PowerUp> getPowerUps(){
		return powerUps;
	}
	
	public ArrayList<BolaFuego> getProyectiles(){
		return proyectiles;
	}
	
	public ArrayList<Spiny> getSpinyssLanzados(){
		return spinyssLanzados;
	}
	
	public int getNivel() {
		return nro;
	}
	
	public Mario getMario() {
		return mario;
	}
}
