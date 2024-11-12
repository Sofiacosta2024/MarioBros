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
    private JButton botonEnviar;
    private JTextField campoNombre;
    private Juego juego;
    private Puntuacion jugadorRanking;
    
    public PantallaIngresoNombre(ControladorDeVistas controladorVistas,Juego j) {
        this.controladorVistas = controladorVistas;
        setSize(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);
        juego=j;
        setLayout(null);
        agregarFondo();
        agregarCampoNombre();
        agregarBotonEnviar();
    }

    protected void agregarFondo() {
        imagenFondo = new JLabel();
        ImageIcon iconoImagen = new ImageIcon(this.getClass().getResource("/Imagenes/Fondos/inicio.jpg")); 
        Image imagenEscalada = iconoImagen.getImage().getScaledInstance(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO, Image.SCALE_SMOOTH);
        Icon iconoImagenEscalado = new ImageIcon(imagenEscalada);
        imagenFondo.setIcon(iconoImagenEscalado);
        imagenFondo.setBounds(0, 0, ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);
        add(imagenFondo);
    }

    protected void agregarCampoNombre() {
        campoNombre = new JTextField();
        campoNombre.setBounds((ConstantesVistas.PANEL_ANCHO / 2) - 100, ConstantesVistas.PANEL_ALTO - 300, 200, 30);
        add(campoNombre);
    }

    protected void agregarBotonEnviar() {
        botonEnviar = new JButton("EMPEZAR");
        botonEnviar.setFont(new Font("Press Start 2P", Font.BOLD, 18));
        decorarBotonEnviar();
        registrarOyenteBotonEnviar();
        imagenFondo.add(botonEnviar);
    }

    protected void decorarBotonEnviar() {
    	botonEnviar.setBackground(Color.RED);
    	botonEnviar.setForeground(Color.WHITE);
        botonEnviar.setBounds((ConstantesVistas.PANEL_ANCHO / 2) - 100, ConstantesVistas.PANEL_ALTO - 250, 200, 50);
        botonEnviar.setFocusable(false);
    }

    protected void registrarOyenteBotonEnviar() {
        botonEnviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = campoNombre.getText();
                if (!nombre.isEmpty()) {
                    mostrarMensaje("Hola, " + nombre + "!");
                    juego.getNivelActual().getMario().setNombre(nombre);
                    controladorVistas.accionarPantallaAlIngresar();
                } else {
                    mostrarMensaje("Por favor, ingresa un nombre.");
                }
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