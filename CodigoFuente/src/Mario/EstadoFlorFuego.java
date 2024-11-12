package Mario;

import Fabrica.Sprite;

public class EstadoFlorFuego extends EstadoSuperMario{

	public EstadoFlorFuego(String rutaImagen) {
		super(rutaImagen);
		lanzarBola = true;
	}
	
	public boolean lanzarBolaFuego() {
		return lanzarBola;
	}
	
	public int puntajeFlorFuego() {
		return 50;
	}
	
	public Sprite getSpriteDetenidoDerecha() {
		Sprite florDetenido = new Sprite(rutaImagen +"/Marioflor-quieto.png", rutaImagen);
		return florDetenido;
	}
	
	public Sprite getSpriteDetenidoIzquierda() {
		Sprite florDetenido = new Sprite(rutaImagen +"/Marioflor-quietoizq.png", rutaImagen);
		return florDetenido;
	}
	
	public Sprite getSpriteDerecha() {
		Sprite florDerecha = new Sprite(rutaImagen +"/Marioflor-caminandoderecha.gif", rutaImagen);
		return florDerecha;
	}
	
	public Sprite getSpriteIzquierda() {
		Sprite florIzquierda = new Sprite(rutaImagen +"/Marioflor-caminandoizquierda.gif", rutaImagen);
		return florIzquierda;
	}

	public Sprite getSpriteSaltandoADerecha() {
		Sprite florSaltandoDerecha = new Sprite(rutaImagen +"/Marioflor-saltando.png", rutaImagen);
		return florSaltandoDerecha;
	}

	public Sprite getSpriteSaltandoAIzquierda() {
		Sprite florSaltandoIzquierda = new Sprite(rutaImagen +"/Marioflor-saltandoaizq.png", rutaImagen);
		return florSaltandoIzquierda;
	}
}
