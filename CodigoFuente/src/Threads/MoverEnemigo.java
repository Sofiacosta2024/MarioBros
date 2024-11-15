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
		try {
			for (Enemigo enemigo : juego.getNivelActual().getEnemigos()) {
				if (!enemigo.estaEnEnfriamiento() && enemigo.colisionable()) {
					//enemigo.mover();
					char direccionEnemigo = enemigo.getMovimiento();
					if(direccionEnemigo == enemigo.IZQUIERDA)
						enemigo.mover(-1, 0);
					else
						enemigo.mover(1, 0);  
				}
			}
			ArrayList<Spiny> spinyss = new ArrayList<>(juego.getNivelActual().getSpinyssLanzados());
			for (Spiny spiny : spinyss) {
				if (!spiny.estaEnEnfriamiento() && spiny.colisionable()) {
					char direccionEnemigo = spiny.getMovimiento();
					if(direccionEnemigo == spiny.IZQUIERDA)
						spiny.mover(-2, 0);
					else
						spiny.mover(2, 0);  
				}
			}
			for (BolaFuego bolaFuego : juego.getNivelActual().getProyectiles()) {
				if(bolaFuego.enMovimiento()) {
					char direccion = bolaFuego.getDireccionMovimiento();
					char direccionAnterior = bolaFuego.getDireccionMovimientoAnterior();
					if (direccion == bolaFuego.DERECHA || (direccion == bolaFuego.QUIETO && direccionAnterior == bolaFuego.DERECHA)) {
						bolaFuego.mover(1, 0);
					}
					else { 
						bolaFuego.mover(-1, 0);
					}
				}
			}
		}
		catch(ConcurrentModificationException excepcion) {
			//System.out.println("CONCURRENNNTTT EN MOVERENEMIGO");
			actualizar();
		}
	}
}