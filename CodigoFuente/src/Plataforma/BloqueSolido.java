package Plataforma;

import Colisionador.ColisionadorVisitorMario;

public class BloqueSolido extends Plataforma {

	public BloqueSolido(int x, int y, int velocidad) {
		super(x,y, velocidad,40,40);
	}

	public void aceptarVisita(ColisionadorVisitorMario visitor) {
		visitor.visitarBloqueSolido(this);		
	}
	
	public void retirar() {
		esColisionable = false;
		observador.actualizarPorMuerte();
	}
}
