package Enemigo;

import javax.swing.Timer;
import Colisionador.ColisionadorVisitorEnemigo;
import Colisionador.ColisionadorVisitorMario;
import Mario.Mario;

public class PiranhaPlant extends Enemigo{

	protected boolean visible;
	protected int posicionInicialY;
	protected int limiteSuperiorY;
	protected Timer temporizador;

	public PiranhaPlant(int x, int y, int velocidad) {
		super(x,y, velocidad,16,32);
		puntaje = 30;
		puntajeMatarAMario = -30;
		posicionInicialY = y-15;
		limiteSuperiorY = y + getAlto();
		direccionMovimiento = DERECHA;
		temporizador = new Timer(4000, e -> alternarVisibilidad());
		temporizador.start();
	}

	public void atacar(Mario mario) {
		if(!muerto) {
			mario.decrementarVida();
		}
	}

	public void aceptarVisita(ColisionadorVisitorMario visitor) {
		if(!muerto) {
			visitor.visitarPiranha(this);
		}
	}

	public void aceptarVisita(ColisionadorVisitorEnemigo visitor) {
		if(!muerto) {
			visitor.visitarPiranhaPlant(this);
		}
	}

	private void alternarVisibilidad() {
		visible = !visible;
	}

	public void mover() {
		if (visible && posicionY < limiteSuperiorY) {
			setPosicionY(posicionY + velocidad); 
			actualizarAreaColision();
			observador.actualizarPosicionTamano();
		} else
			if (!visible && posicionY > posicionInicialY) {
				setPosicionY((posicionY - 10) - velocidad); 
				actualizarAreaColision();
				observador.actualizarPosicionTamano();
		}
	}

	public void detener() {
		temporizador.stop();
	}

}
