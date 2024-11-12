package Logica;

import Fabrica.Sprite;
import Vista.*;

public interface EntidadLogica {
	
	public void notificarObservador();
	public void setObserver(Observer observerEntidad);
	public void setSprite(Sprite sprite);
	public int getPosicionX();
	public int getPosicionY();
	public int getVelocidad();
	public Sprite getSprite();
	public boolean colisionable();
	
}
