package Enemigo;

import javax.swing.Timer;

import Colisionador.ColisionadorVisitorEnemigo;
import Colisionador.ColisionadorVisitorMario;
import Fabrica.Sprite;
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
		movimiento = "DERECHA";
		temporizador = new Timer(4000, e -> alternarVisibilidad());
		temporizador.start();
	}

	public void atacar(Mario mario) {
		mario.decrementarVida();
	}

	public void aceptarVisita(ColisionadorVisitorMario visitor) {
		visitor.visitarPiranha(this);
	}

	public void aceptarVisita(ColisionadorVisitorEnemigo visitor) {
		visitor.visitarPiranhaPlant(this);
	}

	private void alternarVisibilidad() {
		visible = !visible;
	}

	public void mover(int desplazamiento, int desplazamientoNulo) {
		if (visible && posicionY < limiteSuperiorY) {
			setPosicionY(posicionY + desplazamiento); // Subir
			actualizarAreaColision();
			observador.actualizarPosicionTamano();
		} else
			if (!visible && posicionY > posicionInicialY) {
				setPosicionY((posicionY - 10) - desplazamiento); // Bajar
				actualizarAreaColision();
				observador.actualizarPosicionTamano();
			}
	}

	public void detener() {
		temporizador.stop();
	}

}
