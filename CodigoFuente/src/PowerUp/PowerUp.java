package PowerUp;

import Fabrica.Sprite;
import Mario.*;
import Logica.*;

public abstract class PowerUp extends Entidad {

	protected int puntaje;
	
	public PowerUp(int x, int y, int velocidad, int anchoHitBox, int altoHitBox) {
		super(x,y, velocidad,anchoHitBox,altoHitBox);
	}
	
	public void retirar() {
        esColisionable = false;
        notificarObservador();
    }
	
	public abstract void afectar(Mario mario);
	
	public int getPuntaje() {
		return puntaje;
	}	
}
