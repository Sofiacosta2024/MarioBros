package Mario;

import Enemigo.Enemigo;
import Fabrica.*;
import Plataforma.LadrilloSolido;

public abstract class EstadoMario {
	
	protected boolean lanzarBola;
	protected String rutaImagen;
	
	public void aplicarEfecto(Mario mario, EstadoInvencible estadoNuevo) {
		mario.setEstadoAnterior(mario.getEstadoMario());
		mario.setEstadoMario(new EstadoInvencible());
		mario.setCambioEstado(true);
	}
	
	public void aplicarEfecto(Mario mario, EstadoFlorFuego estadoNuevo) {
		mario.setEstadoMario(estadoNuevo);
		mario.setCambioEstado(true);
	}
	
	public void aplicarEfecto(Mario mario, EstadoNormal estadoNuevo) {
		mario.setEstadoAnterior(mario.getEstadoMario());
		mario.setEstadoMario(new EstadoInvencible());
		mario.setCambioEstado(true);
	}
	
	public void aplicarEfecto(Mario mario, EstadoSuperMario estadoNuevo) {
		mario.setEstadoMario(estadoNuevo);
		mario.setCambioEstado(true);
	}
	
	public void actualizarSpriteMario(Mario mario) {
		if(mario.getDireccionMovimiento() == mario.DERECHA) {
			mario.setSprite(mario.getEstadoMario().getSpriteDetenidoDerecha(mario.getSpritesMario()));
		}
		if(mario.getDireccionMovimiento() == mario.IZQUIERDA) {
			mario.setSprite(mario.getEstadoMario().getSpriteDetenidoIzquierda(mario.getSpritesMario()));
		}
	}
	
	public void interactuarConLadrillo(LadrilloSolido ladrilloSolido, String direccionColision) {

	}
	
	public abstract boolean lanzarBolaFuego();
	public abstract void recibirDaño(Mario mario, Enemigo enemigo);
	public abstract int puntajeEstrella();
	public abstract int puntajeFlorFuego();
	public abstract int puntajeSuperChampinon();
	public abstract Sprite getSpriteDetenidoDerecha(Sprite[] sprites);
	public abstract Sprite getSpriteDerecha(Sprite[] sprites);
	public abstract Sprite getSpriteIzquierda(Sprite[] sprites);
	public abstract Sprite getSpriteSaltandoADerecha(Sprite[] sprites);
	public abstract Sprite getSpriteSaltandoAIzquierda(Sprite[] sprites);
	public abstract Sprite getSpriteDetenidoIzquierda(Sprite[] sprites);
}
