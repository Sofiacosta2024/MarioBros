package Plataforma;

import Colisionador.ColisionadorVisitorMario;
import Fabrica.Sprite;
import Mario.*;
import PowerUp.*;

public class BloquePregunta extends BloqueSolido {
	
	protected PowerUp powerUp;
	protected boolean hayPowerUp;
	protected long mostrarPowerUp;
	protected Sprite bloqueVacio;
	protected Sprite bloqueConPowerUp;
	
	public BloquePregunta(int x, int y, PowerUp powerUp, int velocidad, Sprite[] sprites) {
		super(x,y, velocidad);
		this.powerUp = powerUp;
		hayPowerUp = true;
		mostrarPowerUp = -1;
		bloqueConPowerUp = sprite;
		bloqueVacio = sprites[0];
	}

	public void interactuar(Mario mario, String direccionColision) {
		super.interactuar(mario, direccionColision);
		if(direccionColision.equals("ABAJO") && hayPowerUp) {
			if(System.currentTimeMillis() - mostrarPowerUp >= 10000) {
				powerUp.setPosicionY(posicionY + 40);
				powerUp.actualizarAreaColision();
				powerUp.notificarObservador();
				setSprite(bloqueVacio);
			}
        	hayPowerUp = false;
		}
	}
	
	public void aceptarVisita(ColisionadorVisitorMario visitor) {
		visitor.visitarBloquePregunta(this);
	}
	
	public void setColisionable(boolean colisionable) {
		esColisionable = colisionable;
		hayPowerUp = colisionable;
	}
	
	public void setPowerUp(PowerUp pu) {
		this.powerUp = pu;
	}
	
	public PowerUp getPowerUp() {
		return powerUp;
	}
}
