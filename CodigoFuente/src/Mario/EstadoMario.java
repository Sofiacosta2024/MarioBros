package Mario;

import Enemigo.Enemigo;
import Fabrica.*;
import Plataforma.LadrilloSolido;

public abstract class EstadoMario {
	
	protected boolean lanzarBola;
	protected String rutaImagen;
	
	public void aplicarEfecto(Mario mario, EstadoInvencible estadoNuevo) {
		mario.setEstadoAnterior(mario.getEstadoMario());
		mario.setEstadoMario(new EstadoInvencible(rutaImagen));
		mario.setCambioEstado(true);
	}
	
	public void aplicarEfecto(Mario mario, EstadoFlorFuego estadoNuevo) {
		mario.setEstadoMario(estadoNuevo);
		mario.setCambioEstado(true);
	}
	
	public void aplicarEfecto(Mario mario, EstadoNormal estadoNuevo) {
		mario.setEstadoAnterior(mario.getEstadoMario());
		mario.setEstadoMario(new EstadoInvencible(rutaImagen));
		mario.setCambioEstado(true);
	}
	
	public void aplicarEfecto(Mario mario, EstadoSuperMario estadoNuevo) {
		mario.setEstadoMario(estadoNuevo);
		mario.setCambioEstado(true);
	}
	
	public void interactuarConLadrillo(LadrilloSolido ladrilloSolido, String direccionColision) {

	}
	
	public abstract boolean lanzarBolaFuego();
	public abstract void recibirDaño(Mario mario, Enemigo enemigo);
	public abstract int puntajeEstrella();
	public abstract int puntajeFlorFuego();
	public abstract int puntajeSuperChampinon();
	public abstract Sprite getSpriteDetenidoDerecha();
	public abstract Sprite getSpriteDerecha();
	public abstract Sprite getSpriteIzquierda();
	public abstract Sprite getSpriteSaltandoADerecha();
	public abstract Sprite getSpriteSaltandoAIzquierda();
	public abstract Sprite getSpriteDetenidoIzquierda();
}
