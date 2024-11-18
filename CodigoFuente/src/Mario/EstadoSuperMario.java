package Mario;

import Enemigo.Enemigo;
import Fabrica.Sprite;
import Plataforma.LadrilloSolido;

public class EstadoSuperMario extends EstadoMario {

	public EstadoSuperMario() {
		lanzarBola = false;
	}
	
	public boolean lanzarBolaFuego() {
		return lanzarBola;
	}
	
	public void recibirDaño(Mario mario, Enemigo enemigo) {
		mario.setEstadoMario(new EstadoNormal());
		mario.setCambioEstado(true);
		mario.actualizarSprite(mario.getDireccionMovimiento());
		mario.getObserver().actualizarPosicionTamano();
		mario.setColisionable(false);
		new Thread(() -> {
	        try {
	            Thread.sleep(3000); 
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        mario.setColisionable(true);
	    }).start();	
	}
	
	public void interactuarConLadrillo(Mario mario, LadrilloSolido ladrillo, String direccionColision) {
		if(direccionColision.equals("ABAJO")) {
			ladrillo.retirar();
		}
	}

	public int puntajeEstrella() {
		return 30;
	}
	
	public int puntajeFlorFuego() {
		return 30;
	}
	
	public int puntajeSuperChampinon() {
		return 50;
	}
	
	public Sprite getSpriteDetenidoDerecha(Sprite[] sprites) {
		return sprites[6];
	}
	public Sprite getSpriteDetenidoIzquierda(Sprite[] sprites) {
		return sprites[7];
	}
	
	public Sprite getSpriteDerecha(Sprite[] sprites) {
		return sprites[8];
	}
	
	public Sprite getSpriteIzquierda(Sprite[] sprites) {	
		return sprites[9];
	}

	public Sprite getSpriteSaltandoADerecha(Sprite[] sprites) {
		return sprites[10];
	}
	
	public Sprite getSpriteSaltandoAIzquierda(Sprite[] sprites) { 
		return sprites[11];
	}
}
