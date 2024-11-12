package Sonido;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;

public class Sonido {

    protected static Sonido instanciaSonido;
    private Clip clipFondo;
    private Clip clip;

    public static Sonido obtenerInstancia() {
        if (instanciaSonido == null) 
            instanciaSonido = new Sonido();
        return instanciaSonido;
    }
    public void reproducirSonidoGanar() {
    	reproducirClip("/Sonido/Sounds/ganar.wav");
    }
    public void reproducirSonidoPasoNivel() {
    	reproducirClip("/Sonido/Sounds/yahoo.wav");
    }
    public void reproducirSonidoComienzaPartida() {
    	reproducirClip("/Sonido/Sounds/herewego.wav");
    }
    public void reproducirSonidoGameOver() {
    	reproducirClip("/Sonido/Sounds/gameover.wav");
    }
    public void reproducirSonidoFondo() {
        this.cargarSonidoFondo();
        this.reproducirLoopSonidoFondo();
    }
    public void reproducirSonidoBolaFuego() {
    	reproducirClip("/Sonido/Sounds/fireball.wav");
    }
    public void reproducirSonidoSaltar() {
    	reproducirClip("/Sonido/Sounds/jump.wav");
    }
    public void reproducirSonidoCaeAlVacio() {
    	reproducirClip("/Sonido/Sounds/mariofalling.wav");
    }
    public void reproducirSonidoMorir() {
    	reproducirClip("/Sonido/Sounds/Mariodie.wav");
    }
    private void cargarSonidoFondo() {
    	String rutaArchivo="/Sonido/Sounds/fondo.wav";
        try {
            InputStream audioStream = getClass().getResourceAsStream(rutaArchivo);
            if (audioStream == null) {
                System.out.println("Error: no se encontró el archivo de sonido.");
                return;
            }
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioStream);
            clipFondo = AudioSystem.getClip();
            clipFondo.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Error al cargar el sonido: " + e.getMessage());
        }
    }  

    private void reproducirLoopSonidoFondo() {
        if (clipFondo != null) {
            clipFondo.setFramePosition(0);
            clipFondo.loop(Clip.LOOP_CONTINUOUSLY); 
        } else {
            System.out.println("Clip de fondo no está inicializado.");
        }
    }
    public void reproducirClip(String rutaArchivo) {
        try {
            InputStream audioStream = getClass().getResourceAsStream(rutaArchivo);
            if (audioStream == null) {
                System.out.println("Error: no se encontró el archivo de efecto.");
                return;
            }
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioStream);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.setFramePosition(0);
            clip.start(); 
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Error al reproducir el efecto: " + e.getMessage());
        }
    }
    public void detenerSonidoFondo() {
        if (clipFondo != null) {
            clipFondo.stop();
        }
    }
}