package Plataforma;

import Colisionador.ColisionadorVisitorBolaFuego;
import Colisionador.ColisionadorVisitorEnemigo;
import Colisionador.ColisionadorVisitorMario;
import Fabrica.Sprite;
import Logica.*;
import Mario.Mario;

public abstract class Plataforma extends Entidad{

	public Plataforma(int x, int y, int velocidad, int anchoHitBox, int altoHitBox) {
		super(x,y, velocidad,anchoHitBox, altoHitBox);
	}

	public void interactuar(Mario mario, String direccionColision) {
		if (direccionColision.equals("IZQUIERDA")) {
			mario.setPuedeMoverseALaDerecha(false); 
		} else 
			if (direccionColision.equals("DERECHA")) {
			mario.setPuedeMoverseALaIzquierda(false);  
		} else 
			if (direccionColision.equals("ARRIBA")) {
				mario.detenerSalto();
				int pisoDerecha =this.getPosicionX()+this.getAncho(); //pos final en x de la plataforma
				int pisoIzquierda = (this.getPosicionX()); //pos inicial en x de la plataforma
				mario.setLimiteDerecha(pisoDerecha);
				mario.setLimiteIzquierda(pisoIzquierda);
		} else 
			if (direccionColision.equals("ABAJO")) {
				mario.rebotarHaciaAbajo();
			}
	}
	
	public abstract void aceptarVisita(ColisionadorVisitorMario visitor);
	
	public void aceptarVisita(ColisionadorVisitorEnemigo visitor) {
		visitor.visitarPlataformaOEnemigo(this);
	}
	
	public void aceptarVisita(ColisionadorVisitorBolaFuego visitor) {
		visitor.visitarPlataforma(this);
	}
}
