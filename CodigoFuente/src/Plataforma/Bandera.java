package Plataforma;

import Colisionador.ColisionadorVisitorMario;
import Fabrica.Sprite;

public class Bandera extends Plataforma{

	public Bandera(int x, int y, int velocidad, Sprite sprite) {
		super(x, y, velocidad, sprite, 16, 16);
	}

	public void aceptarVisita(ColisionadorVisitorMario visitor) {		
	}

	public void retirar() {
	}
}
