package Plataforma;

import Colisionador.ColisionadorVisitorMario;
import Fabrica.Sprite;

public class BloqueSolido extends Plataforma {

	public BloqueSolido(int x, int y, int velocidad) {
		super(x,y, velocidad,32,32);
	}

	public void aceptarVisita(ColisionadorVisitorMario visitor) {
		visitor.visitarBloqueSolido(this);		
	}
	
	public void retirar() {
		
	}
}
