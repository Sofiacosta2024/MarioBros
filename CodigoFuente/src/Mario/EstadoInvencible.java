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
	
	public Sprite getSpriteDetenidoDerecha() {
		Sprite invencibleDetenido = new Sprite(rutaImagen +"/Mario-invencible.gif", rutaImagen);
		return invencibleDetenido;
	}
	
	public Sprite getSpriteDetenidoIzquierda() {
		Sprite invencibleDetenido = new Sprite(rutaImagen +"/Mario-invencible-aizq.gif", rutaImagen);
		return invencibleDetenido;
	}
											
	public Sprite getSpriteDerecha() {
		Sprite invencibleDetenido = new Sprite(rutaImagen +"/Invencible-caminandoderecha.gif", rutaImagen);
		return invencibleDetenido;
	}
	
	public Sprite getSpriteIzquierda() {
		Sprite invencibleDetenido = new Sprite(rutaImagen +"/Invencible-caminandoizquierda.gif", rutaImagen);
		return invencibleDetenido;
	}
	
	public Sprite getSpriteSaltandoADerecha() {
		Sprite invencibleDetenido = new Sprite(rutaImagen +"/Invencible-saltandoder.gif", rutaImagen);
		return invencibleDetenido;
	}
	
	public Sprite getSpriteSaltandoAIzquierda() {
		Sprite invencibleDetenido = new Sprite(rutaImagen +"/Invencible-saltandoizq.gif", rutaImagen);
		return invencibleDetenido;
	}
}
