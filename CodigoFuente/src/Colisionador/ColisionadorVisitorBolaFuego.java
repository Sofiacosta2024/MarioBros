package Colisionador;

import Logica.*;
import Mario.BolaFuego;
import Plataforma.*;
import Enemigo.*;

public class ColisionadorVisitorBolaFuego {
	
	protected BolaFuego entidadColisionable;
	protected Juego juego;

	public ColisionadorVisitorBolaFuego(BolaFuego bolaFuego, Juego juego) {
		entidadColisionable = bolaFuego;
		this.juego = juego; 
	}

	public void visitarPlataforma(Plataforma plataforma) {
		juego.getNivelActual().getProyectiles().remove(entidadColisionable);
		retirarBolaFuego();
	}

	public void visitarLadrillo(LadrilloSolido ladrillo) {
		ladrillo.retirar();
		ladrillo.notificarObservador();
		retirarBolaFuego();
	}

	public void visitarEnemigo(Enemigo enemigo) {
		enemigo.retirar();
		enemigo.notificarObservador();
		juego.getJugador().setPuntaje(juego.getJugador().getPuntaje() + enemigo.getPuntaje());
		retirarBolaFuego();
	}
	
	private void retirarBolaFuego() {
		entidadColisionable.retirar();
	}
}
