package Logica;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControladorEntradas implements KeyListener {
	
	private final char QUIETO = 'q';
	private EntidadMovimientoMario mario;

	public ControladorEntradas(EntidadMovimientoMario mario) {
		this.mario = mario;
	}

	public void keyPressed(KeyEvent e) {
		int tecla = e.getKeyCode();
		switch(tecla) {
		case KeyEvent.VK_D:
			mario.activarDerecha();
			break;
		case KeyEvent.VK_A:
			mario.activarIzquierda();
			break;
		case KeyEvent.VK_W:
			mario.saltar();
			break;
		case KeyEvent.VK_SPACE:
			mario.lanzarBolaFuego(); 
			break;
		}
	}
    
    public void keyReleased(KeyEvent e) {
		int tecla = e.getKeyCode();
		switch(tecla) {
		case KeyEvent.VK_D:
			mario.desactivarDerecha();
			break;
		case KeyEvent.VK_A:
			mario.desactivarIzquierda();
			break;
		}
		if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_A) {
  			mario.actualizarMovimientoMario(QUIETO);
  		}
	}
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

}