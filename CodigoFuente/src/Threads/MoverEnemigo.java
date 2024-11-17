package Threads;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import Enemigo.Enemigo;
import Enemigo.Spiny;
import Logica.*;
import Mario.BolaFuego;

public class MoverEnemigo extends Thread{

	private boolean corriendo = false;
	private Juego juego;
	private final int FPS = 60; 
	private final double tiempoEntreFrames = 1000.0/FPS;

	public MoverEnemigo(Juego juego) {
		this.juego=juego;
	}

	public void iniciarHilo() {
		if(!corriendo) {
			corriendo = true;
			start();
		}
	}

	public void detenerHilo() {
		if(corriendo) {
			corriendo = false;
		}
	}

	public void run() {
		long ultimaEjecucion = System.currentTimeMillis();
		long tiempoActual = 0; 
		float deltat = 0;
		while(corriendo) {
			tiempoActual = System.currentTimeMillis(); 
			deltat +=  (tiempoActual - ultimaEjecucion)/tiempoEntreFrames;  
			ultimaEjecucion = tiempoActual;
			
			if(deltat >= 1) { 
				actualizarMovimiento();
				deltat--;
			}		
			synchronized(juego.getNivelActual()) {
				juego.detectarColisionesMundoSinMario();  
			}
			try {
				Thread.sleep((long) tiempoEntreFrames / 2);  
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void actualizarMovimiento() {
		try {
			for (Enemigo enemigo : juego.getNivelActual().getEnemigos()) {
				enemigo.mover();
			}
			ArrayList<Spiny> spinyss = new ArrayList<>(juego.getNivelActual().getSpinyssLanzados());
			for (Spiny spiny : spinyss) {
				spiny.mover();
			}
			for (BolaFuego bolaFuego : juego.getNivelActual().getProyectiles()) {
				bolaFuego.mover();
			}
		}
		catch(ConcurrentModificationException excepcion) {
			actualizarMovimiento();
		}
	}
}