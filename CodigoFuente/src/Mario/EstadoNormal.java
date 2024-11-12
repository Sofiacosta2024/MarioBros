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
	
	public Sprite getSpriteDetenidoDerecha(Sprite[] sprites) {
		return sprites[0];
	}
	
	public Sprite getSpriteDetenidoIzquierda(Sprite[] sprites) {
		return sprites[1];
	}
	
	public Sprite getSpriteDerecha(Sprite[] sprites)  {
		return sprites[2];
	}
	
	public Sprite getSpriteIzquierda(Sprite[] sprites) {
		return sprites[3];
	}
	
	public Sprite getSpriteSaltandoADerecha(Sprite[] sprites) {
		return sprites[4];
	}
	
	public Sprite getSpriteSaltandoAIzquierda(Sprite[] sprites) {
		return sprites[5];
	}
}
