package Plataforma;

import Colisionador.ColisionadorVisitorMario;
import Fabrica.Sprite;

public class Bandera extends Plataforma{

	public Bandera(int x, int y, int velocidad) {
		super(x, y, velocidad, 16, 16);
	}

	public void aceptarVisita(ColisionadorVisitorMario visitor) {		
		visitor.visitarBandera(this);
	}

	public void retirar() {
	}
}
