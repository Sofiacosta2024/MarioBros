package PowerUp;

import Colisionador.ColisionadorVisitorMario;
import Mario.EstadoInvencible;
import Mario.Mario;
import Sonido.Sonido;

public class Estrella extends PowerUp {

	public Estrella(int x, int y, int velocidad) {
		super(x,y, velocidad,22,30);
		ancho = 14;
		alto = 16;
	}

	public void afectar(Mario mario) {
		if(esColisionable) {
			Sonido.obtenerInstancia().reproducirSonidoPowerUp();
			EstadoInvencible estadoNuevo = new EstadoInvencible();
			mario.getEstadoMario().aplicarEfecto(mario, estadoNuevo);
			mario.setAlto(32);
			mario.setAncho(24);
			mario.actualizarAreaColision();
			mario.setPuntaje(mario.getPuntaje() + mario.getEstadoMario().puntajeEstrella());
			mario.iniciarRegresionEstadoEstrella();
		}
	}

	public void aceptarVisita(ColisionadorVisitorMario visitor) {
		visitor.visitarEstrella(this);
	}
}
