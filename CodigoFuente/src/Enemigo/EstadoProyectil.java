package Enemigo;

import Fabrica.Sprite;

public class EstadoProyectil extends EstadoKoopaTroopa{
	
	public EstadoProyectil(KoopaTroopa koopaTroopa) {
		this.koopaTroopa = koopaTroopa;
	}
	
	public void mover(int velocidad) {
		koopaTroopa.movilidad();
		if(koopaTroopa.getMovimiento() == koopaTroopa.DERECHA) {
			koopaTroopa.setPosicionX(koopaTroopa.getPosicionX() + velocidad*3);
		}
		else
			if(koopaTroopa.getMovimiento() == koopaTroopa.IZQUIERDA) {
				koopaTroopa.setPosicionX(koopaTroopa.getPosicionX() - velocidad*3);
			}
		
		koopaTroopa.actualizarAreaColision();
        koopaTroopa.getObserver().actualizarPosicionTamano();
    }

	public void afectado(KoopaTroopa koopaTroopa, Sprite[] spritesKoopa) {
		koopaTroopa.retirar();
		koopaTroopa.notificarObservador();
	}
	
	public void interactuarEnemigos(Enemigo enemigo) {
		if(!koopaTroopa.getMovilidad()) {
			enemigo.retirar();
			enemigo.notificarObservador();
		}
		else {
			if(enemigo.getMovimiento() == enemigo.IZQUIERDA) {
				enemigo.setMovimiento(enemigo.DERECHA);
				enemigo.mover();
			}
			else {
				  enemigo.setMovimiento(enemigo.IZQUIERDA);
				  enemigo.mover();
				  }
		    }
	 }
}
