package PowerUp;

import Colisionador.ColisionadorVisitorMario;
import Fabrica.Sprite;
import Mario.EstadoInvencible;
import Mario.Mario;

public class Estrella extends PowerUp {
	
	public Estrella(int x, int y, int velocidad) {
		super(x,y, velocidad,22,30);
		ancho = 14;
		alto = 16;
	}
	
	public void afectar(Mario mario) {
		String rutaImagen = mario.getRutaImagen();
		EstadoInvencible estadoNuevo = new EstadoInvencible(rutaImagen);
		mario.getEstadoMario().aplicarEfecto(mario, estadoNuevo);
		mario.setAlto(32);
		mario.setAncho(24);
		mario.actualizarAreaColision();
	}

	public void aceptarVisita(ColisionadorVisitorMario visitor) {
		visitor.visitarEstrella(this);
	}
}
