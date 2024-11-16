package PowerUp;

import Mario.*;
import Logica.*;

public abstract class PowerUp extends Entidad {
	
	protected boolean  esColisionable;
	protected int puntaje;
	
	public PowerUp(int x, int y, int velocidad, int anchoHitBox, int altoHitBox) {
		super(x,y, velocidad,anchoHitBox,altoHitBox);
		esColisionable = true;
	}
	
	public void retirar() {
        esColisionable = false;
        observador.actualizarPorMuerte();
    }
	
	public void notificarObservador() {
		if(esColisionable)
			observador.actualizar();
	}
	
	public abstract void afectar(Mario mario);
	
	public void setColisionable(boolean colisionable) {
		esColisionable = colisionable;
		notificarObservador();
	}
	
	public boolean colisionable() {
		return esColisionable;
	}
	
	public int getPuntaje() {
		return puntaje;
	}	
}
