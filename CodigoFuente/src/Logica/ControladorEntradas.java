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
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

}