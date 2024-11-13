package Vista;

import java.awt.Color;
import Logica.Juego;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.*;

public class PantallaIngresoNombre extends JPanel {
	private static final long serialVersionUID = 1L;
	private ControladorDeVistas controladorVistas;
    private JLabel imagenFondo;
	private JLabel labelIngresoNombre;
    private JButton botonEnviar;
    private JTextField campoNombre;
    private Juego juego;
    
    public PantallaIngresoNombre(ControladorDeVistas controladorVistas,Juego j) {
        this.controladorVistas = controladorVistas;
        setSize(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);
        juego=j;
        setLayout(null);
        agregarFondo();
        agregarLabelIngresoNombre();
        agregarCampoNombre();
        agregarBotonEnviar();
    }
    public void agregarLabelIngresoNombre() {
        labelIngresoNombre = new JLabel("Ingrese su nombre para entrar al ranking");
        labelIngresoNombre.setFont(new Font("Press Start 2P", Font.BOLD, 18));
        labelIngresoNombre.setForeground(Color.WHITE);
        labelIngresoNombre.setBounds((ConstantesVistas.PANEL_ANCHO / 2) - 150, ConstantesVistas.PANEL_ALTO - 350, 300, 30);
        imagenFondo.add(labelIngresoNombre);
    }
    public void agregarFondo() {
        imagenFondo = new JLabel();
        ImageIcon iconoImagen = new ImageIcon(this.getClass().getResource("/Imagenes/Fondos/inicio.jpg")); 
        Image imagenEscalada = iconoImagen.getImage().getScaledInstance(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO, Image.SCALE_SMOOTH);
        Icon iconoImagenEscalado = new ImageIcon(imagenEscalada);
        imagenFondo.setIcon(iconoImagenEscalado);
        imagenFondo.setBounds(0, 0, ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);
        add(imagenFondo);
    }

    public void agregarCampoNombre() {
        campoNombre = new JTextField();
        campoNombre.setBounds((ConstantesVistas.PANEL_ANCHO / 2) - 100, ConstantesVistas.PANEL_ALTO - 300, 200, 30);
        add(campoNombre);
    }

    public void agregarBotonEnviar() {
        botonEnviar = new JButton("EMPEZAR");
        botonEnviar.setFont(new Font("Press Start 2P", Font.BOLD, 18));
        decorarBotonEnviar();
        registrarOyenteBotonEnviar();
        imagenFondo.add(botonEnviar);
    }

    public void decorarBotonEnviar() {
    	botonEnviar.setBackground(Color.RED);
    	botonEnviar.setForeground(Color.WHITE);
        botonEnviar.setBounds((ConstantesVistas.PANEL_ANCHO / 2) - 100, ConstantesVistas.PANEL_ALTO - 250, 200, 50);
        botonEnviar.setFocusable(false);
    }

    public void registrarOyenteBotonEnviar() {
        botonEnviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = campoNombre.getText();
                if (!nombre.isEmpty()) {
                    mostrarMensaje("Hola, " + nombre + "!");
                    juego.getNivelActual().getMario().setNombre(nombre);
                    controladorVistas.accionarPantallaAlIngresarNombre();
                } else {
                    mostrarMensaje("Por favor, ingresa un nombre.");
                }
                System.out.println(nombre);
            }
        });
    }
    private void mostrarMensaje(String mensaje) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(mensaje);
        label.setFont(new Font("Press Start 2P", Font.BOLD, 18));
        label.setForeground(Color.WHITE); 
        panel.add(label);
        panel.setBackground(Color.BLACK);
        JOptionPane.showMessageDialog(null, panel);
    }
    
}