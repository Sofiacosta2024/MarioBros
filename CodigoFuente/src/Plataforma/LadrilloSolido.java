package Plataforma;

import Colisionador.ColisionadorVisitorBolaFuego;
import Colisionador.ColisionadorVisitorMario;
import Fabrica.Sprite;
import Mario.Mario;

public class LadrilloSolido extends BloqueSolido{

	public LadrilloSolido(int x, int y, int velocidad, Sprite sprite) {
		super(x,y, velocidad, sprite);
	}

	public void interactuar(Mario mario,String direccionColision) {
		super.interactuar(mario, direccionColision);
		mario.getEstadoMario().interactuarConLadrillo(this,direccionColision);
	}
	
	public void aceptarVisita(ColisionadorVisitorMario visitor) {
		visitor.visitarLadrillo(this);
	}
	
	public void aceptarVisita(ColisionadorVisitorBolaFuego visitor) {
		visitor.visitarLadrillo(this);
	}
	
	public void retirar() {
		esColisionable = false;
	}
}