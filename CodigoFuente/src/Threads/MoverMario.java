package Threads;

import Logica.Juego;
import Mario.*;

public class MoverMario extends Thread{

	private Mario mario;
	private Juego juego;
	private boolean corriendo;

	public MoverMario(Juego j,Mario mario){
		juego = j;
		corriendo = false;
		this.mario=mario;
	}

	public void iniciarHilo() {
		if(!corriendo) {
			corriendo = true;
			super.start();
		}
	}

	public void detenerHilo() {
		if(corriendo) {
			corriendo = false;
		}
	}

	public void run() {		
		while(corriendo) {	
			if(juego.juegoTerminado()) {
				detenerHilo();
				return;
			}
			if(juego.getTiempoActual()==0) {
				mario.decrementarVida();
				juego.reiniciarNivel();
			}
			if(mario.getPosicionX()>=4150) {
				mario.detenerMario();
				mario.setPuedeMoverseALaDerecha(false);
				mario.setPuedeMoverseALaIzquierda(false);
				juego.jugadorPasaDeNivel();
			}	
			juego.moverMario();
			synchronized(juego.getNivelActual()) {
				juego.detectarColisionesMarioMundo();
			}
			if(mario.getVidas()==0) {
				juego.finalizarPartida();
				detenerHilo();
				return;
			}
			try {
				Thread.sleep(40);
			} 
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}	
}