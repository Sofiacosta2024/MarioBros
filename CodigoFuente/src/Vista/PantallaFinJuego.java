package Vista;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaFinJuego extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private ControladorDeVistas controladorVistas;
	private JLabel imagenFondo;

	public PantallaFinJuego(ControladorDeVistas controladorVistas) {
		this.controladorVistas = controladorVistas;
		inicializarComponentes();
	}

	private void inicializarComponentes() {
		setSize(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);
		setLayout(null);
		agregarFondo();
		agregarBotónSalir();
	}

	private void agregarFondo() {  	
		imagenFondo = new JLabel();
		ImageIcon iconoImagen = new ImageIcon(this.getClass().getResource("/Imagenes/Fondos/GameOver.png"));
		Image imagenEscalada = iconoImagen.getImage().getScaledInstance(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO, Image.SCALE_SMOOTH);
		Icon iconoImagenEscalado = new ImageIcon(imagenEscalada);
		imagenFondo.setIcon(iconoImagenEscalado);
		imagenFondo.setBounds(0,0, ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);
		add(imagenFondo);
	}

	private void agregarBotónSalir() {
		JButton botonSalir = new JButton("Salir");
		botonSalir.setBounds((ConstantesVistas.PANEL_ANCHO / 2) - 100,ConstantesVistas.PANEL_ALTO - 230, 200 , 50);
		asignarImagenBoton(botonSalir, "/Imagenes/Botones/BotonFinishNuevo.png");
		botonSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladorVistas.accionarPantallaFinDeJuegoSalir();
			}
		});
		imagenFondo.add(botonSalir);
		botonSalir.revalidate();
		botonSalir.repaint();
	}

	private void  asignarImagenBoton(JButton boton, String string) {
		ImageIcon iconoBoton = new ImageIcon(this.getClass().getResource(string));
		Image imagenEscalada = iconoBoton.getImage().getScaledInstance(200, 50, Image.SCALE_SMOOTH); 
		Icon iconoEscalado = new ImageIcon(imagenEscalada);
		boton.setIcon(iconoEscalado);
	}
}