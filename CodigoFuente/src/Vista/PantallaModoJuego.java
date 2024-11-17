package Vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

public class PantallaModoJuego extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private ControladorDeVistas controladorVistas;
	private JButton botonModoOriginal;
	private JButton botonModoEspecial;
	private JLabel imagenFondo;
	private JButton botonRanking;
	private Font fuente;

	public PantallaModoJuego(ControladorDeVistas controladorVistas) {
		this.controladorVistas = controladorVistas;
		setSize(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);
		fuente=cargarFuente();
		setLayout(null);
		agregarFondo();
		agregarBotonModoOriginal();
		agregarBotonModoEspecial();
		agregarBotonRanking();
	}

	public void agregarFondo() {
		imagenFondo = new JLabel();
		ImageIcon iconoImagen = new ImageIcon(this.getClass().getResource("/Imagenes/Fondos/pantallainicialmario.jpeg"));
		Image imagenEscalada = iconoImagen.getImage().getScaledInstance(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO, Image.SCALE_SMOOTH);
		Icon iconoImagenEscalado = new ImageIcon(imagenEscalada);
		imagenFondo.setIcon(iconoImagenEscalado);
		imagenFondo.setBounds(0,0, ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);
		add(imagenFondo);
	}

	public void agregarBotonModoOriginal() {
		botonModoOriginal = new JButton("MODO ORIGINAL");
		botonModoOriginal.setFont(fuente);
		decorarBotonModoOriginal();
		registrarOyenteBotonModoOriginal();
		imagenFondo.add(botonModoOriginal);
		botonModoOriginal.setVisible(true);
		botonModoOriginal.revalidate();
		botonModoOriginal.repaint();
	}

	public void agregarBotonModoEspecial() {
		botonModoEspecial = new JButton("MODO ESPECIAL");
		botonModoEspecial.setFont(fuente);
		decorarBotonModoEspecial();
		registrarOyenteBotonModoEspecial();
		imagenFondo.add(botonModoEspecial);
		botonModoEspecial.setVisible(true);
		botonModoEspecial.revalidate();
		botonModoEspecial.repaint();
	}

	public void agregarBotonRanking() {
		botonRanking = new JButton("RANKING");  
		botonRanking.setFont(fuente);
		decorarBotonRanking();
		registrarOyenteBotonRanking();
		imagenFondo.add(botonRanking); 
		botonRanking.setVisible(true);  
		botonRanking.revalidate();
		botonRanking.repaint();
	}

	public void decorarBotonModoEspecial() {
		botonModoEspecial.setBounds((ConstantesVistas.PANEL_ANCHO / 2) - 130 ,ConstantesVistas.PANEL_ALTO - 200, 260 , 50);
		botonModoEspecial.setFocusable(false);
		botonModoEspecial.setBackground(Color.RED);  
		botonModoEspecial.setForeground(Color.WHITE); 
	}
	public void decorarBotonModoOriginal() {
		botonModoOriginal.setBounds((ConstantesVistas.PANEL_ANCHO / 2) - 130 ,ConstantesVistas.PANEL_ALTO - 250, 260 , 50);
		botonModoOriginal.setFocusable(false);
		botonModoOriginal.setBackground(Color.BLUE);  
		botonModoOriginal.setForeground(Color.WHITE);  
	}

	public void decorarBotonRanking() {
		botonRanking.setBounds((ConstantesVistas.PANEL_ANCHO / 2) - 130 ,ConstantesVistas.PANEL_ALTO - 150, 260 , 50);
		botonRanking.setFocusable(false);
		botonRanking.setBackground(Color.black);
		botonRanking.setForeground(Color.WHITE);  
	}

	public void registrarOyenteBotonModoEspecial() {
		botonModoEspecial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladorVistas.accionarModoEspecial();
			}
		});
	}

	public void registrarOyenteBotonModoOriginal() {
		botonModoOriginal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladorVistas.accionarModoOriginal();
			}
		});
	}

	public void registrarOyenteBotonRanking() {
		botonRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladorVistas.accionarPantallaRanking();
			}
		});
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
