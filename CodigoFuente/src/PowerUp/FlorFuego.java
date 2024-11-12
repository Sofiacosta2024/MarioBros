package PowerUp;

import Colisionador.ColisionadorVisitorMario;
import Fabrica.Sprite;
import Mario.EstadoFlorFuego;
import Mario.Mario;

public class FlorFuego extends PowerUp {
	
	public FlorFuego(int x, int y, int velocidad) {
		super(x,y, velocidad,16,16);
	}
	
	public void afectar(Mario mario) {
		EstadoFlorFuego estadoNuevo = new EstadoFlorFuego();
		mario.getEstadoMario().aplicarEfecto(mario, estadoNuevo);
		mario.setAlto(32);
		mario.setAncho(24);
		mario.actualizarAreaColision();
	}

	public void aceptarVisita(ColisionadorVisitorMario visitor) {
		visitor.visitarFlorFuego(this);
	}
}
