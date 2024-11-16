package Threads;

import Logica.Juego;

public class MoverMario extends Thread{

	private Juego juego;
	private boolean corriendo;

	public MoverMario(Juego j){
		juego = j;
		corriendo = false;
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
			juego.moverMario();
			synchronized(juego.getNivelActual()) {
				juego.detectarColisionesMarioMundo();
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