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

	public void mover(int nuevaX, int nuevaY) {
		koopaTroopa.setPosicionX(koopaTroopa.getPosicionX() + nuevaX);
		koopaTroopa.setPosicionY(koopaTroopa.getPosicionY() + nuevaY);
		koopaTroopa.actualizarAreaColision();
        koopaTroopa.getObserver().actualizarPosicionTamano();
	}
	
	public void interactuarEnemigos(Enemigo enemigo) {
		if(enemigo.getMovimiento().equals("IZQUIERDA")) {
			enemigo.mover(5, 0);
			enemigo.setMovimiento("DERECHA");
		}
		else {
			enemigo.mover(-5, 0);
			enemigo.setMovimiento("IZQUIERDA");
		}
	}
}