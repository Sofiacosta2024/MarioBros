package Plataforma;

import Colisionador.ColisionadorVisitorEnemigo;
import Colisionador.ColisionadorVisitorMario;
import Fabrica.Sprite;
import Mario.Mario;
public class Vacio extends Plataforma{

	public Vacio(int x, int y, int velocidad) {
		super(x,y, velocidad,16,32);
	}

	public void interactuar(Mario m, String direccionColisionable) {
		//El manejo se genera desde el colisionador
	}

	public void aceptarVisita(ColisionadorVisitorMario visitor) {
		visitor.visitarVacio(this);
		
	}
	
	public void aceptarVisita(ColisionadorVisitorEnemigo visitor) {
		visitor.visitarVacio(this);
	}
	
	public void retirar() {
		
	}

    public int puntajeMarioVacio() {
    	return -15;
    }
}
