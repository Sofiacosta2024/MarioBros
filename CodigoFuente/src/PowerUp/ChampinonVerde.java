package PowerUp;

import Colisionador.ColisionadorVisitorMario;
import Fabrica.Sprite;
import Mario.Mario;
import Sonido.Sonido;

public class ChampinonVerde extends PowerUp{

	public ChampinonVerde(int x, int y, int velocidad) {
		super(x,y, velocidad,24,24);
		puntaje = 100;
	}

	public void afectar(Mario mario) {
		if(esColisionable) {
			Sonido.obtenerInstancia().reproducirSonidoObtuvoVida();
			mario.aumentarVida();
			mario.setPuntaje(mario.getPuntaje() + puntaje);
			retirar();
		}
	}

	public void aceptarVisita(ColisionadorVisitorMario visitor) {
		visitor.visitarChampinonVerde(this);
	}
}
