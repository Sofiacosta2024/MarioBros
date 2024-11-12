package Enemigo;

public class EstadoProyectil extends EstadoKoopaTroopa{
	
	public EstadoProyectil(KoopaTroopa koopaTroopa) {
		this.koopaTroopa = koopaTroopa;
	}
	
	public void mover(int nuevaX, int nuevaY) {
		koopaTroopa.movilidad();
		koopaTroopa.setPosicionX(koopaTroopa.getPosicionX() + nuevaX*3);
		koopaTroopa.setPosicionY(koopaTroopa.getPosicionY() + nuevaY);
		koopaTroopa.actualizarAreaColision();
        koopaTroopa.getObserver().actualizarPosicionTamano();
	    }

	public void afectado(KoopaTroopa koopaTroopa) {
		koopaTroopa.retirar();
		koopaTroopa.notificarObservador();
	}
	
	public void interactuarEnemigos(Enemigo enemigo) {
		if(!koopaTroopa.getMovilidad()) {
			enemigo.retirar();
			enemigo.notificarObservador();
		}
		else {
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

}
