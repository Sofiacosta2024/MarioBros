package PowerUp;

import Colisionador.ColisionadorVisitorMario;
import Fabrica.Sprite;
import Mario.EstadoFlorFuego;
import Mario.Mario;
import Sonido.Sonido;

public class FlorFuego extends PowerUp {

	public FlorFuego(int x, int y, int velocidad) {
		super(x,y, velocidad,24,24);
	}

	public void afectar(Mario mario) {
		if(esColisionable) {
			Sonido.obtenerInstancia().reproducirSonidoFlorDeFuego();
			EstadoFlorFuego estadoNuevo = new EstadoFlorFuego();
			mario.getEstadoMario().aplicarEfecto(mario, estadoNuevo);
			mario.setPuntaje(mario.getPuntaje() + mario.getEstadoMario().puntajeFlorFuego());
			retirar();
		}
	}

	public void aceptarVisita(ColisionadorVisitorMario visitor) {
		if(esColisionable)
			visitor.visitarFlorFuego(this);
	}
}
