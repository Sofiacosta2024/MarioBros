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
		retirarBolaFuego();
	}

	public void visitarLadrillo(LadrilloSolido ladrillo) {
		ladrillo.retirar();
		retirarBolaFuego();
	}

	public void visitarEnemigo(Enemigo enemigo) {
		enemigo.retirar();
		retirarBolaFuego();
	}
	
	private void retirarBolaFuego() {
		juego.getNivelActual().getProyectiles().remove(entidadColisionable);
		entidadColisionable.retirar();
	}
}
