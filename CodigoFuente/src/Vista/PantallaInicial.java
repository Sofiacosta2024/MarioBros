package Vista;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PantallaInicial extends JPanel {
	private static final long serialVersionUID = 1L;
	private ControladorDeVistas controladorVistas;
	private JLabel imagenFondo;
	private JButton botonIniciar;
	
	public PantallaInicial(ControladorDeVistas controladorVistas) {
		this.controladorVistas = controladorVistas;
		setSize(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);
		setLayout(null);
		agregarFondo();
		agregarBotonIniciar();
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
	
	public void agregarBotonIniciar() {
		botonIniciar = new JButton();
		decorarBotonIniciar();
		asignarImagenBoton(botonIniciar, "/Imagenes/Botones/BotonInicio.jpg");
		registrarOyenteBotonIniciar();
		imagenFondo.add(botonIniciar);
		botonIniciar.revalidate();
		botonIniciar.repaint();
	}
	
	private void  asignarImagenBoton(JButton b, String s) {
		ImageIcon iconoBoton = new ImageIcon(this.getClass().getResource(s));
	    Image imagenEscalada = iconoBoton.getImage().getScaledInstance(200, 50, Image.SCALE_SMOOTH); 
	    Icon iconoEscalado = new ImageIcon(imagenEscalada);
	    b.setIcon(iconoEscalado);
	}
	
	public void decorarBotonIniciar() {
		transparentarBoton(botonIniciar);
		botonIniciar.setBounds((ConstantesVistas.PANEL_ANCHO / 2) - 100,ConstantesVistas.PANEL_ALTO - 230, 200 , 50);
		botonIniciar.setFocusable(false);
	}
	
	public void registrarOyenteBotonIniciar() {
		botonIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladorVistas.accionarInicioJuego();
			}
		});
	}
	 
	public void transparentarBoton(JButton boton) {
		boton.setOpaque(false);
		boton.setContentAreaFilled(false);
		boton.setBorderPainted(false);
	}
}