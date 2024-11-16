package Plataforma;

import Colisionador.ColisionadorVisitorEnemigo;
import Colisionador.ColisionadorVisitorMario;
import Logica.Juego;
import Mario.Mario;

public class Vacio extends Plataforma{

	public Vacio(int x, int y, int velocidad) {
		super(x,y, velocidad,16,32);
	}

	public void interactuar(Mario mario, Juego juego) {
		if(esColisionable) {
			mario.decrementarVida();
			juego.getNivelActual().getMario().setPuntaje(juego.getNivelActual().getMario().getPuntaje() + puntajeMarioVacio());
			juego.actualizarPuntaje();
			juego.actualizarVidas();
			esColisionable = false;
			new Thread(() -> {
				try {
					Thread.sleep(550); 
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				mario.setEstadoInicial();
				juego.reiniciarNivel();
			}).start();
		}
	}

	public void aceptarVisita(ColisionadorVisitorMario visitor) {
		if(esColisionable)
			visitor.visitarVacio(this);

	}

	public void aceptarVisita(ColisionadorVisitorEnemigo visitor) {
		if(esColisionable)
			visitor.visitarVacio(this);
	}

	public void retirar() {

	}

	public int puntajeMarioVacio() {
		return -15;
	}
}
