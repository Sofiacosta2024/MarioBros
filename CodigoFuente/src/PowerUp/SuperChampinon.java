package PowerUp;

import Colisionador.ColisionadorVisitorMario;
import Fabrica.Sprite;
import Mario.EstadoSuperMario;
import Mario.Mario;

public class SuperChampinon extends PowerUp {
	protected boolean visible;
	
	public SuperChampinon(int x, int y, int velocidad) {
		super(x,y, velocidad,16,16);
	}
	
	public void afectar(Mario mario) {
		EstadoSuperMario estadoNuevo = new EstadoSuperMario();
		mario.getEstadoMario().aplicarEfecto(mario, estadoNuevo);
		mario.setAlto(32);
		mario.setAncho(24);
		mario.actualizarAreaColision();
	}

	public void aceptarVisita(ColisionadorVisitorMario visitor) {
		visitor.visitarSuperChampinon(this);
	}
}