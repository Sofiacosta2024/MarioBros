package Mario;

import Enemigo.Enemigo;
import Fabrica.Sprite;

public class EstadoInvencible extends EstadoMario {
	
	protected String rutaImagen;

	public EstadoInvencible(String rutaImagen) {
		this.rutaImagen = rutaImagen;
		this.lanzarBola = false;		
	}
	
	public boolean lanzarBolaFuego() {
		return lanzarBola;
	}
	
	public void recibirDaño(Mario mario, Enemigo enemigo) {
		enemigo.retirar();	
	}
	
	public void aplicarEfecto(Mario mario, EstadoInvencible estadoNuevo) {
		mario.setEstadoAnterior(mario.getEstadoMario());
		mario.setEstadoMario(new EstadoInvencible(rutaImagen));
		mario.setCambioEstado(true);
	}
	
	public void aplicarEfecto(Mario mario, EstadoFlorFuego estadoNuevo) {
		
	}
	
	public void aplicarEfecto(Mario mario, EstadoNormal estadoNuevo) {
		
	}
	
	public void aplicarEfecto(Mario mario, EstadoSuperMario estadoNuevo) {
		
	}
	

	public int puntajeEstrella() {
		return 35;
	}
	
	public int puntajeFlorFuego() {
		return 5;
	}
	
	public int puntajeSuperChampinon() {
		return 10;
	}
	
	public Sprite getSpriteDetenidoDerecha(Sprite[] sprites) {
		return sprites[12];
	}
	
	public Sprite getSpriteDetenidoIzquierda(Sprite[] sprites) {
		return sprites[13];
	}
											
	public Sprite getSpriteDerecha(Sprite[] sprites) {
		return sprites[14];
	}
	
	public Sprite getSpriteIzquierda(Sprite[] sprites) {
		return sprites[15];
	}
	
	public Sprite getSpriteSaltandoADerecha(Sprite[] sprites) {
		return sprites[16];
	}
	
	public Sprite getSpriteSaltandoAIzquierda(Sprite[] sprites) {
		return sprites[17];
	}
}
