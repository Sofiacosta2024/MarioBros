package Logica;

import Mario.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControladorEntradas implements KeyListener {

	private Mario mario;

	public ControladorEntradas(Mario mario) {
		this.mario = mario;
	}

    public void keyPressed(KeyEvent e) {
		int tecla = e.getKeyCode();
		switch(tecla) {
		case KeyEvent.VK_D:
			mario.moverDerecha();
			break;
		case KeyEvent.VK_A:
			mario.moverIzquierda();
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
		if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_A) {
			mario.actualizarMovimientoMario(mario.QUIETO);
		}
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub	
	}
}