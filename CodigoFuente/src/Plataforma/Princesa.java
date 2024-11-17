package Plataforma;

import Colisionador.ColisionadorVisitorMario;

public class Princesa extends Plataforma{

	public Princesa(int x, int y, int velocidad) {
		super(x, y, velocidad,32,32);
	}

	public void aceptarVisita(ColisionadorVisitorMario visitor) {
	}

	public void retirar() {		
	
	}

}
