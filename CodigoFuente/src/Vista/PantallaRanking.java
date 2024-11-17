package Vista;

import java.awt.BorderLayout;
import Logica.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.FontFormatException;
import javax.swing.Icon;

public class PantallaRanking extends JPanel{

	private static final long serialVersionUID = 1L;
	private JButton botonVolver;
	private ControladorVistas controladorVistas;
	private JLabel imagenFondo;
	private JPanel panelInformacion;
	private JLabel labelRanking;
	private JLabel labelJugadores;
	private JLabel labelMejores;
	private JLabel[] labelsRank;
	
	public PantallaRanking(ControladorVistas controladorVistas) {
		this.controladorVistas=controladorVistas;
		setSize(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);
		setLayout(null);
		agregarPanelInformacion();
		actualizarRanking();
		agregarBotonVolver();
		agregarImagenFondo();
	}
	
	public void agregarImagenFondo() {
		imagenFondo = new JLabel();
		ImageIcon iconoImagen = new ImageIcon(getClass().getClassLoader().getResource("Imagenes/Fondos/fondoRanking.jpg"));
		Image imagenEscalada = iconoImagen.getImage().getScaledInstance(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO, Image.SCALE_SMOOTH);
		Icon iconoImagenEscalado = new ImageIcon(imagenEscalada);
		imagenFondo.setIcon(iconoImagenEscalado);
		imagenFondo.setBounds(0, 0, ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);
		add(imagenFondo);
	}
	
	public void agregarBotonVolver() {
		botonVolver = new JButton("VOLVER");
		decorarBotonVolver();
		registrarOyenteBotonVolver();
		add(botonVolver);
	}
	
	public void decorarBotonVolver() {
		Font fuente = cargarFuente();
		transparentarBoton(botonVolver);
		botonVolver.setBounds((ConstantesVistas.PANEL_ANCHO / 2),ConstantesVistas.PANEL_ALTO - 100, 600 , 80);
		botonVolver.setForeground(Color.WHITE);
		botonVolver.setFont(fuente);
	}
	
	public void registrarOyenteBotonVolver() {
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladorVistas.mostrarPantallaModoJuego();
			}
		});
	}
	
	public void transparentarBoton(JButton boton) {
		boton.setOpaque(false);
		boton.setContentAreaFilled(false);
		boton.setBorderPainted(false);
	}
	
	public void agregarPanelInformacion() {
		panelInformacion = new JPanel();
		panelInformacion.setLayout(null);
		panelInformacion.setPreferredSize(new Dimension(ConstantesVistas.PANEL_INFORMACION_ANCHO, ConstantesVistas.PANEL_ALTO));
		agregarLabelsEditablesInformacion();
		agregarLabelsRanking();
		add(panelInformacion, BorderLayout.EAST);
	}
	
	public void agregarLabelsRanking() {
		labelsRank = new JLabel[5];
		Font fuente = cargarFuente();
		int y = 200;
		for(int i=0; i<labelsRank.length; i++) {
			labelsRank[i] = new JLabel((i + 1) + ". " + "XXXX" + " - " + "0");
			labelsRank[i].setBounds(100, y, 600, 30);
			labelsRank[i].setForeground(Color.WHITE);
			labelsRank[i].setFont(fuente);
			add(labelsRank[i]);
			y+=40;
		}
	}
	
	public void actualizarRanking() {
		List<Puntuacion> ranking = Ranking.getInstancia().getRanking();
		int i=0;
		for (Puntuacion jugador: ranking) {
	        if (i < ranking.size()) {
	            labelsRank[i].setText((i + 1) + ". " + jugador.getNombre() + " - " + jugador.getPuntaje());
	        } else {
	            labelsRank[i].setText((i + 1) + ". -");
	        }
	        i++;
	    }
	}
	
	public void agregarLabelsEditablesInformacion() {
		labelRanking = new JLabel("RANKING");
		labelMejores = new JLabel("DE MEJORES");
		labelJugadores = new JLabel("JUGADORES");
		decorarLabelsInformacion();
		add(labelRanking);
		add(labelMejores);
		add(labelJugadores);
	}
	
	public void decorarLabelsInformacion() {
		Font fuente = cargarFuente();
		labelRanking.setBounds(240, 20, 300, 100);
		labelMejores.setBounds(218, 55, 300, 100);
		labelJugadores.setBounds(221, 85, 300, 100);
		labelRanking.setForeground(Color.WHITE);
		labelMejores.setForeground(Color.WHITE);
		labelJugadores.setForeground(Color.WHITE);
		labelRanking.setFont(fuente.deriveFont(24f));
		labelMejores.setFont(fuente.deriveFont(24f));
		labelJugadores.setFont(fuente.deriveFont(24f));
	}
	
	public Font cargarFuente() {
		File fuenteFile = new File("src/Fuente/fuentejuego.ttf");
		try {
        	return Font.createFont(Font.TRUETYPE_FONT, fuenteFile).deriveFont(16.3f);
		} catch (FontFormatException | IOException e) {
			return new Font("Arial", Font.BOLD, 22);
		}
	}
}