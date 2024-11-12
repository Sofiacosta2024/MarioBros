package Threads;

import Mario.Mario;

public class TemporizadorEstadoEstrella extends Thread {
	
    private Mario mario;
    private long duracionEstadoEstrella;

    public TemporizadorEstadoEstrella(Mario mario){
        this.mario = mario;
        duracionEstadoEstrella = 10000;
    }

    public void run(){
        try {
            Thread.sleep(duracionEstadoEstrella); 
            mario.setEstadoMario(mario.getEstadoAnterior());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
