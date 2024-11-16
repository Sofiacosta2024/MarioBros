package PowerUp;

import Colisionador.ColisionadorVisitorMario;
import Fabrica.Sprite;
import Mario.EstadoSuperMario;
import Mario.Mario;
import Sonido.Sonido;

public class SuperChampinon extends PowerUp {
	protected boolean visible;

	public SuperChampinon(int x, int y, int velocidad) {
		super(x,y, velocidad,24,24);
	}

	public void afectar(Mario mario) {
		if(esColisionable) {
			Sonido.obtenerInstancia().reproducirSonidoSuperChampinon();
			EstadoSuperMario estadoNuevo = new EstadoSuperMario();
			mario.getEstadoMario().aplicarEfecto(mario, estadoNuevo);
			mario.setAlto(32);
			mario.setAncho(24);
			mario.actualizarAreaColision();
			mario.setPuntaje(mario.getPuntaje() + mario.getEstadoMario().puntajeSuperChampinon());
			retirar();
		}
	}

	public void aceptarVisita(ColisionadorVisitorMario visitor) {
		if(esColisionable)
			visitor.visitarSuperChampinon(this);
	}
}