package Mario;

import Colisionador.ColisionadorVisitorMario;
import Fabrica.Sprite;
import Logica.Entidad;

public class BolaFuego extends Entidad {
	
	private boolean enMovimiento;

	public BolaFuego(int x, int y, int velocidad, Sprite sprite, char direccionMovimiento, char direccionMovimientoAnterior) {
		super(x, y, velocidad, 16, 16);
		this.sprite = sprite;
		this.direccionMovimiento = direccionMovimiento;
		this.direccionMovimientoAnterior = direccionMovimientoAnterior;
		enMovimiento = false;
	}

	public void aceptarVisita(ColisionadorVisitorMario visitor) {
		
	}
	
	public void notificarObservador() {
		observador.actualizar();
	}
	
	public void retirar() {
		observador.actualizarPorMuerte();
		enMovimiento = false;
	}
	
	public void mover(int nuevaX, int nuevaY) {
		posicionX = posicionX + nuevaX;
		if(posicionX <= -65)
			retirar();
		actualizarAreaColision();
		observador.actualizarPosicionTamano();
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
	
	public boolean enMovimiento() {
		return enMovimiento;
	}
}
