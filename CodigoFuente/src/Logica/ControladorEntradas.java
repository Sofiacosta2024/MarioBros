package Logica;

import Mario.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControladorEntradas implements KeyListener {

    private Mario mario;
    private boolean derechaPresionada = false;
    private boolean izquierdaPresionada = false;
    private boolean saltando = false;
    private long ultimoDisparo = 0;
    private static final long COOLDOWN_DISPARO = 300; // en milisegundos

    public ControladorEntradas(Mario mario) {
        this.mario = mario;
    }

    public void keyPressed(KeyEvent e) {
        int tecla = e.getKeyCode();
        switch(tecla) {
            case KeyEvent.VK_D:
                if (!derechaPresionada) {
                    derechaPresionada = true;
                    mario.moverDerecha();
                }
                break;
            case KeyEvent.VK_A:
                if (!izquierdaPresionada) {
                    izquierdaPresionada = true;
                    mario.moverIzquierda();
                }
                break;
            case KeyEvent.VK_W:
                if (!saltando && mario.getEnSuelo()) { // Asegura que solo salta si está en el suelo
                    saltando = true;
                    mario.saltar();
                }
                break;
            case KeyEvent.VK_SPACE:
                if (System.currentTimeMillis() - ultimoDisparo > COOLDOWN_DISPARO) { // Verifica el cooldown
                    mario.lanzarBolaFuego();
                    ultimoDisparo = System.currentTimeMillis();
                }
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        int tecla = e.getKeyCode();
        switch(tecla) {
            case KeyEvent.VK_D:
                derechaPresionada = false;
                if (!izquierdaPresionada) {
                    mario.actualizarMovimientoMario('q'); // Detiene si no se está moviendo hacia la izquierda
                } else {
                    mario.moverIzquierda(); // Cambia dirección si se mantiene izquierda presionada
                }
                break;
            case KeyEvent.VK_A:
                izquierdaPresionada = false;
                if (!derechaPresionada) {
                    mario.actualizarMovimientoMario('q'); // Detiene si no se está moviendo hacia la derecha
                } else {
                    mario.moverDerecha(); // Cambia dirección si se mantiene derecha presionada
                }
                break;
            case KeyEvent.VK_W:
                saltando = false; // Permite saltar de nuevo al soltar la tecla
                break;
        }
    }

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub	
	}
}