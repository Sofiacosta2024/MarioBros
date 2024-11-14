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
		//System.out.println(mario.getEnSuelo());
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
				/*if() {
				mario.setEnSuelo(false);
				}*/
				
				} else 
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
						//System.out.println("a");
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
