package Plataforma;

import Colisionador.ColisionadorVisitorMario;
import Logica.Juego;
import Mario.Mario;

public class Bandera extends Plataforma{

	public Bandera(int x, int y, int velocidad) {
		super(x, y, velocidad, 16, 16);
	}

	public void aceptarVisita(ColisionadorVisitorMario visitor) {		
		visitor.visitarBandera(this);
	}
	
	public void interactuar(Juego juego, Mario mario) {
		if(esColisionable) {
			mario.detenerMario();
			mario.setPuedeMoverseALaDerecha(false);
			mario.setPuedeMoverseALaIzquierda(false);
			juego.jugadorPasaDeNivel();
			esColisionable = false;
		}
	}

	public void retirar() {
	
	}
}
