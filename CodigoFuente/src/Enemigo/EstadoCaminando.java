package Enemigo;

import Fabrica.Sprite;

public class EstadoCaminando extends EstadoKoopaTroopa{
	
	public EstadoCaminando(KoopaTroopa koopaTroopa) {
		this.koopaTroopa = koopaTroopa;
	}

	public void afectado(KoopaTroopa koopaTroopa, Sprite[] spritesKoopa) {
		koopaTroopa.setSprite(spritesKoopa[1]);
		koopaTroopa.movilidad();
		koopaTroopa.setEstado(new EstadoProyectil(koopaTroopa));
		koopaTroopa.notificarObservador();
	}
	
	public void mover(int velocidad) {
		if(koopaTroopa.getMovimiento() == koopaTroopa.IZQUIERDA) {
			koopaTroopa.setPosicionX(koopaTroopa.getPosicionX() - velocidad);
		}
		else
			if(koopaTroopa.getMovimiento() == koopaTroopa.DERECHA) {
				koopaTroopa.setPosicionX(koopaTroopa.getPosicionX() + velocidad);
			}
	}
	
	public void interactuarEnemigos(Enemigo enemigo) {
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