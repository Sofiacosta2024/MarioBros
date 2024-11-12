package Mario;

import Colisionador.ColisionadorVisitorMario;
import Fabrica.Sprite;
import Logica.Entidad;

public class BolaFuego extends Entidad implements Runnable {
	
	private boolean enMovimiento;
	private char direccionMovimiento;
	private char direccionMovimientoAnterior;

	public BolaFuego(int x, int y, int velocidad, Sprite sprite, char direccionMovimiento, char direccionMovimientoAnterior) {
		super(x, y, velocidad, sprite, 16, 16);
		enMovimiento = true;
		this.direccionMovimiento = direccionMovimiento;
		this.direccionMovimientoAnterior = direccionMovimientoAnterior;
	}

	public void aceptarVisita(ColisionadorVisitorMario visitor) {
		
	}
	
	public void notificarObservador() {
		if(observador != null) {
			observador.actualizar();
		}
	}
	
	public void retirar() {
		esColisionable = false;
		detener();
	}

	public void run() {
        while (enMovimiento) {
            mover();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
	
	public synchronized void detener() {
		enMovimiento = false;
	}
	
	private synchronized void mover() {
		if(direccionMovimiento == 'd' || direccionMovimiento == 'q' && direccionMovimientoAnterior == 'd')
			posicionX += velocidad*2;
		else
			posicionX -= velocidad*2;
		actualizarAreaColision();
		observador.actualizarPosicionTamano();
	}
}
