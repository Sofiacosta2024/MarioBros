package Plataforma;

import Colisionador.ColisionadorVisitorBolaFuego;
import Colisionador.ColisionadorVisitorEnemigo;
import Colisionador.ColisionadorVisitorMario;
import Logica.*;
import Mario.Mario;

public abstract class Plataforma extends Entidad {

	protected boolean esColisionable;

	public Plataforma(int x, int y, int velocidad, int anchoHitBox, int altoHitBox) {
		super(x,y, velocidad,anchoHitBox, altoHitBox);
		esColisionable = true;
	}

	public void interactuar(Mario mario, String direccionColision) {
		if(esColisionable) {
			if (direccionColision.equals("IZQUIERDA")) {
				mario.setPuedeMoverseALaDerecha(false); 
				if(!mario.getEnSuelo()) {
					mario.rebotarHaciaAbajo();
				}
			} else 
				if (direccionColision.equals("DERECHA")) {
					mario.setPuedeMoverseALaIzquierda(false); 
					if(!mario.getEnSuelo()) {
						mario.rebotarHaciaAbajo();
					}
				} 
				else 
					if (direccionColision.equals("ARRIBA")) {
						mario.detenerSalto();
						mario.setEnSuelo(true);
						int pisoDerecha =this.getPosicionX()+this.getAncho()-5; 
						int pisoIzquierda = (this.getPosicionX()-20); 
						mario.setLimiteDerecha(pisoDerecha);
						mario.setLimiteIzquierda(pisoIzquierda);
					} else 
						if (direccionColision.equals("ABAJO")) {
							mario.rebotarHaciaAbajo();
						}
		}
	}

	public abstract void aceptarVisita(ColisionadorVisitorMario visitor);

	public void aceptarVisita(ColisionadorVisitorEnemigo visitor) {
		if(esColisionable)
			visitor.visitarPlataformaOEnemigo(this);
	}

	public void aceptarVisita(ColisionadorVisitorBolaFuego visitor) {
		if(esColisionable)
			visitor.visitarPlataforma(this);
	}

	public void notificarObservador() {
		if(esColisionable)
			observador.actualizar();
	}

	public void setColisionable(boolean colisionable) {
		esColisionable = colisionable;
		notificarObservador();
	}

	public boolean colisionable() {
		return esColisionable;
	}
}
