package Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PantallaVictoria extends JPanel {

	private static final long serialVersionUID = 1L;
	private ControladorVistas controladorVistas;
    private JLabel imagenFondo;
    private JLabel etiquetaPuntaje;
    private JButton botonMenuPrincipal;
    private JLabel etiquetaFelicidades;
    private JLabel etiquetaGanar;
    
    public PantallaVictoria(ControladorVistas controladorVistas) {
        this.controladorVistas = controladorVistas;
        setSize(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);
        setLayout(null);
        agregarFondo();
        agregarEtiquetaPuntaje();
        agregarBotonMenuPrincipal();
        agregarEtiquetaGanador();
    }
    
    public void agregarFondo() {
        imagenFondo = new JLabel();
        ImageIcon iconoImagen = new ImageIcon(this.getClass().getResource("/Imagenes/Fondos/pantallaVictoria.jpg"));
        Image imagenEscalada = iconoImagen.getImage().getScaledInstance(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO, Image.SCALE_SMOOTH);
        Icon iconoImagenEscalado = new ImageIcon(imagenEscalada);
        imagenFondo.setIcon(iconoImagenEscalado);
        imagenFondo.setBounds(0, 0, ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);
        add(imagenFondo);
    }
    
    public void agregarEtiquetaPuntaje() {
    	Font fuente=cargarFuente();
        etiquetaPuntaje = new JLabel("Puntaje:"+0);
        etiquetaPuntaje.setBackground(Color.WHITE); 
        etiquetaPuntaje.setOpaque(true);
        etiquetaPuntaje.setFont(fuente);
        etiquetaPuntaje.setForeground(Color.BLACK);
        etiquetaPuntaje.setBounds(300, 200, 200, 100);
        etiquetaPuntaje.setHorizontalAlignment(JLabel.CENTER);
        imagenFondo.add(etiquetaPuntaje);
    }
    
    public void setPuntajeLabel(int puntaje) {
    	etiquetaPuntaje.setText("Puntaje: "+puntaje);
    }
    
    public void agregarBotonMenuPrincipal() {
        botonMenuPrincipal = new JButton();
        decorarBotonMenuPrincipal();
        asignarImagenBoton(botonMenuPrincipal, "/Imagenes/Botones/BotonFinishNuevo.png");
        registrarOyenteBotonMenuPrincipal();
        imagenFondo.add(botonMenuPrincipal);
        botonMenuPrincipal.revalidate();
        botonMenuPrincipal.repaint();
    }
    
    public void agregarEtiquetaGanador() {
    	agregarEtiquetaFelicidades();
    	agregarEtiquetaGanar();
    }
    
    public void agregarEtiquetaFelicidades() {
        Font fuente = cargarFuente();
        etiquetaFelicidades = new JLabel("¡Felicidades!");
        etiquetaFelicidades.setFont(fuente); 
        etiquetaFelicidades.setForeground(Color.BLACK); 
        etiquetaFelicidades.setBounds(300, 50, 500, 100); 
        etiquetaFelicidades.setHorizontalAlignment(JLabel.CENTER);
        imagenFondo.add(etiquetaFelicidades); 
    }    
    public void agregarEtiquetaGanar() {
        Font fuente = cargarFuente();
        etiquetaGanar = new JLabel("Has ganado");
        etiquetaGanar.setFont(fuente); 
        etiquetaGanar.setForeground(Color.BLACK); 
        etiquetaGanar.setBounds(300, 100, 500, 100); 
        etiquetaGanar.setHorizontalAlignment(JLabel.CENTER);
        imagenFondo.add(etiquetaGanar); 
    }
    
    public void asignarImagenBoton(JButton b, String s) {
        ImageIcon iconoBoton = new ImageIcon(this.getClass().getResource(s));
        Image imagenEscalada = iconoBoton.getImage().getScaledInstance(200, 50, Image.SCALE_SMOOTH);
        Icon iconoEscalado = new ImageIcon(imagenEscalada);
        b.setIcon(iconoEscalado);
    }
    
    public void decorarBotonMenuPrincipal() {
        transparentarBoton(botonMenuPrincipal);
        botonMenuPrincipal.setBounds((ConstantesVistas.PANEL_ANCHO / 2) - 100, ConstantesVistas.PANEL_ALTO - 200, 200, 50);
        botonMenuPrincipal.setFocusable(false);
    }
    
    public void registrarOyenteBotonMenuPrincipal() {
        botonMenuPrincipal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controladorVistas.accionarPantallaVictoria();
            }
        });
    }
    
    public void transparentarBoton(JButton boton) {
        boton.setOpaque(false);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
    }
    
	private Font cargarFuente() {
		File fuenteFile = new File("src/Fuente/fuentejuego.ttf");
		try {
        	return Font.createFont(Font.TRUETYPE_FONT, fuenteFile).deriveFont(16.3f);
		} catch (FontFormatException | IOException e) {
			return new Font("Arial", Font.BOLD, 22);
		}
	}
}