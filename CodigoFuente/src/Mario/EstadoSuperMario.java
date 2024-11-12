package Mario;

import Enemigo.Enemigo;
import Fabrica.Sprite;
import Plataforma.LadrilloSolido;

public class EstadoSuperMario extends EstadoMario {

	public EstadoSuperMario(String rutaImagen) {
		this.rutaImagen = rutaImagen;
		lanzarBola = false;
	}
	
	public boolean lanzarBolaFuego() {
		return lanzarBola;
	}
	
	public void recibirDaño(Mario mario, Enemigo enemigo) {
		mario.setEstadoMario(new EstadoNormal(rutaImagen));
		mario.setCambioEstado(true);
		mario.notificarObservador();
		mario.setColisionable(false);
		new Thread(() -> {
	        try {
	            Thread.sleep(4000); 
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        mario.setColisionable(true);
	        mario.notificarObservador();
	    }).start();	
	}
	
	public void interactuarConLadrillo(LadrilloSolido ladrillo, String direccionColision) {
		if(direccionColision.equals("ABAJO")) {
			ladrillo.retirar();
			ladrillo.notificarObservador();
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
	
	public Sprite getSpriteDetenidoDerecha() {
		Sprite superDetenido = new Sprite(rutaImagen +"/Supermario-quieto.png", rutaImagen);
		return superDetenido;
	}
	public Sprite getSpriteDetenidoIzquierda() {
		Sprite superDetenido = new Sprite(rutaImagen +"/Supermario-quietoizq.png", rutaImagen);
		return superDetenido;
	}
	
	public Sprite getSpriteDerecha() {
		Sprite superDerecha = new Sprite(rutaImagen +"/Supermario-caminandoderecha.gif", rutaImagen);
		return superDerecha;
	}
	
	public Sprite getSpriteIzquierda() {	
		Sprite superDerecha = new Sprite(rutaImagen +"/Supermario-caminandoizquierda.gif", rutaImagen);
		return superDerecha;
	}

	public Sprite getSpriteSaltandoADerecha() {
		Sprite superSaltandoDerecha = new Sprite(rutaImagen +"/Supermario-saltando.png", rutaImagen);
		return superSaltandoDerecha;
	}
	
	public Sprite getSpriteSaltandoAIzquierda() { 
		Sprite superSaltandoIzquierda = new Sprite(rutaImagen +"/Supermario-saltandoaizq.png", rutaImagen);
		return superSaltandoIzquierda;
	}
}
