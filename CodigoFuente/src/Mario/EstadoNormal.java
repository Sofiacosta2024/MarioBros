package Mario;

import Enemigo.Enemigo;
import Fabrica.Sprite;

public class EstadoNormal extends EstadoMario {
	
	public EstadoNormal(String rutaImagen) {
		this.rutaImagen = rutaImagen;
		lanzarBola = false;
	}
	
	public boolean lanzarBolaFuego() {
		return lanzarBola;
	}
	
	public void recibirDaño(Mario mario, Enemigo enemigo) {
		mario.decrementarVida();
		mario.setColisionable(false);
		new Thread(() -> {
	        try {
	            Thread.sleep(550); 
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

			mario.getJuego().reiniciarNivel();
	    }).start();
	}
	
	public int puntajeEstrella() {
		return 20;
	}

	public int puntajeFlorFuego() {
		return 5;
	}
	
	public int puntajeSuperChampinon() {
		return 10;
	}
	
	public Sprite getSpriteDetenidoDerecha() {
		return new Sprite(rutaImagen+"/Marioquieto.png", rutaImagen);
	}
	
	public Sprite getSpriteDetenidoIzquierda() {
		return new Sprite(rutaImagen+"/Marioquietoizq.png",rutaImagen);
	}
	
	public Sprite getSpriteDerecha() {
		return new Sprite(rutaImagen+"/Mariocaminando.gif", rutaImagen);
	}
	
	public Sprite getSpriteIzquierda() {
		return new Sprite(rutaImagen+"/Mariocaminandoizq.gif", rutaImagen);
	}
	
	public Sprite getSpriteSaltandoADerecha() {
		return new Sprite(rutaImagen+"/MarioJumping.png", rutaImagen);
	}
	
	public Sprite getSpriteSaltandoAIzquierda() {
		return new Sprite(rutaImagen+"/MarioJumpingizq.png", rutaImagen);
	}
}
