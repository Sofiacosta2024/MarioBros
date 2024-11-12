package PowerUp;

import Fabrica.Sprite;
import Mario.*;
import Logica.*;

public abstract class PowerUp extends Entidad {
	
	protected int puntaje, posicionInicialX, posicionInicialY;
	
	public PowerUp(int x, int y, int velocidad, Sprite sprite, int anchoHitBox, int altoHitBox) {
		super(x,y, velocidad, sprite,anchoHitBox,altoHitBox);
		posicionInicialX = x;
		posicionInicialY = y;
	}
	
	public void retirar() {
        esColisionable = false;
        notificarObservador();
    }
	
	public abstract void afectar(Mario mario);

	public void setPosicionInicial() {
		posicionX = posicionInicialX;
		posicionY = posicionInicialY;
	}
	
	public int getPuntaje() {
		return puntaje;
	}	
}
