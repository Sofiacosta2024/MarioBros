package Mario;

import Colisionador.ColisionadorVisitorMario;
import Fabrica.Sprite;
import Logica.Entidad;

public class BolaFuego extends Entidad {
	
	protected boolean esColisionable;
	private boolean enMovimiento;

	public BolaFuego(int x, int y, int velocidad, Sprite sprite, char direccionMovimiento, char direccionMovimientoAnterior) {
		super(x, y, velocidad, 16, 16);
		this.sprite = sprite;
		this.direccionMovimiento = direccionMovimiento;
		this.direccionMovimientoAnterior = direccionMovimientoAnterior;
		enMovimiento = false;
		esColisionable = true;
	}

	public void aceptarVisita(ColisionadorVisitorMario visitor) {
		
	}
	
	public void notificarObservador() {
		if(esColisionable)
			observador.actualizar();
	}
	
	public void retirar() {
		observador.actualizarPorMuerte();
		enMovimiento = false;
		esColisionable = false;
	}
	
	public void mover() {
		if(enMovimiento && esColisionable) {
			char direccion = getDireccionMovimiento();
			char direccionAnterior = getDireccionMovimientoAnterior();
			if (direccion == DERECHA || (direccion == QUIETO && direccionAnterior == DERECHA)) {
				posicionX += 2;
			}
			else { 
				posicionX -= 2;
			}
			actualizarAreaColision();
			observador.actualizarPosicionTamano();
		}
	}
	
	public void setEnMovimiento(boolean enMovimiento) {
		this.enMovimiento = enMovimiento;
	}
	
	public char getDireccionMovimiento() {
		return direccionMovimiento;
	}
	
	public char getDireccionMovimientoAnterior() {
		return direccionMovimientoAnterior;
	}
	
	public boolean colisionable() {
		return esColisionable;
	}
}
