package Threads;

import java.util.ArrayList;
import Enemigo.Enemigo;
import Enemigo.Spiny;
import Logica.*;

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
			if(juego.juegoTerminado()) {
				detenerHilo();
				return;
			}
			tiempoActual = System.currentTimeMillis(); 
			deltat +=  (tiempoActual - ultimaEjecucion)/tiempoEntreFrames;  
			ultimaEjecucion = tiempoActual;
			if(deltat >= 1) { 
				actualizar();
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

	private void actualizar() {
		for (Enemigo enemigo : juego.getNivelActual().getEnemigos()) {
			if (!enemigo.estaEnEnfriamiento() && enemigo.colisionable()) {
				String direccionEnemigo = enemigo.getMovimiento();
				if(direccionEnemigo.equals("IZQUIERDA"))
					enemigo.mover(-1, 0);
				else
					enemigo.mover(1, 0);  
			}
		}
		ArrayList<Spiny> spinyss = new ArrayList<>(juego.getNivelActual().getSpinyssLanzados());
		for (Spiny spiny : spinyss) {
			if (!spiny.estaEnEnfriamiento() && spiny.colisionable()) {
				String direccionEnemigo = spiny.getMovimiento();
				if(direccionEnemigo.equals("IZQUIERDA"))
					spiny.mover(-1, 0);
				else
					spiny.mover(1, 0);  
			}
		}
	}
}