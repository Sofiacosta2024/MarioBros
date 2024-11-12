package Plataforma;

import Colisionador.ColisionadorVisitorMario;
import Fabrica.Sprite;

public class Princesa extends Plataforma{

	public Princesa(int x, int y, int velocidad, Sprite sprite) {
		super(x, y, velocidad, sprite,32,32);
	}

	public void aceptarVisita(ColisionadorVisitorMario visitor) {
	}

	public void retirar() {		
	}

}
